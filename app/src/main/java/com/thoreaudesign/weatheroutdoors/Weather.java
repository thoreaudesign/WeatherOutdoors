package com.thoreaudesign.weatheroutdoors;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
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

public class Weather extends AppCompatActivity
{
    private FloatingActionButton metocean;
    private FloatingActionButton stormglass;
    private FloatingActionButton darksky;
    private TextView container;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

        container = findViewById(R.id.container);

        container.setText("Welcome to Weather Outdoors! Click an icon below to see weather data in JSON format.");


        CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();

        LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

        darksky = findViewById(R.id.darksky);

        darksky.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.DARKSKY));

        metocean = findViewById(R.id.metocean);

        metocean.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.METOCEAN));

        stormglass = findViewById(R.id.stormglass);

        stormglass.setOnClickListener(new RequestTemplate(this, factory, LambdaFunctions.STORMGLASS));
    }
}