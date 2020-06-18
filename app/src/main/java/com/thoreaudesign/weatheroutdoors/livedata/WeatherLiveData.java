package com.thoreaudesign.weatheroutdoors.livedata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;

public class WeatherLiveData<T extends BaseObservable> extends MutableLiveData<T>
{
    @Override
    public void setValue(T value)
    {
        super.setValue(value);

        value.addOnPropertyChangedCallback(callback);
    }

    Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback()
    {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId)
        {
            setValue(getValue());
        }
    };
}
