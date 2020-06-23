package com.thoreaudesign.weatheroutdoors.livedata;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.aws.WeatherServiceClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel implements LifecycleObserver
{
    private static final long CACHE_LIFE_MILLIS = 60 * 60 * 1000;

    private WeatherLiveData<WeatherDataObservable> weatherDataObservable;
    private Cache cache;
    private String lat;
    private String lon;

    public WeatherViewModel()
    {
        this.weatherDataObservable = new WeatherLiveData<>();
    }

    //<editor-fold desc="/** Getters **/">
    public Cache getCache()
    {
        return cache;
    }

    public WeatherLiveData<WeatherDataObservable> getWeatherDataObservable()
    {
        if(weatherDataObservable == null)
        {
            weatherDataObservable = new WeatherLiveData<>();
            weatherDataObservable.setValue(new WeatherDataObservable());
        }
        return weatherDataObservable;
    }
    //</editor-fold>

    //<editor-fold desc="/** Setters **/">
    public void setCache(Cache cache)
    {
        this.cache = cache;
    }

    public void setLatLon(String lat, String lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    public void setWeatherData(String cacheData)
    {
        if(this.weatherDataObservable == null)
        {
            this.weatherDataObservable = new WeatherLiveData<>();
        }
        WeatherDataObservable weatherDataObservable = new WeatherDataObservable();
        weatherDataObservable.setWeatherData(cacheData);
        this.weatherDataObservable.setValue(weatherDataObservable);
    }
    //</editor-fold>

    //<editor-fold desc="/** Network Calls **/">
    public void getWeatherDataFromAWS()
    {
        if(weatherDataObservable == null)
        {
            weatherDataObservable = new WeatherLiveData<>();
            weatherDataObservable.setValue(new WeatherDataObservable());
        }

        retriveWeatherData();
    }

    public void retriveWeatherData()
    {
        WeatherServiceClient.getInstance()
                .getWeatherData(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<WeatherData>()
                           {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d)
                               {
                                   Log.v("-- Begin --");
                                   Log.v("-- End --");
                               }

                               @Override
                               public void onNext(@io.reactivex.rxjava3.annotations.NonNull WeatherData response)
                               {
                                   Log.v("-- Begin --");
                                   WeatherDataObservable weatherDataObservable = new WeatherDataObservable();
                                   weatherDataObservable.setWeatherData(response);
                                   WeatherViewModel.this.getWeatherDataObservable().setValue(weatherDataObservable);
                                   WeatherViewModel.this.cache.setData(response.toString());
                                   WeatherViewModel.this.cache.write();
                                   Log.v("-- End --");
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e)
                               {
                                   Log.v("-- Begin --");
                                   Log.e(e.getMessage());
                                   Log.v("-- End --");
                               }

                               @Override
                               public void onComplete()
                               {
                                   Log.v("-- Begin --");
                                   Log.v("-- End --");
                               }
                           }
                );
    }
    //</editor-fold>
}
