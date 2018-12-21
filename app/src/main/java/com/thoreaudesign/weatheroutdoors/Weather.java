package com.thoreaudesign.weatheroutdoors;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class Weather extends AppCompatActivity
{
    private FloatingActionButton metocean;
    private FloatingActionButton stormglass;
    private FloatingActionButton land;
    private TextView container;

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

        land = findViewById(R.id.land);

        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setText("Land!!!!");
            }
        });

        metocean = findViewById(R.id.metocean);

        metocean.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view)
            {
                final CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();

                final LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

                MarineRequest data = new MarineRequest("-32", "14");

                new AsyncTask<MarineRequest, Void, String>()
                {
                    @Override
                    protected String doInBackground(MarineRequest... params)
                    {
                        WeatherInterface weatherInterface = factory.build(WeatherInterface.class);

                        try
                        {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            return gson.toJson(weatherInterface.MetOcean(params[0]));
                        }
                        catch (LambdaFunctionException lfe)
                        {
                            String error = "Failed to invoke MetOcean API...";
                            Log.e("Error", error, lfe);
                            return error;
                        }
                    }

                    @Override
                    protected void onPostExecute(String result)
                    {

                        container.setText(result);

                    }
                }.execute(data);
            }
        });

        stormglass = findViewById(R.id.stormglass);

        stormglass.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view)
            {
                final CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();

                final LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

                MarineRequest data = new MarineRequest("-32", "14");

                new AsyncTask<MarineRequest, Void, String>()
                {
                    @Override
                    protected String doInBackground(MarineRequest... params)
                    {
                        WeatherInterface weatherInterface = factory.build(WeatherInterface.class);

                        try
                        {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            return gson.toJson(weatherInterface.Stormglass(params[0]));
                        }
                        catch (LambdaFunctionException lfe)
                        {
                            String error = "Failed to invoke Stormglass API...";
                            Log.e("Error", error, lfe);
                            return error;
                        }
                    }

                    @Override
                    protected void onPostExecute(String result) {

                        container.setText(result);

                    }
                }.execute(data);
            }
        });
    }
}