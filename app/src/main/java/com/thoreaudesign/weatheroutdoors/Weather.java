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
import com.thoreaudesign.weatheroutdoors.cache.CacheSingleton;
import com.thoreaudesign.weatheroutdoors.fragments.HomeSummaryFragment;
import com.thoreaudesign.weatheroutdoors.fragments.HourlyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;

import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class Weather extends FragmentActivity
{
    protected AsyncRequest asyncRequest;

    private CacheSingleton cacheSingleton;

    private DevicePermissionsManager permissionsManager;

    public void killAsyncRequest()
    {
        if (asyncRequest != null)
            asyncRequest.cancel(true);
    }

    //<editor-fold desc="/** AWS Lambda Plumbing **/">

    private CognitoCachingCredentialsProvider getCredentialsProvider()
    {
        return new CognitoCachingCredentialsProvider(
            getApplicationContext(),
            "us-east-1:710ef06b-950f-44d4-8b5b-dbd630484d1c",
            Regions.US_EAST_1);
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

    //</editor-fold>

    //<editor-fold desc="/** Android GPS Coordinates **/">

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

    //</editor-fold>

    //<editor-fold desc="/** Android Permissions **/">

    public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfint)
    {
        Log.v("--- Begin ---");
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

    //</editor-fold>

    private void populateCache(ProgressBar progressBar, RequestParams requestParams, RequestTemplate requestTemplate)
    {
        asyncRequest = new AsyncRequest(requestTemplate, progressBar);

        asyncRequest.setListener(new AsyncRequest.Listener()
        {
            public void onTaskResult(Object lambdaResult)
            {
                String response = new Gson().toJson(lambdaResult);

                Log.v(response);

                if (asyncRequest.isResponseValid(response))
                {
                    cacheSingleton.getCacheManager().populateCache(response);

                    Weather.this.updateFragments();
                }
                else
                {
                    cacheSingleton.getCacheManager().setLastModified(System.currentTimeMillis());

                    Log.e("Lambda response failed validation. Updated lastModified date of cache. Data not updated.");
                }
            }
        });

        asyncRequest.execute(requestParams);
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
        else if (cacheSingleton.getCacheManager().isCacheOutdated())
        {
            ProgressBar progressBar = findViewById(R.id.progress);
            populateCache(progressBar, getGPSParams(), getRequestTemplate());
        }
        else
        {
            updateFragments();
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
        killAsyncRequest();
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
            String cacheData = cacheSingleton.getCacheManager().getCacheData();

            switch(pos)
            {
                case 0:
                default:
                    return HomeSummaryFragment.newInstance(cacheData);
//                case 1:
//                    return MinutelyForecastFragment.newInstance(cacheDir);
                case 2:
                    return HourlyForecastFragment.newInstance(cacheData);
//                case 3:
//                    return DailyForecastFragment.newInstance(cacheDir);
            }
        }
    }
}
