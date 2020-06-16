package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.thoreaudesign.weatheroutdoors.ForecastData;
import com.thoreaudesign.weatheroutdoors.ForecastExpandableListAdapter;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MinutelyForecastFragment extends WeatherFragmentBase
{
    public static MinutelyForecastFragment newInstance(String cacheData)
    {
        return (MinutelyForecastFragment)setBundle(new MinutelyForecastFragment(), cacheData);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return createView(R.layout.fragment_minutely_forecast, inflater, container);
    }

    protected void populateLayout()
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
}
