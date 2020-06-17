package com.thoreaudesign.weatheroutdoors.aws;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IWeatherService
{
    String BASE_URL = "https://ul5bddlgti.execute-api.us-east-1.amazonaws.com/dev/";

    @GET("data?lat={lat}&lon={lon}")
    Call<JSONObject> getWeatherData(@Path("lat") int lat, @Path("lon") int lon);
}
