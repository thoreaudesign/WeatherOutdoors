package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.thoreaudesign.weatheroutdoors.CacheAdapter;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public abstract class WeatherFragmentBase extends Fragment
{
    protected Darksky data;

    abstract void populateLayoutWithData();

    private void populateDataFromCache()
    {
        Bundle newBundle = getArguments();
        CacheAdapter cacheAdapter = new CacheAdapter(newBundle);
        this.data = cacheAdapter.getCacheData(ServiceName.DARKSKY);
    }

    public void updateWeatherData()
    {
        this.populateDataFromCache();
        this.populateLayoutWithData();
    }
}

