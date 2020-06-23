package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.DataPrinter;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentWeatherTodayBinding;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherDataObservable;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Currently;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;


public class WeatherTodayFragment extends Fragment
{
    private WeatherViewModel weatherViewModel;
    private FragmentWeatherTodayBinding homeSummaryBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onCreateView(inflater, container, savedInstanceState);
        weatherViewModel = new ViewModelProvider(requireActivity()).get(com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel.class);
        homeSummaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_today, container, false);
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
                    StackTraceElement[] stackTrace = e.getStackTrace();

                    for(int i = 0; i < stackTrace.length; i++)
                    {
                        Log.e(stackTrace[i].toString());
                    }
                }
                Log.v("--- End ---");
            }
        });
        Log.v("--- End ---");
    }

    @SuppressLint("SetTextI18n")
    private void populateViews(WeatherDataObservable weatherDataObservable)
    {
        Darksky darksky = weatherDataObservable.getWeatherData().darksky;
        Currently currently = darksky.getCurrently();
        String summary = currently.getSummary();

        homeSummaryBinding.currentDegrees.setText(DataPrinter.printTemperatureF(currently.getTemperature()));
        homeSummaryBinding.currentApparentTemp.setText(DataPrinter.printApparentTemperatureF(currently.getApparentTemperature()));
        homeSummaryBinding.currentHumidity.setText(DataPrinter.printHumidity(currently.getHumidity()));
        homeSummaryBinding.currentDewpoint.setText(DataPrinter.printDewpoint(currently.getDewPoint()));
        homeSummaryBinding.currentWind.setText(DataPrinter.printWind(currently.getWindSpeed(), currently.getWindBearing()));
        homeSummaryBinding.currentDescription.setText(summary);
        homeSummaryBinding.currentIcon.setImageResource(WeatherIcon.get(currently.getIcon()));
        homeSummaryBinding.nearestStorm.setText(DataPrinter.printNearestStorm(currently.getNearestStormDistance(), currently.getNearestStormBearing()));
        homeSummaryBinding.precipProbability.setText(DataPrinter.printChanceOfRain(currently.getPrecipProbability()));
        homeSummaryBinding.precipIntensity.setText(DataPrinter.printPrecipitationIntensity(currently.getPrecipIntensity()));
        homeSummaryBinding.cloudCover.setText(DataPrinter.printCloudCover(currently.getPrecipIntensity()));
        homeSummaryBinding.visibility.setText(DataPrinter.printVisibility(currently.getVisibility()));
        homeSummaryBinding.uvIndex.setText(DataPrinter.printUv(currently.getUvIndex()));
        homeSummaryBinding.ozone.setText(DataPrinter.printOzone(currently.getOzone()));
    }
}
