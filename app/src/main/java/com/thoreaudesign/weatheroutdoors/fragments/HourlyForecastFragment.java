package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.thoreaudesign.weatheroutdoors.R;

public class HourlyForecastFragment extends Fragment
{
    public HourlyForecastFragment()
    {
    }

    public static HourlyForecastFragment newInstance(String cacheDir)
    {
        return new HourlyForecastFragment();
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hourly_forecast, container, false);
    }
}

