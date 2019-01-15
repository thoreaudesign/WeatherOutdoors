package com.thoreaudesign.weatheroutdoors;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.google.gson.JsonObject;

import com.thoreaudesign.weatheroutdoors.fragments.*;
import com.thoreaudesign.weatheroutdoors.aws.*;

public class Weather extends FragmentActivity
{
    private int REQUEST_RESULT_FINE;
    private int REQUEST_RESULT_COARSE;

    private LambdaInvokerFactory getLambdaInvokerFactory(CognitoCachingCredentialsProvider provider)
    {
        return new LambdaInvokerFactory(
                Weather.this,
                Regions.US_EAST_1,
                provider
        );
    }

    private CognitoCachingCredentialsProvider getCredentialsProvider()
    {
        return new CognitoCachingCredentialsProvider(
                Weather.this,
                "us-east-1:710ef06b-950f-44d4-8b5b-dbd630484d1c",
                Regions.US_EAST_1
        );
    }

    private void verifyPermissions()
    {
        Context context = Weather.this.getApplicationContext();

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Weather.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_RESULT_FINE);
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        if(savedInstanceState == null)
            verifyPermissions();

        setContentView(R.layout.activity_main);

        CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();
        LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

        GPSCoordinates gps = new GPSCoordinates(Weather.this);
        Location location = gps.getLocation();

        Log.i("Location",
        "Lat: " + Double.toString(location.getLatitude()) + "\n" +
            "Lon: " + Double.toString(location.getLongitude()));

        if (location == null)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(Weather.this);

            alert.setMessage("Unable to obtain location provider. Please review permissions for WeatherOutdoors under Settings > Apps > WeatherOutdoors.");
            alert.setTitle("Error");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    finishAffinity();
                }
            });
        }
        else
        {
            RequestParams params = new RequestParams(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));

            RequestTemplate darksky = new RequestTemplate(Weather.this, factory, LambdaFunctions.DARKSKY, params);
            new AsyncRequest(darksky, LambdaFunctions.DARKSKY).execute(params);

            RequestTemplate stormglass = new RequestTemplate(Weather.this, factory, LambdaFunctions.STORMGLASS, params);
            new AsyncRequest(stormglass, LambdaFunctions.STORMGLASS).execute(params);

            RequestTemplate stormglass_astro = new RequestTemplate(Weather.this, factory, LambdaFunctions.STORMGLASS_ASTRO, params);
            new AsyncRequest(stormglass_astro, LambdaFunctions.STORMGLASS_ASTRO).execute(params);

            RequestTemplate metocean = new RequestTemplate(Weather.this, factory, LambdaFunctions.METOCEAN, params);
            new AsyncRequest(metocean, LambdaFunctions.METOCEAN).execute(params);
        }

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new WeatherPagerAdapter(getSupportFragmentManager(), this));

        super.onCreate(savedInstanceState);
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter
    {
        private Activity activity;

        public WeatherPagerAdapter(FragmentManager fm, Activity activity)
        {
            super(fm);
            this.activity = activity;
        }

        @Override
        public Fragment getItem(int pos)
        {
            switch(pos)
            {
                case 0: return DataFragment.newInstance();

                case 1:
                    Darksky darksky = new Darksky();

                    String data = darksky.getSummaryData(this.activity);

                    return SummaryFragment.newInstance(data);
                    /*
                            darksky.getWindData(data),
                            darksky.getMoonData(data),
                            darksky.getWeatherData(data),
                            darksky.getTideData(data),
                            darksky.getPressureData(data)
                    */

                default: return DataFragment.newInstance();
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }
}