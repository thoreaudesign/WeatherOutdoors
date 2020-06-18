package com.thoreaudesign.weatheroutdoors.fragments;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.serialization.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class WeatherFragmentBase extends Fragment
{
    protected Darksky parseDarkskyCacheData(String cacheData) throws Throwable
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
}

