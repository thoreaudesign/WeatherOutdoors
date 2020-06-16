package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;

public class HourlyForecastFragment extends WeatherFragmentBase
{
    public static HourlyForecastFragment newInstance(String cacheData)
    {
        return (HourlyForecastFragment)setBundle(new HourlyForecastFragment(), cacheData);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        layout = inflater.inflate(R.layout.fragment_minutely_forecast, container, false);
        Log.v("--- End ---");
        return layout;
    }

    void parseData()
    {
        cacheData = "Hello World!!!";
    }

    protected void populateLayout()
    {
        TextView hourlyText = this.layout.findViewById(R.id.hourly_text);
        hourlyText.setText(cacheData);
    }
}

