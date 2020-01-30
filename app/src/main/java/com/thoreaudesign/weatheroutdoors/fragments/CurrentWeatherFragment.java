package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.Compass;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.NavigationHost;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class CurrentWeatherFragment extends NavigationHost implements IWeatherFragment
{
    private Darksky data;

    public View layout;

    private Integer getInt(Double paramDouble)
    {
        return (int)Math.round(paramDouble);
    }

    public static CurrentWeatherFragment newInstance(String cacheDir)
    {
        Log.v("--- Begin ---");
        Log.v("Recieved value of cachDir: " + cacheDir);
        CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();

        Bundle bundle = new Bundle();
        bundle.putString("cacheDir", cacheDir);

        currentWeatherFragment.setArguments(bundle);

        Log.v("--- End ---");

        return currentWeatherFragment;
    }

    private void setCurrentWeather()
    {
        Log.v("--- Begin ---");
        Integer temperature = getInt(this.data.getCurrently().getTemperature());
        Integer apparentTemperature = getInt(this.data.getCurrently().getApparentTemperature());
        Integer humidity = getInt(this.data.getCurrently().getHumidity() * 100.0D);
        Integer dewPoint = getInt(this.data.getCurrently().getDewPoint());
        Integer windSpeed = getInt(this.data.getCurrently().getWindSpeed());
        Integer windDirection = getInt(this.data.getCurrently().getWindBearing());
        String summary = this.data.getCurrently().getSummary();

        String temperatureText = temperature.toString() + "F";
        String apparentTemperatureText = "Feels like " + apparentTemperature.toString() + "F";
        String windText = "Wind " + windSpeed.toString() + "mph " + Compass.getWindDirection(windDirection);
        String humidityText = "Humidity " + humidity + "%";
        String dewPointText = "Dew Point " + dewPoint.toString() + "F";

        TextView tvTemperature = this.layout.findViewById(R.id.current_degrees);
        TextView tvApparentTemperature = this.layout.findViewById(R.id.current_apparent_temp);
        TextView tvHumidity = this.layout.findViewById(R.id.current_humidity);
        TextView tvDewPoint = this.layout.findViewById(R.id.current_dewpoint);
        TextView tvWind = this.layout.findViewById(R.id.current_wind);
        TextView tvSummary = this.layout.findViewById(R.id.current_description);
        ImageView weatherImage = this.layout.findViewById(R.id.current_icon);

        tvTemperature.setText(temperatureText);
        tvApparentTemperature.setText(apparentTemperatureText);
        tvHumidity.setText(humidityText);
        tvDewPoint.setText(dewPointText);
        tvWind.setText(windText);
        tvSummary.setText(summary);
        weatherImage.setImageResource(WeatherIcon.get(this.data.getCurrently().getIcon()));

        Log.v("--- End ---");
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        super.onActivityCreated(savedInstanceState);
        try
        {
            loadFragment();
        }
        catch (Exception e)
        {
            Log.e(e.getMessage());
        }
        Log.v("--- End ---");
    }

    private void loadFragment() throws Exception
    {
        Object args = this.getArguments();

        if(args == null)
        {
            throw new Exception("Unable to retrieve cache - missing arguments from bundle.");
        }
        else
        {
            this.data = hydrate(getArguments());

            if (this.data == null)
            {
                Log.w("Failed to update fragment. Cache data null.");
                return;
            }

            this.setCurrentWeather();
        }
    }

    private Darksky hydrate(Bundle bundle)
    {
        Log.v("--- Begin ---");

        Cache cache = new Cache(bundle.getString("cacheDir"));

        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        String json = null;

        try
        {
            json = cache.getSection(ServiceName.DARKSKY.toLower());
        }
        catch (RuntimeException runtimeException)
        {
            cache.delete();
            Log.e("An error occurred retrieving cache data... Deleted existing cache.");
        }

        Log.v("--- End ---");

        return gson.fromJson(json, Darksky.class);
    }

    public void onCreate(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        super.onCreate(savedInstanceState);
        Log.v("--- End ---");
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        this.layout = paramLayoutInflater.inflate(R.layout.fragment_current_weather, container, false);
        Log.v("--- End ---");
        return this.layout;
    }

    public void update()
    {
        Log.v("--- Begin ---");
        try
        {
            loadFragment();
        }
        catch (Exception e)
        {
            Log.e(e.getMessage());
        }
        Log.v("--- End ---");
    }
}
