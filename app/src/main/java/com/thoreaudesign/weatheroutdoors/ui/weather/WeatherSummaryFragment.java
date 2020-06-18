package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.Compass;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentHomeSummaryBinding;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class WeatherSummaryFragment extends WeatherFragmentBase
{
    private CacheViewModel cacheViewModel;
    private FragmentHomeSummaryBinding homeSummaryBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        cacheViewModel = new ViewModelProvider(requireActivity()).get(CacheViewModel.class);
        homeSummaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_summary, container, false);
        homeSummaryBinding.setCacheViewModel(cacheViewModel);
        return homeSummaryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(mView, savedInstanceState);
        cacheViewModel.getCache().observe(getViewLifecycleOwner(), new Observer<Cache>()
        {
            @Override
            public void onChanged(Cache cache)
            {
                Log.v("--- Begin ---");
                try
                {
                    Darksky darksky = cacheViewModel.getCache().getValue().getDarkskyData();

                    Integer temperature = getInt(darksky.getCurrently().getTemperature());
                    Integer apparentTemperature = getInt(darksky.getCurrently().getApparentTemperature());
                    Integer humidity = getInt(darksky.getCurrently().getHumidity() * 100.0D);
                    Integer dewPoint = getInt(darksky.getCurrently().getDewPoint());
                    Integer windSpeed = getInt(darksky.getCurrently().getWindSpeed());
                    Integer windDirection = getInt(darksky.getCurrently().getWindBearing());
                    String summary = darksky.getCurrently().getSummary();

                    String temperatureText = temperature.toString() + "F";
                    String apparentTemperatureText = "Feels like " + apparentTemperature.toString() + "F";
                    String windText = "Wind " + windSpeed.toString() + "mph " + Compass.getWindDirection(windDirection);
                    String humidityText = "Humidity " + humidity + "%";
                    String dewPointText = "Dew Point " + dewPoint.toString() + "F";

                    homeSummaryBinding.currentDegrees.setText(temperatureText);
                    homeSummaryBinding.currentApparentTemp.setText(apparentTemperatureText);
                    homeSummaryBinding.currentHumidity.setText(humidityText);
                    homeSummaryBinding.currentDewpoint.setText(dewPointText);
                    homeSummaryBinding.currentWind.setText(windText);
                    homeSummaryBinding.currentDescription.setText(summary);
                    homeSummaryBinding.currentIcon.setImageResource(WeatherIcon.get(darksky.getCurrently().getIcon()));
                }
                catch (Throwable e)
                {
                    Log.e(e.getMessage());
                }
                Log.v("--- End ---");
            }
        });
    }
    private Integer getInt(Double paramDouble)
    {
        return (int)Math.round(paramDouble);
    }
}
