package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoreaudesign.weatheroutdoors.Cache;

import org.json.JSONObject;

public class DataFragment extends Fragment
{
    protected String getCacheSection(Cache cache, String sectionName)
    {
        String target = new String();
        try
        {
            JSONObject data = new JSONObject();
            data = new JSONObject(cache.getData());
            target = data.getString(sectionName);

        }
        catch (Exception e)
        {

        }

        return target;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return container;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
}
