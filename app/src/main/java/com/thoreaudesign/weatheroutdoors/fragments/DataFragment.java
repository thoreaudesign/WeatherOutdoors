package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.aws.LambdaFunctions;
import com.thoreaudesign.weatheroutdoors.fragments.eventhandlers.DataFragmentOnClickListener;

import org.json.JSONObject;

public class DataFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();
        Cache cache = new Cache(bundle.getString("cacheDir"), bundle.getString("cacheName"));
        cache.read();

        final View layout = inflater.inflate(R.layout.data_fragment, container, false);

        TextView displayData = layout.findViewById(R.id.display_data);

        displayData.setText("Welcome to Weather Outdoors! Click an icon below to see weather cacheList in JSON format.");

        try
        {
            JSONObject cacheData = new JSONObject(cache.getData());

            String rawData = cacheData.toString();

            for (String functionName : LambdaFunctions.getFunctionNames())
            {
                int id = getResources().getIdentifier(functionName, "id", "com.thoreaudesign.weatheroutdoors");

                FloatingActionButton fab = layout.findViewById(id);

                if(rawData == null)
                {
                    rawData = "Loading weather data...";
                }
                else
                {
                    rawData = cacheData.getJSONObject(functionName).toString();
                }

                fab.setOnClickListener(new DataFragmentOnClickListener(rawData, displayData));

                Log.d("Attached onClickListener for FloatingActionButton " + functionName + "successfully.");
            }
        }
        catch (Exception e)
        {
            Log.v("Failed to load cache data: " + e.getMessage());
        }

        FloatingActionButton clearJson = layout.findViewById(R.id.clearJson);

        clearJson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView displayData = layout.findViewById(R.id.display_data);
                displayData.setText("");
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public static DataFragment newInstance(Cache cache)
//    public static DataFragment newInstance(String cacheDir, String cacheName)
    {
        DataFragment fragment = new DataFragment();

        Bundle bundle = new Bundle();
        bundle.putString("cacheDir", cache.getDir().toString());
        bundle.putString("cacheName", cache.getName());
//        bundle.putParcelable("cacheDir", cacheDir);

        fragment.setArguments(bundle);

        return fragment;

    }
}
