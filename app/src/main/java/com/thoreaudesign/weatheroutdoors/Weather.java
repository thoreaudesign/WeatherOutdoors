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

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.thoreaudesign.weatheroutdoors.aws.AsyncRequest;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.RequestTemplate;
import com.thoreaudesign.weatheroutdoors.fragments.DailyForecastFragment;

public class Weather extends FragmentActivity
{
    private int REQUEST_RESULT_FINE;
    private int REQUEST_RESULT_COARSE;
    private long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    private ViewPager mViewPager;

    private Cache cache;

    private AsyncRequest lambdaRequest;

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

    public Cache getCache()
    {
        return this.cache;
    }

    public void setCache(Cache cache)
    {
        this.cache = cache;
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

            String lambdaName = "weatheroutdoors";

            try
            {
                LambdaInvokerFactory factory = this.getLambdaInvokerFactory(this.getCredentialsProvider());

                RequestParams params = new RequestParams(lat, lon);

                RequestTemplate requestTemplate = new RequestTemplate(factory);

                this.lambdaRequest = new AsyncRequest(requestTemplate, lambdaName, this.getCache());

                lambdaRequest.execute(params);
            }
            catch (LambdaFunctionException lfe)
            {
                Log.v("Failed to invoke AWS Lambda function '" + this.cache.getName() + ".'");
            }
            catch (Exception e)
            {
                Log.e("Exception ocurred running lambda: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(savedInstanceState == null)
            verifyPermissions();

        setContentView(R.layout.activity_main);

        String cacheName = "weatheroutdoors";

        Cache cache = new Cache(this.getCacheDir(), cacheName);
        setCache(cache);
        cache.read();

        Log.v("Checking weather data cache...");

        long now = System.currentTimeMillis();
        long lastModified = this.getCache().getCache().lastModified();

        Log.v("Current time: " + now);
        Log.v("Last modified: " + lastModified);
        Log.v("Filesize: " + this.getCache().getCache().length());

        if(!cache.exists())
        {
            Log.v("Cache not set. Retrieving weather data from source.");

            getWeatherData();
        }
        else if(this.getCache().getData() == null)
        {
            Log.v("Cache is empty. Re-populating cache.");

            getWeatherData();
        }
        else if(now - lastModified > this.CACHE_LIFE_MILLIS)
        {
            Log.v("Cache is out-of-date. Re-populating cache.");

            getWeatherData();
        }

        super.onCreate(savedInstanceState);
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
                    return DailyForecastFragment.newInstance(this.weather.getCache());
/*
                case 1:
                    return MarineForecastFragment.newInstance();
                case 2:
                    return LunarForecastFragment.newInstance();

                    String data;

                    Cache cache = this.weather.getCache();

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
            return 3;
        }
    }
}