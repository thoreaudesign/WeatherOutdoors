package com.thoreaudesign.weatheroutdoors;

import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.aws.AsyncRequest;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.RequestTemplate;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheManagerViewModelFactory;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;
import com.thoreaudesign.weatheroutdoors.fragments.HomeSummaryFragment;
import com.thoreaudesign.weatheroutdoors.fragments.HourlyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.MinutelyForecastFragment;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;

import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class Weather extends AppCompatActivity
{
    protected AsyncRequest asyncRequest;
    ActionBarDrawerToggle mDrawerToggle;

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private CacheViewModel cacheViewModel;
    private DevicePermissionsManager permissionsManager;

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

    //<editor-fold desc="/** Android Activity Lifecycle **/">

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_description_open, R.string.drawer_description_close)
            {
                public void onDrawerClosed(View view)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
                }

                public void onDrawerOpened(View drawerView)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }

        ViewPager mViewPager = findViewById(R.id.viewPager);
        WeatherPagerAdapter weatherPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        weatherPagerAdapter.setWeather(this);
        mViewPager.setAdapter(weatherPagerAdapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /**
         * Subscribe to CacheViewModel and observe CacheViewModel.Cache.
         */
         Cache cache = new Cache(getCacheDir());
        CacheViewModel cacheViewModel = new ViewModelProvider(this, new CacheManagerViewModelFactory(cache)).get(CacheViewModel.class);

        final Observer<Cache> observedCache = new Observer<Cache>()
        {
            @Override
            public void onChanged(@Nullable final Cache newCache)
            {
                Weather.this.updateFragments(newCache.getData());
            }
        };

        cacheViewModel.getCacheLive().observe(this, observedCache);

        this.permissionsManager = new DevicePermissionsManager( this);

        if (this.permissionsManager.permissionRequired())
        {
            this.permissionsManager.requestPermissions();
        }

        Log.v("--- End ---");
    }

    protected void onStart()
    {
        Log.v("--- Begin ---");
        super.onStart();
        Log.v("--- End ---");
    }

    protected void onResume()
    {
        Log.v("--- Begin ---");

        super.onResume();

        if (this.permissionsManager.permissionRequired())
        {
            Log.v("Permission denied.");
        }
        else
        {
            cacheViewModel = new CacheViewModel(new Cache(getCacheDir()));

            if (cacheViewModel.isCacheOutdated())
            {
                Log.i("Cache is out-of-date.");
                ProgressBar progressBar = findViewById(R.id.progress);
                populateCache(progressBar, getGPSParams(), getRequestTemplate());
            }
            else
            {
                updateFragments(cacheViewModel.getCacheData());
            }
        }
        Log.v("--- End ---");
    }

    protected void onPause()
    {
        Log.v("--- Begin ---");
        super.onPause();
        Log.v("--- End ---");
    }

    protected void onStop()
    {
        Log.v("--- Begin ---");
        killAsyncRequest();
        super.onStop();
        Log.v("--- End ---");
    }

    protected void onDestroy()
    {
        Log.v("--- Begin ---");
        super.onDestroy();
        Log.v("--- End ---");
    }

    //</editor-fold>

    public void killAsyncRequest()
    {
        if (asyncRequest != null)
            asyncRequest.cancel(true);
    }

    private void populateCache(ProgressBar progressBar, RequestParams requestParams, RequestTemplate requestTemplate)
    {
        asyncRequest = new AsyncRequest(requestTemplate, progressBar, this);

        asyncRequest.setListener(new AsyncRequest.Listener()
        {
            public void onTaskResult(Object lambdaResult)
            {
                String response = new Gson().toJson(lambdaResult);

                Log.v(response);

                if (asyncRequest.isResponseValid(response))
                {
                    cacheViewModel.populateCache(response);
                }
                else
                {
                    cacheViewModel.setLastModified(System.currentTimeMillis());

                    Log.e("Lambda response failed validation. Updated lastModified date of cache. Data not updated.");
                }
            }
        });

        asyncRequest.execute(requestParams);
    }

    public void updateFragments(String cacheData)
    {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (!allFragments.isEmpty())
        {
            for (Fragment fragment : allFragments)
            {
                if (fragment.isVisible())
                {
                    ((WeatherFragmentBase)fragment).updateWeatherData(cacheData);
                }
            }
        }
    }

    public void updateFragments()
    {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (!allFragments.isEmpty())
        {
            for (Fragment fragment : allFragments)
            {
                if (fragment.isVisible())
                {
                    ((WeatherFragmentBase)fragment).updateWeatherData(cacheViewModel.getCacheData());
                }
            }
        }
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter
    {
        Weather weather;

        WeatherPagerAdapter(@NonNull FragmentManager fm, int behavior)
        {
            super(fm, behavior);
        }

        void setWeather(Weather weather)
        {
            this.weather = weather;
        }

        public int getCount()
        {
            return 3;
        }

        @NonNull
        public Fragment getItem(int pos)
        {
            String cacheData = cacheViewModel.getCacheData();
            Log.v("Cache data at fragment creation: " + cacheData);

            switch(pos)
            {
                default:
                case 0:
                    return HomeSummaryFragment.newInstance(cacheData);
               case 1:
                    return MinutelyForecastFragment.newInstance(cacheData);
               case 2:
                    return HourlyForecastFragment.newInstance(cacheData);
            }
        }
    }
}
