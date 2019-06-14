package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.jsonTypes.Darksky.Darksky;

import org.json.JSONObject;

public class DailyForecastFragment extends Fragment
{
    private String dataSource = "darksky";

    private String getDarksyAsString(Cache cache)
    {
        String target = new String();
        try
        {
            JSONObject data = new JSONObject();
            data = new JSONObject(cache.getData());
            target = data.getString(this.dataSource);

        }
        catch (Exception e)
        {

        }

        return target;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();

        Cache cache = new Cache(bundle.getString("cacheDir"), bundle.getString("cacheName"));
        cache.read();

        String jsonString = this.getDarksyAsString(cache);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Darksky data = gson.fromJson(jsonString, Darksky.class);

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
