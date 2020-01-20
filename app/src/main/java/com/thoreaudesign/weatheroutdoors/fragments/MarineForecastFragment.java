package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MarineForecastFragment extends Fragment
{
    public static MarineForecastFragment newInstance()
    {
        return new MarineForecastFragment();
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
