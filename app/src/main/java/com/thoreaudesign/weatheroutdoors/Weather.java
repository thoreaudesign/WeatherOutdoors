package com.thoreaudesign.weatheroutdoors;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thoreaudesign.weatheroutdoors.aws.RequestParams;
import com.thoreaudesign.weatheroutdoors.aws.WeatherServiceClient;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheManagerViewModelFactory;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;
import com.thoreaudesign.weatheroutdoors.serialization.WeatherDataResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Weather extends AppCompatActivity
{
    CacheViewModel cacheViewModel;
    private DevicePermissionsManager permissionsManager;

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
        } else
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

        final Observer<Cache> observedCache = newCache ->
        {
//                Weather.this.updateFragments(newCache.getData());
        };

        cacheViewModel.getCacheLive().observe(this, observedCache);

        this.permissionsManager = new DevicePermissionsManager(this);

        if (this.permissionsManager.permissionRequired())
        {
            this.permissionsManager.requestPermissions();
        }

        getWeatherData(getGPSParams().getLat(), getGPSParams().getLon());

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
        } else
        {
            cacheViewModel = new CacheViewModel(new Cache(getCacheDir()));

            if (cacheViewModel.isCacheOutdated())
            {
                Log.i("Cache is out-of-date.");
                ProgressBar progressBar = findViewById(R.id.progress);
                progressBar.setVisibility(View.VISIBLE);
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

    public void getWeatherData(String lat, String lon)
    {
        WeatherServiceClient.getInstance()
                .getWeatherData(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<WeatherDataResponse>()
                           {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d)
                               {
                                   Log.v("-- Begin WeatherServcieClient.onSubscribe() --");
                                   Log.v("-- End WeatherServcieClient.onSubscribe() --");
                               }

                               @Override
                               public void onNext(@io.reactivex.rxjava3.annotations.NonNull WeatherDataResponse response)
                               {
                                   Log.v("-- Begin WeatherServcieClient.onNext() --");
                                   Log.v("Weather Data:");
                                   Weather.this.cacheViewModel.populateCache(response.toString());
                                   Log.v("-- End WeatherServcieClient.onNext() --");
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e)
                               {
                                   Log.v("-- Begin WeatherServcieClient.onError() --");
                                   Log.e(e.getMessage());
                                   Log.v("-- End WeatherServcieClient.onError() --");
                               }

                               @Override
                               public void onComplete()
                               {
                                   Log.v("-- Begin WeatherServcieClient.onComplete() --");
                                   Log.v("-- End WeatherServcieClient.onComplete() --");
                               }
                           }
                );
    }
}
