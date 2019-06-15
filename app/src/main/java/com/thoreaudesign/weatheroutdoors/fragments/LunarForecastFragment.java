package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro.StormglassAstro;

public class LunarForecastFragment extends DataFragment
{
    private String stormglassAstro = "stormglass_astro";

    private StormglassAstro hydrate(Bundle bundle)
    {
        Cache cache = new Cache(bundle.getString("cacheDir"), bundle.getString("cacheName"));
        cache.read();

        String json = this.getCacheSection(cache, this.stormglassAstro);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json , StormglassAstro.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        StormglassAstro data = this.hydrate(this.getArguments());

        return container;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public static LunarForecastFragment newInstance()
    {
        return new LunarForecastFragment();
    }
}

