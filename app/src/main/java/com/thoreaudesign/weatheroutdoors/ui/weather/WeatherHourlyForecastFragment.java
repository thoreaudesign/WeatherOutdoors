package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.ForecastData;
import com.thoreaudesign.weatheroutdoors.ForecastExpandableListAdapter;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WeatherHourlyForecastFragment extends WeatherFragmentBase
{
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_minutely_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
        final View view = mView;
        super.onViewCreated(view, savedInstanceState);
        CacheViewModel model = new ViewModelProvider(requireActivity()).get(CacheViewModel.class);
        model.getCacheLive().observe(getViewLifecycleOwner(), new Observer<Cache>()
        {
            @Override
            public void onChanged(Cache cache)
            {
                Log.v("--- Begin ---");
                try
                {
                    Darksky darksky = WeatherHourlyForecastFragment.this.parseDarkskyCacheData(cache.getData());
                    ForecastData forecastData = new ForecastData(darksky);
                    ExpandableListView expandableListView = view.findViewById(R.id.forecast_list_view);
                    final LinkedHashMap<String, List<DatumHourly>> forecastDataList = forecastData.getData();
                    final List<String> expandableListTitle = new ArrayList<String>(forecastDataList.keySet());
                    ExpandableListAdapter expandableListAdapter = new ForecastExpandableListAdapter(WeatherHourlyForecastFragment.this.getContext(), expandableListTitle, forecastDataList);
                    expandableListView.setAdapter(expandableListAdapter);
                    expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
                    {

                        @Override
                        public void onGroupExpand(int groupPosition)
                        {
                            Toast.makeText(WeatherHourlyForecastFragment.this.getContext(),
                                    expandableListTitle.get(groupPosition) + " List Expanded.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
                    {

                        @Override
                        public void onGroupCollapse(int groupPosition)
                        {
                            Toast.makeText(WeatherHourlyForecastFragment.this.getContext(),
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
                                    WeatherHourlyForecastFragment.this.getContext(),
                                    expandableListTitle.get(groupPosition)
                                            + " -> "
                                            + forecastDataList.get(
                                            expandableListTitle.get(groupPosition)).get(
                                            childPosition), Toast.LENGTH_SHORT
                            ).show();
                            return false;
                        }
                    });
                } catch (Throwable e)
                {
                    Log.e(e.getMessage());
                }
            }
        });
    }
}
