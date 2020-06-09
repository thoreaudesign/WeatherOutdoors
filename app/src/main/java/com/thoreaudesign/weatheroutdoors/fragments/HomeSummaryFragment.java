package com.thoreaudesign.weatheroutdoors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.Compass;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class HomeSummaryFragment extends WeatherFragmentBase
{
    private View layout;
    private Darksky data;

    public static HomeSummaryFragment newInstance(String cacheData)
    {
        Log.v("--- Begin ---");
        HomeSummaryFragment currentWeatherFragment = new HomeSummaryFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Cache.BUNDLE_KEY_DATA, cacheData);

        currentWeatherFragment.setArguments(bundle);

        Log.v("--- End ---");

        return currentWeatherFragment;
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

        this.layout = paramLayoutInflater.inflate(R.layout.fragment_home_summary, container, false);

        Log.v("--- End ---");

        return this.layout;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        Log.v("--- Begin ---");
        super.onActivityCreated(savedInstanceState);

        Bundle newBundle = getArguments();
        String cacheData = newBundle.getString(Cache.BUNDLE_KEY_DATA);

        try
        {
            this.data = parseDarkskyCacheData(ServiceName.DARKSKY, cacheData);

            if(this.data == null)
            {
                throw new NullPointerException("Darksky cache is empty.");
            }
            else
            {
                updateWeatherData();
            }
        }
        catch (Throwable exception)
        {
            Log.e("Failed to parse Darksky data from cache.");
            Log.e(exception.getMessage());
        }

        Log.v("--- End ---");
    }

    void populateLayoutWithData()
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

    private Integer getInt(Double paramDouble)
    {
        return (int)Math.round(paramDouble);
    }
}
