package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.thoreaudesign.weatheroutdoors.R;

public class DailyForecastFragment extends Fragment
{
    public DailyForecastFragment()
    {
    }

    public static DailyForecastFragment newInstance(String cacheDir)
    {
        return new DailyForecastFragment();
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_forecast, container, false);
    }
}

