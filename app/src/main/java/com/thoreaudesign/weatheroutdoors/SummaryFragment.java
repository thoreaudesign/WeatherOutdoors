package com.thoreaudesign.weatheroutdoors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.summary_fragment, container, false);

        TextView windView = layout.findViewById(R.id.wind);
        TextView weatherView = layout.findViewById(R.id.weather);
        TextView pressureView = layout.findViewById(R.id.pressure);

        windView.setText(getArguments().getString("wind"));
        weatherView.setText(getArguments().getString("weather"));
        pressureView.setText(getArguments().getString("pressure"));

        return layout;
    }

    public static SummaryFragment newInstance(String wind, String weather, String pressure)
    {
        SummaryFragment fragment = new SummaryFragment();

        Bundle bundle = new Bundle();

        bundle.putString("wind", wind);
        bundle.putString("weather", weather);
        bundle.putString("pressure", pressure);

        fragment.setArguments(bundle);

        return fragment;
    }
}
