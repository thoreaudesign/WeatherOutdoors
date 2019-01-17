package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.aws.*;
import com.thoreaudesign.weatheroutdoors.fragments.eventhandlers.DataFragmentOnClickListener;
import com.thoreaudesign.weatheroutdoors.Log;

import java.lang.reflect.Field;

public class DataFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View layout = inflater.inflate(R.layout.data_fragment, container, false);

        TextView displayData = layout.findViewById(R.id.display_data);
        displayData.setText("Welcome to Weather Outdoors! Click an icon below to see weather data in JSON format.");

        LambdaFunctions fxns = new LambdaFunctions();

        for(Field field : fxns.getClass().getDeclaredFields())
        {
            int id = getResources().getIdentifier(field.getName(), "id", "com.thoreaudesign.weatheroutdoors");

            FloatingActionButton fab = layout.findViewById(id);

            fab.setOnClickListener(new DataFragmentOnClickListener(this.getActivity(), field.getName(), displayData));

            Log.d("Attached onClickListener for FloatingActionButton " + field.getName() + "successfully.");
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

    public static DataFragment newInstance()
    {
        return new DataFragment();
    }
}
