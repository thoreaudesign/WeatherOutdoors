package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class DailyForecastFragment extends DataFragment
{
    private String darksky = "darksky";

    private Darksky hydrate(Bundle bundle)
    {
        Cache cache = new Cache(bundle.getString("cacheDir"), bundle.getString("cacheName"));
        cache.read();

        String json = this.getCacheSection(cache, this.darksky);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json, Darksky.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Darksky data = this.hydrate(this.getArguments());

        final View layout = inflater.inflate(R.layout.daily_forecast_fragment, container, false);

        ScrollView scrollView = layout.findViewById(R.id.scrollView);

        return scrollView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public static DailyForecastFragment newInstance(Cache cache)
    {
        DailyForecastFragment fragment = new DailyForecastFragment();

        Bundle bundle = new Bundle();
        bundle.putString("cacheDir", cache.getDir().toString());
        bundle.putString("cacheName", cache.getName());

        fragment.setArguments(bundle);

        return fragment;

    }
}
