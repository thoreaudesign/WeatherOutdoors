package com.thoreaudesign.weatheroutdoors;

import android.location.Location;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.aws.AsyncRequest;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.RequestTemplate;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.fragments.CurrentWeatherFragment;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragment;

import org.json.JSONObject;

import java.util.List;

public class Weather extends FragmentActivity
{
    private static final long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    public static final String CACHE_NAME = "weatheroutdoors";

    private Cache cache;

    private AsyncRequest lambdaRequest;

    private DevicePermissionsManager permissionsManager;

    private CognitoCachingCredentialsProvider getCredentialsProvider()
    {
        return new CognitoCachingCredentialsProvider(
            getApplicationContext(),
            "us-east-1:710ef06b-950f-44d4-8b5b-dbd630484d1c",
            Regions.US_EAST_1);
    }

    private RequestParams getGPSParams()
    {
        Location location = (new GPSCoordinates(this)).getLocation();

        if (location != null)
        {
            String lat = Double.toString(location.getLatitude());
            Log.v("Latitude: " + lat);

            String lon = Double.toString(location.getLongitude());
            Log.v("Longitude: " + lon);

            return new RequestParams(lat, lon);
        }
        else
        {
            throw new RuntimeException("Failed to obatin GPS coordinates from device.");
        }
    }

    private LambdaInvokerFactory getLambdaInvokerFactory(CognitoCachingCredentialsProvider paramCognitoCachingCredentialsProvider)
    {
        return new LambdaInvokerFactory(
            getApplicationContext(),
            Regions.US_EAST_1,
            paramCognitoCachingCredentialsProvider);
    }

    private RequestTemplate getRequestTemplate()
    {
        return new RequestTemplate(getLambdaInvokerFactory(getCredentialsProvider()));
    }

    private boolean isCacheCurrent()
    {
        boolean isCurrent = false;

        if (this.cache.getFile().exists())
        {
            long now = System.currentTimeMillis();
            long lastModified = this.cache.getFile().lastModified();

            Log.v("Current time: " + now);
            Log.v("Last modified: " + lastModified);
            Log.v("Fileize: " + this.cache.getFile().length());

            if (now - lastModified > CACHE_LIFE_MILLIS)
            {
                Log.i("Cache is out-of-date. Re-populating cache.");
                isCurrent = false;
            } else
            {
                Log.i("Cache is current.");
                isCurrent = true;
            }
        }

        Log.i("Cache not set. Retrieving weather data from source.");
        return isCurrent;
    }

    private boolean isResponseValid(String response)
    {
        boolean responseValid = false;

        try
        {
            JSONObject responseJson = new JSONObject(response);
            ServiceName[] serviceNames = ServiceName.values();

            for(int i = 0; i < serviceNames.length; i++)
            {
                ServiceName serviceName = serviceNames[i];

                if (responseJson.has(serviceName.toLower()))
                {
                    responseValid = true;
                }
                else
                {
                    Log.v("Lambda function returned invalid data from service '" + serviceName.toLower() + ".'");
                }
            }
        }
        catch (Exception exception)
        {
            Log.e(exception.getMessage());
            Log.v(response);
        }

        return responseValid;
    }

    public void getWeatherData()
    {
        ProgressBar progress = findViewById(R.id.progress);

        this.lambdaRequest = new AsyncRequest(getRequestTemplate(), getCacheDir().toString(), progress);

        this.lambdaRequest.setListener(new AsyncRequest.Listener()
        {
            public void onTaskResult(Object lambdaResult)
            {
                String response = new Gson().toJson(lambdaResult);

                Log.v(response);

                if (Weather.this.isResponseValid(response))
                {
                    Weather.this.cache.setData(response);

                    if (Weather.this.cache.write())
                    {
                        Log.i("Lambda response succeeeded. Updated cache with latest data.");
                    } else
                    {
                        Log.w("Failed to write cache data.");
                    }
                } else
                {
                    Weather.this.cache.getFile().setLastModified(System.currentTimeMillis());
                    Log.e("Lambda response failed validation. Updated lastModified date of cache. Data not updated.");
                }
                Weather.this.updateFragments();
            }
        });

        this.lambdaRequest.execute(this.getGPSParams());
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.permissionsManager = new DevicePermissionsManager( this);

        if (this.permissionsManager.permissionRequired())
        {
            this.permissionsManager.requestPermissions();
        }

        this.cache = new Cache(getCacheDir());

        Log.v("--- End ---");
    }

    protected void onDestroy()
    {
        Log.v("--- Begin ---");
        super.onDestroy();
        Log.v("--- End ---");
    }

    protected void onPause()
    {
        Log.v("--- Begin ---");
        super.onPause();
        Log.v("--- End ---");
    }

    public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfint)
    {
        Log.v("*** Begin *** ");
        if (paramInt == this.permissionsManager.REQUEST_RESULT_FINE)
        {
            Log.v("Permissions requested: android.permission.ACCESS_FINE_LOCATION");
            if (paramArrayOfint.length == 0 || paramArrayOfint[0] == -1)
            {
                Log.v("Failed to acquire permissions.");
                finishAffinity();
                return;
            }
            Log.v("Permissions acquired successfully.");
            return;
        }
        Log.v("Failed to match request code...");
    }

    protected void onResume()
    {
        Log.v("--- Begin ---");

        ViewPager mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new WeatherPagerAdapter(this, getSupportFragmentManager()));

        super.onResume();

        if (this.permissionsManager.permissionRequired())
        {
            Log.v("Permission denied.");
        }
        else if (isCacheCurrent())
        {
            updateFragments();
        }
        else
        {
            getWeatherData();
        }

        Log.v("--- End ---");
    }

    protected void onStart()
    {
        Log.v("--- Begin ---");
        super.onStart();
        Log.v("--- End ---");
    }

    protected void onStop()
    {
        Log.v("--- Begin ---");
        AsyncRequest asyncRequest = this.lambdaRequest;
        if (asyncRequest != null)
            asyncRequest.cancel(true);
        super.onStop();
        Log.v("--- End ---");
    }

    public void updateFragments()
    {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments.isEmpty())
        {
            return;
        }
        else
        {
            for (Fragment fragment : allFragments)
            {
                if (fragment.isVisible())
                {
                    ((WeatherFragment) fragment).update();
                }
            }
        }
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter
    {
        Weather weather;

        public WeatherPagerAdapter(Weather weather, FragmentManager fragmentManager)
        {
            super(fragmentManager);
            this.weather = weather;
        }

        public int getCount()
        {
            return 1;
        }

        public Fragment getItem(int pos)
        {
            switch(pos)
            {
                case 0:
                default:
                    return CurrentWeatherFragment.newInstance(this.weather.getCacheDir().toString());
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
    }
}
