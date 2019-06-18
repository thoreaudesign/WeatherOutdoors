package com.thoreaudesign.weatheroutdoors;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.aws.AsyncRequest;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.RequestTemplate;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.fragments.DailyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Weather extends FragmentActivity
{
    private int REQUEST_RESULT_FINE;
    private int REQUEST_RESULT_COARSE;

    private long CACHE_LIFE_MILLIS = 30 * 60 * 1000;
    public static final String CACHE_NAME = "weatheroutdoors";

    private ViewPager mViewPager;
    private AsyncRequest lambdaRequest;

    private Cache cache;

    public Cache getCache()
    {
        return cache;
    }

    public void setCache(Cache cache)
    {
        this.cache = cache;
    }

    private void verifyPermissions()
    {
        Context context = Weather.this.getApplicationContext();

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Weather.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_RESULT_FINE);
        }
    }

    private Location getLocation()
    {
        GPSCoordinates gps = new GPSCoordinates(Weather.this);

        Location location = gps.getLocation();

        return location;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        if (requestCode == this.REQUEST_RESULT_FINE)
        {
            if (grantResults.length == 0 || grantResults[0] == PackageManager.PERMISSION_DENIED)
            {
                finishAffinity();
            }
        }
    }

    private LambdaInvokerFactory getLambdaInvokerFactory(CognitoCachingCredentialsProvider provider)
    {
        return new LambdaInvokerFactory(
                Weather.this.getApplicationContext(),
                Regions.US_EAST_1,
                provider
        );
    }

    private CognitoCachingCredentialsProvider getCredentialsProvider()
    {
        return new CognitoCachingCredentialsProvider(
                Weather.this.getApplicationContext(),
                "us-east-1:710ef06b-950f-44d4-8b5b-dbd630484d1c",
                Regions.US_EAST_1
        );
    }

    private void populateCache()
    {
        Cache cache = new Cache(this.getCacheDir());

        Log.i("Checking weather data cache...");

        if(cache.getFile().exists())
        {
            long now = System.currentTimeMillis();
            long lastModified = cache.getFile().lastModified();

            Log.v("Current time: " + now);
            Log.v("Last modified: " + lastModified);

            Log.v("Filesize: " + cache.getFile().length());

            if(now - lastModified > this.CACHE_LIFE_MILLIS)
            {
                Log.i("Cache is out-of-date. Re-populating cache.");

                getWeatherData();
            }
            else
            {
                Log.i("Cache is current.");
            }
        }
        else
        {
            Log.i("Cache not set. Retrieving weather data from source.");

            getWeatherData();
        }
    }

    private int validateResponse(String response)
    {
        int numFailures = 0;

        try
        {
            JSONObject data = new JSONObject(response);

            for(ServiceName value : ServiceName.values())
            {
                if (data.has(value.toLower()))
                {
                    continue;
                }
                else
                {
                    numFailures++;
                    throw new JSONException("Lambda function returned invalid data from service '" + value.toLower() + ".'");
                }
            }
        }
        catch (Exception e)
        {
            Log.e(e.getMessage());
            Log.v(response);
        }

        return numFailures;
    }

    private void getWeatherData()
    {
        Location location = this.getLocation();

        if (location == null)
        {
            throw new RuntimeException("Failed to obatin GPS coordinates from device.");
        }
        else
        {
            Log.d("Successfully created Location object.");

            String lat = Double.toString(location.getLatitude());
            Log.i("Latitude: " + lat + ".");

            String lon = Double.toString(location.getLongitude());
            Log.i("Longitude: " + lon + ".");


            ProgressBar progress = findViewById(R.id.progress);
            LambdaInvokerFactory factory = this.getLambdaInvokerFactory(this.getCredentialsProvider());
            RequestParams params = new RequestParams(lat, lon);
            RequestTemplate requestTemplate = new RequestTemplate(factory);

            this.lambdaRequest = new AsyncRequest(requestTemplate, this.getCacheDir().toString(), progress);

            this.lambdaRequest.setListener(new AsyncRequest.Listener()
            {
                @Override
                public void onTaskResult(Object response)
                {
                    Cache cache = new Cache(Weather.this.getCacheDir());

                    String data = new Gson().toJson(response);

                    Log.v(data);

                    int numFailures = Weather.this.validateResponse(data);

                    if(numFailures > 0)
                    {
                        cache.getFile().setLastModified(System.currentTimeMillis());
                        Log.e("Lambda response failed validation. Updated lastModified date of cache. Data not updated.");
                    }
                    else
                    {
                        cache.setData(data);
                        if(cache.write())
                        {
                            Log.i("Lambda response succeeeded. Updated cache with latest data.");
                        }
                        else
                        {
                            Log.e("Failed to write cache data.");
                        }
                    }

                    Weather.this.updateFragments();

                }
            });

            this.lambdaRequest.execute(params);
        }
    }

    public void updateFragments()
    {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments == null || allFragments.isEmpty())
        {
            return;
        }
        for (Fragment fragment : allFragments)
        {
            if (fragment.isVisible())
            {
                ((WeatherFragment) fragment).update();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            verifyPermissions();
            populateCache();
        }
    }

    @Override
    protected void onResume()
    {
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new WeatherPagerAdapter(this, getSupportFragmentManager()));
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        if(this.lambdaRequest != null)
        {
            this.lambdaRequest.cancel(true);
        }
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter
    {
        Weather weather;

        public WeatherPagerAdapter(Weather weather, FragmentManager fm)
        {
            super(fm);
            this.weather = weather;
        }

        @Override
        public Fragment getItem(int pos)
        {
            switch(pos)
            {
                case 0:
                default:
                    return DailyForecastFragment.newInstance(this.weather.getCacheDir().toString());
/*
                case 1:
                    return MarineForecastFragment.newInstance();
                case 2:
                    return LunarForecastFragment.newInstance();

                    String data;

                    Cache cache = this.weather.getFile();

                    if(cache.exists() && cache.getData() != null)
                    {
                        Log.v("Cache good");
                        Darksky darksky = new Darksky();
                        data = darksky.getSummaryData(cache);
                        Log.v("Darksky data:" + data);
                    }
                    else
                    {
                        data = "Loading weather data...";
                    }

                    return SummaryFragment.newInstance(data);
*/
            }
        }

        @Override
        public int getCount()
        {
            return 1;
        }
    }
}