package com.thoreaudesign.weatheroutdoors.livedata;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.serialization.WeatherData;

public class WeatherViewModel extends ViewModel implements LifecycleObserver
{
    private static final long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    private WeatherMutableLiveData<WeatherData> weatherData;

    public void setWeatherData(WeatherData weatherData)
    {
        this.weatherData.setValue(weatherData);
    }

    public void setWeatherData(String cacheData)
    {
        Gson gson = new Gson();

        weatherData.setValue(
            gson.fromJson(cacheData, WeatherData.class));
    }

    public WeatherData getWeatherData()
    {
        return weatherData.getValue();
    }

    public WeatherMutableLiveData<WeatherData> getWeatherMutableLiveData()
    {
        return weatherData;
    }
}
