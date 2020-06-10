package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class WeatherFragmentBase extends Fragment
{
    String cacheData;
    View layout;

    protected Darksky data;

    abstract void populateLayout();

    static Fragment setBundle(Fragment fragment, String cacheData)
    {
        Log.v("--- Begin ---");

        Bundle bundle = new Bundle();
        bundle.putString(Cache.BUNDLE_KEY_DATA, cacheData);

        fragment.setArguments(bundle);

        Log.v("--- End ---");

        return fragment;
    }

    View createView(int resource, LayoutInflater inflater, ViewGroup container)
    {
        Log.v("--- Begin ---");

        layout = inflater.inflate(resource, container, false);

        updateWeatherData();

        Log.v("--- End ---");

        return layout;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        super.onActivityCreated(savedInstanceState);
        Bundle newBundle = getArguments();
        cacheData = newBundle.getString(Cache.BUNDLE_KEY_DATA);
    }

    private Darksky parseDarkskyCacheData(String cacheData) throws Throwable
    {
        Log.v("--- Begin ---");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String sectionName = ServiceName.DARKSKY.toLower();
        String json;

        if(cacheData == null)
        {
            throw new NullPointerException("Cache is empty.");
        }
        else
        {
            try
            {
                json = new JSONObject(cacheData).getString(sectionName);
            } catch (NullPointerException | JSONException e)
            {
                Log.e("Failed to parse section '" + sectionName + "' from JSON cache.");
                throw e;
            }

            Log.v("--- End ---");
        }

        return gson.fromJson(json, Darksky.class);
    }

    private void updateWeatherData()
    {
        try
        {
            this.data = parseDarkskyCacheData(this.cacheData);
            populateLayout();
        }
        catch (Throwable e)
        {
            Log.e(e.getMessage());
        }
    }

    public void updateWeatherData(String cacheData)
    {
        try
        {
            this.data = parseDarkskyCacheData(cacheData);
            populateLayout();
        }
        catch (Throwable e)
        {
            Log.e(e.getMessage());
        }
    }
}

