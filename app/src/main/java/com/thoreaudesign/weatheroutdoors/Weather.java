package com.thoreaudesign.weatheroutdoors;

import android.location.Location;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.aws.AsyncRequest;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.RequestTemplate;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheManagerViewModelFactory;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;

public class Weather extends AppCompatActivity
{
    protected AsyncRequest asyncRequest;

    private Toolbar toolbar;
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
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //<editor-fold desc="/** Configure navigation **/">
        final BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_weather, R.id.navigation_marine, R.id.navigation_lunar, R.id.navigation_maps)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //</editor-fold>

        Cache cache = new Cache(getCacheDir());
        CacheViewModel cacheViewModel = new ViewModelProvider(this, new CacheManagerViewModelFactory(cache)).get(CacheViewModel.class);

        final Observer<Cache> observedCache = new Observer<Cache>()
        {
            @Override
            public void onChanged(@Nullable final Cache newCache)
            {
//                Weather.this.updateFragments(newCache.getData());
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
/*
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
*/
}
