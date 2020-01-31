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
import com.thoreaudesign.weatheroutdoors.fragments.DailyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.HomeSummaryFragment;
import com.thoreaudesign.weatheroutdoors.fragments.HourlyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.MinutelyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;

import org.json.JSONObject;

import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

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
        WeatherPagerAdapter weatherPagerAdapter  = new WeatherPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        weatherPagerAdapter.setWeather(this);
        mViewPager.setAdapter(weatherPagerAdapter);
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
            Log.i("Cache not set. Retrieving weather data from source.");
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
                    ((WeatherFragmentBase)fragment).updateWeatherData();
                }
            }
        }
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter
    {
        Weather weather;

        public WeatherPagerAdapter(@NonNull FragmentManager fm, int behavior)
        {
            super(fm, behavior);
        }

        public void setWeather(Weather weather)
        {
            this.weather = weather;
        }

        public int getCount()
        {
            return 4;
        }

        public Fragment getItem(int pos)
        {
            String cacheDir = this.weather.getCacheDir().toString();
            Log.v("Cache directory: " + cacheDir);

            switch(pos)
            {
                case 0:
                default:
                    return HomeSummaryFragment.newInstance(cacheDir);
                case 1:
                    return MinutelyForecastFragment.newInstance(cacheDir);
                case 2:
                    return HourlyForecastFragment.newInstance(cacheDir);
                case 3:
                    return DailyForecastFragment.newInstance(cacheDir);
            }
        }
    }
}
