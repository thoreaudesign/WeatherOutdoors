package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentHourlyForecastBinding;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherDataObservable;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumDaily;

public class WeatherDailyForecastFragment extends WeatherFragmentBase
{
    private WeatherViewModel weatherViewModel;
    private FragmentHourlyForecastBinding homeSummaryBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onCreateView(inflater, container, savedInstanceState);
        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
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

        LinearLayout layout = homeSummaryBinding.getRoot().findViewById(R.id.hourly_forecast_linearLayout);

        for(DatumDaily dailyData : darksky.getDaily().getData())
        {
            Log.v("Time: " + Double.toString(dailyData.getTime()));
            TextView hourTextView = new TextView(getActivity());
            hourTextView.setId((int)dailyData.getTime());
            hourTextView.setText(dailyData.getSummary());
            hourTextView.setGravity(Gravity.CENTER);
            hourTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            layout.addView(hourTextView);
        }
    }
}
