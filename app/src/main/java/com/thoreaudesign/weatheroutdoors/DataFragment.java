package com.thoreaudesign.weatheroutdoors;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View layout = inflater.inflate(R.layout.data_fragment, container, false);

        TextView displayData = layout.findViewById(R.id.display_data);
        displayData.setText("Welcome to Weather Outdoors! Click an icon below to see weather data in JSON format.");

        FloatingActionButton darksky = layout.findViewById(R.id.darksky);
        darksky.setOnClickListener(new RawData(this.getActivity(), LambdaFunctions.DARKSKY, displayData));

        FloatingActionButton stormglass = layout.findViewById(R.id.stormglass);
        stormglass.setOnClickListener(new RawData(this.getActivity(), LambdaFunctions.STORMGLASS, displayData));

        FloatingActionButton metocean = layout.findViewById(R.id.metocean);
        metocean.setOnClickListener(new RawData(this.getActivity(), LambdaFunctions.METOCEAN, displayData));

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
