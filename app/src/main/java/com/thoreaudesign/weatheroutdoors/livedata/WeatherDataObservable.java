package com.thoreaudesign.weatheroutdoors.livedata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.BR;

public class WeatherDataObservable extends BaseObservable
{
    private WeatherData weatherData;

    @Bindable
    public WeatherData getWeatherData()
    {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData)
    {
        this.weatherData = weatherData;
        notifyPropertyChanged(BR.weatherDataObservable);
    }

    public void setWeatherData(String cacheData)
    {
        Gson gson = new Gson();

        weatherData = gson.fromJson(cacheData, WeatherData.class);
        notifyPropertyChanged(BR.weatherDataObservable);
    }
}
