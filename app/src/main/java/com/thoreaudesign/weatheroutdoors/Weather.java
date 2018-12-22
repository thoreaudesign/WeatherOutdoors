package com.thoreaudesign.weatheroutdoors;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class Weather extends AppCompatActivity
{
    private FloatingActionButton metocean;
    private FloatingActionButton stormglass;
    private FloatingActionButton darksky;
    private TextView container;
    private int REQUEST_RESULT_FINE;
    private int REQUEST_RESULT_COARSE;
    private HashMap<Object, Integer> permissions;

    public TextView getContainer()
    {
        return this.container;
    }

    private LambdaInvokerFactory getLambdaInvokerFactory(CognitoCachingCredentialsProvider provider) {
        return new LambdaInvokerFactory(
                Weather.this,
                Regions.US_EAST_1,
                provider
        );
    }

    private CognitoCachingCredentialsProvider getCredentialsProvider() {
        return new CognitoCachingCredentialsProvider(
                Weather.this,
                "us-east-1:710ef06b-950f-44d4-8b5b-dbd630484d1c",
                Regions.US_EAST_1
        );
    }

    private void userPermissionsCheck()
    {
        Context context = Weather.this.getApplicationContext();

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Weather.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_RESULT_FINE);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Weather.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, this.REQUEST_RESULT_COARSE);
        }
    }

    private void verifyPermissions()
    {
        this.permissions = new HashMap<Object, Integer>();

        this.userPermissionsCheck();

        if ((this.permissions.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
            (this.permissions.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        if (requestCode == this.REQUEST_RESULT_COARSE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                this.permissions.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
            }
            else
            {
                this.permissions.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_DENIED);
            }
        }
        else if (requestCode == this.REQUEST_RESULT_FINE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                this.permissions.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
            }
            else
            {
                this.permissions.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_DENIED);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

        container = findViewById(R.id.container);

        container.setText("Welcome to Weather Outdoors! Click an icon below to see weather data in JSON format.");

        if(verifyPermissions())
        {
            CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();
            LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

            GPSCoordinates gps = new GPSCoordinates(Weather.this.getApplicationContext());
            Location location = gps.getLocation();

            RequestParams params = new RequestParams(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));

            darksky = findViewById(R.id.darksky);
            darksky.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.DARKSKY, params));

            metocean = findViewById(R.id.metocean);
            metocean.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.METOCEAN, params));

            stormglass = findViewById(R.id.stormglass);
            stormglass.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.STORMGLASS, params));
        }
        else
        {
            finishAffinity();
        }
    }
}