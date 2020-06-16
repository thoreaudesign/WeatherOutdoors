package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.thoreaudesign.weatheroutdoors.Compass;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;

public class WeatherSummaryFragment extends WeatherFragmentBase
{
    private WeatherViewModel weatherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        return createView(R.layout.fragment_home_summary, inflater, container);
    }

    protected void populateLayout()
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
