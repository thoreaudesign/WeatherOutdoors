package com.thoreaudesign.weatheroutdoors;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import com.thoreaudesign.weatheroutdoors.fragments.*;
import com.thoreaudesign.weatheroutdoors.aws.*;

import java.lang.reflect.Field;

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

    private Location getLocation()
    {
        GPSCoordinates gps = new GPSCoordinates(Weather.this);

        Location location = gps.getLocation();

        if(location == null)
        {
            throw new RuntimeException("Failed to obatin GPS coordinates from device.");
        }
        else
        {
            return location;
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
        Log.d("Established AWS CognitoCachingCredentialsProvider.");
        Log.v(credentials.toString());
        LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);
        Log.d("Established AWS LambdaInvokerFactory.");
        Log.v(factory.toString());

        try
        {
            Location location = this.getLocation();
            Log.d("Successfully created Location object.");

            String lat = Double.toString(location.getLatitude());
            Log.i("Latitude: " + lat + ".");
            String lon = Double.toString(location.getLongitude());
            Log.i("Longitude: " + lon + ".");

            RequestParams params = new RequestParams(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
            Log.d("Successfully created RequestParams object.");
            Log.v(params.toString());

            LambdaFunctions fxns = new LambdaFunctions();

            for(Field field : fxns.getClass().getDeclaredFields())
            {
                String name = field.getName();

                RequestTemplate request = new RequestTemplate(Weather.this, factory, field.getName(), params);
                Log.d("Successfull created RequestTemplate object for AWS Lambda function " + name + ".");
                new AsyncRequest(request, field.getName()).execute(params);
                Log.d("Successfully submitted AsyncRequest for AWS Lambda function " + name + ".");
            }
        }
        catch (RuntimeException e)
        {
            Log.e(e.toString());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT);
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
                case 0:
                Log.d("Returning Data Fragment...");
                return DataFragment.newInstance();

                case 1:
                    Darksky darksky = new Darksky();

                    String data = darksky.getSummaryData(this.activity);

                    Log.d("Loaded Summary data successfully.");

                    Log.d("Returning Summary Fragment...");

                    return SummaryFragment.newInstance(data);

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