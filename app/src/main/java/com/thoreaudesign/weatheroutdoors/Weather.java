package com.thoreaudesign.weatheroutdoors;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;

public class Weather extends AppCompatActivity
{
    WeatherViewModel weatherViewModel;
    private DevicePermissionsManager permissionsManager;
    private String lat;
    private String lon;

    //<editor-fold desc="/** Android GPS Coordinates **/">

    private void getGPSParams()
    {
        Location location = (new GPSCoordinates(this)).getLocation();

        if (location != null)
        {
            String lat = Double.toString(location.getLatitude());
            Log.v("Latitude: " + lat);
            this.lat = lat;

            String lon = Double.toString(location.getLongitude());
            Log.v("Longitude: " + lon);
            this.lon = lon;

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

        this.permissionsManager = new DevicePermissionsManager(this);

        if (this.permissionsManager.permissionRequired())
        {
            this.permissionsManager.requestPermissions();
        }
        else
        {
            getGPSParams();
            weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
            weatherViewModel.setLatLon(lat, lon);

            Cache cache = new Cache(getCacheDir());
            weatherViewModel.setCache(cache);
            weatherViewModel.getCache().read();

            if (weatherViewModel.getCache().isCacheOutdated())
            {
                weatherViewModel.getWeatherDataFromAWS();
            }
            else
            {
                weatherViewModel.setWeatherData(
                    weatherViewModel.getCache().getData());
            }
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
        } else
        {
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
//        weatherViewModel.getCache().delete();
        Log.v("--- End ---");
    }

    protected void onDestroy()
    {
        Log.v("--- Begin ---");
        super.onDestroy();
        Log.v("--- End ---");
    }
    //</editor-fold>
}

