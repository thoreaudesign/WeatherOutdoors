package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentHourlyForecastBinding;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherDataObservable;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

public class WeatherHourlyForecastFragment extends WeatherFragmentBase
{
    private WeatherViewModel weatherViewModel;
    private FragmentHourlyForecastBinding homeSummaryBinding;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onCreateView(inflater, container, savedInstanceState);
        context = this.getContext();
        weatherViewModel = new ViewModelProvider(requireActivity()).get(com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel.class);
        homeSummaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hourly_forecast, container, false);
        homeSummaryBinding.setWeatherDataObservable(weatherViewModel.getWeatherDataObservable().getValue());
        Log.v("-- End --");
        return homeSummaryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onViewCreated(mView, savedInstanceState);
        weatherViewModel.getWeatherDataObservable().observe(getViewLifecycleOwner(), new Observer<WeatherDataObservable>()
        {
            @Override
            public void onChanged(WeatherDataObservable weatherDataObservable)
            {
                Log.v("--- Begin ---");
                try
                {
                    populateViews(weatherDataObservable);
                }
                catch (Throwable e)
                {
                    Log.e(e.getMessage());
                }
                Log.v("--- End ---");
            }
        });
        Log.v("-- End --");
    }

    private void populateViews(WeatherDataObservable weatherDataObservable)
    {
        Darksky darksky = weatherDataObservable.getWeatherData().darksky;
        LinearLayout outterLayout = homeSummaryBinding.getRoot().findViewById(R.id.hourly_forecast_linearLayout);

        for(DatumHourly hourlyData : darksky.getHourly().getData())
        {
            ConstraintLayout innerLayout = (ConstraintLayout)LayoutInflater.from(context).inflate(R.layout.forecast_list_item_container, null);
            ((TextView)innerLayout.findViewById(R.id.time)).setText(String.format(Double.toString(hourlyData.getTime())));
            ((TextView)innerLayout.findViewById(R.id.summary)).setText(hourlyData.getSummary());
            ((ImageView)innerLayout.findViewById(R.id.imageView)).setImageResource(WeatherIcon.get(hourlyData.getIcon()));
            ((TextView)innerLayout.findViewById(R.id.precipProbability)).setText(String.format(Double.toString(hourlyData.getPrecipProbability())));
            outterLayout.addView(innerLayout);
        }
    }
}
