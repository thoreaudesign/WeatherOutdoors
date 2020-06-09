package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro.StormglassAstro;

public class LunarForecastFragment extends Fragment
{
    private String stormglassAstro = "stormglass_astro";

    private StormglassAstro hydrate(Bundle paramBundle)
    {
        Cache cache = new Cache(paramBundle.getString("cacheDir"));
        cache.read();
        String str = cache.getSection(ServiceName.STORMGLASSASTRO.toLower());
        return (StormglassAstro) (new GsonBuilder()).create().fromJson(str, StormglassAstro.class);
    }

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
        hydrate(getArguments());
        return (View) paramViewGroup;
    }
}
