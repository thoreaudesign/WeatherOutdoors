package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LunarForecastFragment extends Fragment
{
    private String stormglassAstro = "stormglass_astro";

    public static LunarForecastFragment newInstance()
    {
        return new LunarForecastFragment();
    }

    public void onActivityCreated(Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return (View) paramViewGroup;
    }
}
