package com.thoreaudesign.weatheroutdoors;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.amazonaws.mobileconnectors.lambdainvoker.*;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

public class Weather extends AppCompatActivity
{
    private static final int UPDATE_INTERVAL = 1800000;

    private FloatingActionButton metocean;
    private FloatingActionButton stormglass;
    private FloatingActionButton darksky;
    private TextView container;
    private int REQUEST_RESULT_FINE;
    private int REQUEST_RESULT_COARSE;

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
        super.onCreate(savedInstanceState);

        /**
         * Only validate permissions if the activity never previously existed.
         */
        if(savedInstanceState == null)
        {
            verifyPermissions();
        }

        setContentView(R.layout.activity_weather);

        container = findViewById(R.id.container);

        container.setText("Welcome to Weather Outdoors! Click an icon below to see weather data in JSON format.");

        final Handler handler = new Handler();

        Runnable weatherData = new Runnable()
        {
            @Override
            public void run()
            {
                CognitoCachingCredentialsProvider credentials = Weather.this.getCredentialsProvider();
                LambdaInvokerFactory factory = Weather.this.getLambdaInvokerFactory(credentials);

                GPSCoordinates gps = new GPSCoordinates(Weather.this);
                Location location = gps.getLocation();

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
                } else
                {
                    RequestParams params = new RequestParams(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));

                    RequestTemplate darksky = new RequestTemplate(Weather.this, factory, LambdaFunctions.DARKSKY, params);
                    new AsyncRequest(darksky, LambdaFunctions.DARKSKY).execute(params);

                    RequestTemplate stormglass = new RequestTemplate(Weather.this, factory, LambdaFunctions.STORMGLASS, params);
                    new AsyncRequest(stormglass, LambdaFunctions.STORMGLASS).execute(params);

                    RequestTemplate metocean = new RequestTemplate(Weather.this, factory, LambdaFunctions.METOCEAN, params);
                    new AsyncRequest(metocean, LambdaFunctions.METOCEAN).execute(params);

                    handler.postDelayed(this, Weather.this.UPDATE_INTERVAL);

                }
            }
        };

        handler.post(weatherData);

        findViewById(R.id.darksky).setOnClickListener(new Test(this, LambdaFunctions.DARKSKY));

        findViewById(R.id.stormglass).setOnClickListener(new Test(this, LambdaFunctions.STORMGLASS));

        findViewById(R.id.metocean).setOnClickListener(new Test(this, LambdaFunctions.METOCEAN));
    }
}