package com.thoreaudesign.weatheroutdoors;

import android.content.Context;

public class WeatherInput {
    private float lat;
    private float lon;
    private Context context;

    public WeatherInput (float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
        this.context = context;
    }

    public float getLat()
    {
        return this.lat;
    }

    public float getLon()
    {
        return this.lon;
    }

    public Context getContext(){
        return this.context;
    }
}
