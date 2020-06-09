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

    private String getCacheSection(String cacheData, String sectionName)
    {
        try
        {
            return new JSONObject(cacheData).getString(sectionName);
        }
        catch (JSONException jSONException)
        {
            Log.e("Failed to parse section '" + sectionName + "' from JSON cache.");
            /** TO-DO - Change this to a different type of exception. */
            throw new RuntimeException(jSONException);
        }
    }

    public Darksky parseDarkskyCacheData(ServiceName serviceName, String cacheData)
    {
        Log.v("--- Begin ---");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = null;

        try
        {
            try
            {
                json = getCacheSection(cacheData, serviceName.toLower());
            }
            catch (RuntimeException runtimeException)
            {
                /** TO-DO - Implement exception that can be caught by activity */
                Log.e("An error occurred retrieving cache data... Deleted existing cache.");
            }
        }
        catch (NullPointerException e)
        {
            Log.e(e.getMessage());
        }

        Log.v("--- End ---");

        return gson.fromJson(json, Darksky.class);
    }

    public void updateWeatherData()
    {
        populateLayoutWithData();
    }
}

