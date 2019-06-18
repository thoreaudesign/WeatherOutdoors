package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class DailyForecastFragment extends Fragment implements WeatherFragment
{
    private Darksky data;
    public View layout;

    @Override
    public void update()
    {
        this.data = this.hydrate(this.getArguments());

        if(data == null)
        {
            Log.e("Failed to update fragment. Cache data null.");
        }
        else if(this.layout == null)
        {
            Log.e("Failed to update fragment. Layout unavailable.");
        }
        else
        {
            TextView description = this.layout.findViewById(R.id.current_description);
            description.setText(this.data.getCurrently().getSummary());
            TextView degrees = this.layout.findViewById(R.id.current_degrees);
            degrees.setText(Double.toString(this.data.getCurrently().getTemperature()) + "\u00B0 F");

            Log.v("Current summary: " + this.data.getCurrently().getSummary());
            Log.v("Current icon: " + this.data.getCurrently().getIcon());

            ImageView icon = this.layout.findViewById(R.id.current_icon);
            icon.setImageResource(this.getIcon(this.data.getCurrently().getIcon()));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    private Darksky hydrate(Bundle bundle)
    {
        Cache cache = new Cache(bundle.getString("cacheDir"));

        String json = cache.getSection(ServiceName.DARKSKY.toLower());

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json, Darksky.class);
    }

    public int getIcon(String iconText)
    {
        int resource = 0;

        switch(iconText)
        {
            case "partly-cloudy-day":
            case "partly-cloudy-night":
                resource = R.mipmap.mostly_cloudy_foreground;
                break;

            case "clear-day":
            case "clear-night":
                resource = R.mipmap.sunny_foreground;
                break;

            case "rain":
                resource = R.mipmap.rainy_foreground;
                break;

            case "snow":
                resource = R.mipmap.snowy_foreground;
                break;

            case "sleet":
                resource = R.mipmap.sleet_foreground;
                break;

            case "wind":
                resource = R.mipmap.windy_foreground;
                break;

            case "fog":
                resource = R.mipmap.foggy_foreground;
                break;

            case "cloudy":
                resource = R.mipmap.cloudy_foreground;
                break;

            case "hail":
            case "thunderstorm":
            case "tornado":
                resource = R.mipmap.thunderstorms_foreground;
                break;
        }

        return resource;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.layout = inflater.inflate(R.layout.daily_forecast_fragment, container, false);
        this.update();
        return this.layout;

    }
    public static DailyForecastFragment newInstance(String cacheDir)
    {
        DailyForecastFragment fragment = new DailyForecastFragment();

        Bundle bundle = new Bundle();
        bundle.putString("cacheDir", cacheDir);

        fragment.setArguments(bundle);

        return fragment;
    }
}
