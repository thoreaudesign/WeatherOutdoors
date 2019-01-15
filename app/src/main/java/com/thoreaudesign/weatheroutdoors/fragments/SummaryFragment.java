package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.R;

import org.w3c.dom.Text;

public class SummaryFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.summary_fragment_linear, container, false);

        TextView summary = layout.findViewById(R.id.summary);

        summary.setText(getArguments().getString("summary"));

        return layout;
/*
        TextView windView = layout.findViewById(R.id.wind);
        TextView moonView = layout.findViewById(R.id.moon);
        TextView weatherView = layout.findViewById(R.id.weather);
        TextView tideView = layout.findViewById(R.id.tide);
        TextView pressureView = layout.findViewById(R.id.pressure);

        windView.setText(getArguments().getString("wind"));
        moonView.setText(getArguments().getString("moon"));
        weatherView.setText(getArguments().getString("weather"));
        tideView.setText(getArguments().getString("tide"));
        pressureView.setText(getArguments().getString("pressure"));
*/
    }

    public static SummaryFragment newInstance(String summary)
    {
        SummaryFragment fragment = new SummaryFragment();

        Bundle bundle = new Bundle();

        bundle.putString("summary", summary);
/*
        bundle.putString("wind", wind);
        bundle.putString("moon", moon);
        bundle.putString("weather", weather);
        bundle.putString("tide", tide);
        bundle.putString("pressure", pressure);
*/
        fragment.setArguments(bundle);

        return fragment;
    }
}
