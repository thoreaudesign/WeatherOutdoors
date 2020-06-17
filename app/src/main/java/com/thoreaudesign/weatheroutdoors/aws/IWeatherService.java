package com.thoreaudesign.weatheroutdoors.aws;


import com.thoreaudesign.weatheroutdoors.serialization.WeatherDataResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService
{

    @GET("/dev/data")
    Observable<WeatherDataResponse> getWeatherData(@Query("lat") String lat, @Query("lon") String lon);
}
