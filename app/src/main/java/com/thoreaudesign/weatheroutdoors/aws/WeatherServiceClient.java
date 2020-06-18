package com.thoreaudesign.weatheroutdoors.aws;

import androidx.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.serialization.WeatherData;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceClient
{
    private static final String BASE_URL = "https://ul5bddlgti.execute-api.us-east-1.amazonaws.com/";
    private static WeatherServiceClient instance;
    private IWeatherService weatherService;

    private WeatherServiceClient()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherServiceClient.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        weatherService = retrofit.create(IWeatherService.class);
    }

    public static WeatherServiceClient getInstance()
    {
        if (instance == null)
        {
            instance = new WeatherServiceClient();
        }
        return instance;
    }

    public Observable<WeatherData> getWeatherData(@NonNull String lat, @NonNull String lon)
    {
        return weatherService.getWeatherData(lat, lon);
    }
}
