package com.thoreaudesign.weatheroutdoors.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.thoreaudesign.weatheroutdoors.ForecastData;
import com.thoreaudesign.weatheroutdoors.ForecastExpandableListAdapter;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MinutelyForecastFragment extends Fragment implements WeatherFragment
{
    private Darksky data;
    public View layout;

    private void generateForecast()
    {
        ForecastData forecastData = new ForecastData(this.data);
        ExpandableListView expandableListView = this.layout.findViewById(R.id.forecast_list_view);
        final LinkedHashMap<String, List<DatumHourly>> forecastDataList = forecastData.getData();
        final List<String> expandableListTitle = new ArrayList<String>(forecastDataList.keySet());
        ExpandableListAdapter expandableListAdapter = new ForecastExpandableListAdapter(this.getContext(), expandableListTitle, forecastDataList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {

            @Override
            public void onGroupExpand(int groupPosition)
            {
                Toast.makeText(MinutelyForecastFragment.this.getContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {

            @Override
            public void onGroupCollapse(int groupPosition)
            {
                Toast.makeText(MinutelyForecastFragment.this.getContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id)
            {
                Toast.makeText(
                        MinutelyForecastFragment.this.getContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + forecastDataList.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    public static MinutelyForecastFragment newInstance(String param1, String param2)
    {
        Log.v("--- Begin ---");

        MinutelyForecastFragment minutelyForecastFragment = new MinutelyForecastFragment();

        Bundle bundle = new Bundle();
//        bundle.putString("cacheDir", cacheDir);

        minutelyForecastFragment.setArguments(bundle);

        Log.v("--- End ---");

        return minutelyForecastFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_minutely_forecast, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public void update()
    {

    }
}
