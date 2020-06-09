package com.thoreaudesign.weatheroutdoors.fragments;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class WeatherFragmentBase extends Fragment
{
    protected Darksky data;

    abstract void populateLayoutWithData();

    private String getCacheSection(String cacheData, String sectionName) throws Throwable
    {
        try
        {
            return new JSONObject(cacheData).getString(sectionName);
        }
        catch (JSONException jSONException)
        {
            Log.e("Failed to parse section '" + sectionName + "' from JSON cache.");
            throw jSONException;
        }
    }

    protected Darksky parseDarkskyCacheData(ServiceName serviceName, String cacheData) throws Throwable
    {
        Log.v("--- Begin ---");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json;

        try
        {
            json = getCacheSection(cacheData, serviceName.toLower());
        }
        catch (NullPointerException nullPointerException)
        {
            throw nullPointerException;
        }
        catch (JSONException jSONException)
        {
            throw jSONException;
        }

        Log.v("--- End ---");

        return gson.fromJson(json, Darksky.class);
    }

    public void updateWeatherData()
    {
        populateLayoutWithData();
    }
}

