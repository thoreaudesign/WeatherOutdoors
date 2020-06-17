package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.Compass;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.cache.Cache;
import com.thoreaudesign.weatheroutdoors.cache.CacheViewModel;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class WeatherSummaryFragment extends WeatherFragmentBase
{
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
        final View view = mView;
        super.onViewCreated(view, savedInstanceState);
        CacheViewModel model = new ViewModelProvider(requireActivity()).get(CacheViewModel.class);
        model.getCacheLive().observe(getViewLifecycleOwner(), new Observer<Cache>()
        {
            @Override
            public void onChanged(Cache cache)
            {
                Log.v("--- Begin ---");
                try
                {
                    Darksky darksky = WeatherSummaryFragment.this.parseDarkskyCacheData(cache.getData());

                    Integer temperature = getInt(darksky.getCurrently().getTemperature());
                    Integer apparentTemperature = getInt(darksky.getCurrently().getApparentTemperature());
                    Integer humidity = getInt(darksky.getCurrently().getHumidity() * 100.0D);
                    Integer dewPoint = getInt(darksky.getCurrently().getDewPoint());
                    Integer windSpeed = getInt(darksky.getCurrently().getWindSpeed());
                    Integer windDirection = getInt(darksky.getCurrently().getWindBearing());
                    String summary = darksky.getCurrently().getSummary();

                    String temperatureText = temperature.toString() + "F";
                    String apparentTemperatureText = "Feels like " + apparentTemperature.toString() + "F";
                    String windText = "Wind " + windSpeed.toString() + "mph " + Compass.getWindDirection(windDirection);
                    String humidityText = "Humidity " + humidity + "%";
                    String dewPointText = "Dew Point " + dewPoint.toString() + "F";

                    TextView tvTemperature = view.findViewById(R.id.current_degrees);
                    TextView tvApparentTemperature = view.findViewById(R.id.current_apparent_temp);
                    TextView tvHumidity = view.findViewById(R.id.current_humidity);
                    TextView tvDewPoint = view.findViewById(R.id.current_dewpoint);
                    TextView tvWind = view.findViewById(R.id.current_wind);
                    TextView tvSummary = view.findViewById(R.id.current_description);
                    ImageView weatherImage = view.findViewById(R.id.current_icon);

                    tvTemperature.setText(temperatureText);
                    tvApparentTemperature.setText(apparentTemperatureText);
                    tvHumidity.setText(humidityText);
                    tvDewPoint.setText(dewPointText);
                    tvWind.setText(windText);
                    tvSummary.setText(summary);
                    weatherImage.setImageResource(WeatherIcon.get(darksky.getCurrently().getIcon()));

                }
                catch (Throwable e)
                {
                    Log.e(e.getMessage());
                }
                Log.v("--- End ---");
            }
        });
    }
    private Integer getInt(Double paramDouble)
    {
        return (int)Math.round(paramDouble);
    }
}
