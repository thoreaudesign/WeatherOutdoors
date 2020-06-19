package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentHourlyForecastBinding;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;

public class WeatherHourlyForecastFragment extends WeatherFragmentBase
{
    private WeatherViewModel weatherViewModel;
    private FragmentHourlyForecastBinding homeSummaryBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        weatherViewModel = new ViewModelProvider(requireActivity()).get(com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel.class);
        homeSummaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_summary, container, false);
        homeSummaryBinding.setWeatherDataObservable(weatherViewModel.getWeatherDataObservable().getValue());
        Log.v("-- End --");
        return homeSummaryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
    }
