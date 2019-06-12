package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.R;

public class SummaryFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();

        View layout = inflater.inflate(R.layout.summary_fragment_linear, container, false);

        TextView summary = layout.findViewById(R.id.summary);

        summary.setText(bundle.getString("summary"));

        return layout;
    }

    public static SummaryFragment newInstance(String summary)
    {
        SummaryFragment fragment = new SummaryFragment();

        Bundle bundle = new Bundle();

        bundle.putString("summary", summary);

        fragment.setArguments(bundle);

        return fragment;
    }
}
