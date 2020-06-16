package com.thoreaudesign.weatheroutdoors.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel
{

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mTextHourly;

    public WeatherViewModel()
    {
        mText = new MutableLiveData<>();
        mTextHourly = new MutableLiveData<>();
        mText.setValue("This is weather summary fragment.");
        mTextHourly.setValue("This is hourly forecast fragment.");
    }

    public LiveData<String> getText()
    {
        return mText;
    }

    public LiveData<String> getHourlyText()
    {
        return mTextHourly;
    }
}