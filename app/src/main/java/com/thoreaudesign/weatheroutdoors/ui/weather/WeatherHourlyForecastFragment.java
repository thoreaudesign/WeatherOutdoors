package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.thoreaudesign.weatheroutdoors.DataPrinter;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.R;
import com.thoreaudesign.weatheroutdoors.WeatherIcon;
import com.thoreaudesign.weatheroutdoors.databinding.FragmentHourlyForecastBinding;
import com.thoreaudesign.weatheroutdoors.fragments.WeatherFragmentBase;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherDataObservable;
import com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.util.List;

public class WeatherHourlyForecastFragment extends WeatherFragmentBase
{
    private WeatherViewModel weatherViewModel;
    private FragmentHourlyForecastBinding homeSummaryBinding;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onCreateView(inflater, container, savedInstanceState);
        context = this.getContext();
        weatherViewModel = new ViewModelProvider(requireActivity()).get(com.thoreaudesign.weatheroutdoors.livedata.WeatherViewModel.class);
        homeSummaryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hourly_forecast, container, false);
        homeSummaryBinding.setWeatherDataObservable(weatherViewModel.getWeatherDataObservable().getValue());
        Log.v("-- End --");
        return homeSummaryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View mView, @Nullable Bundle savedInstanceState)
    {
        Log.v("-- Begin --");
        super.onViewCreated(mView, savedInstanceState);
        weatherViewModel.getWeatherDataObservable().observe(getViewLifecycleOwner(), new Observer<WeatherDataObservable>()
        {
            @Override
            public void onChanged(WeatherDataObservable weatherDataObservable)
            {
                Log.v("--- Begin ---");
                try
                {
                    populateViews(weatherDataObservable);
                }
                catch (Throwable e)
                {
                    Log.e(e.getMessage());
                }
                Log.v("--- End ---");
            }
        });
        Log.v("-- End --");
    }

    @SuppressLint("SetTextI18n")
    private void populateViews(WeatherDataObservable weatherDataObservable)
    {
        Darksky darksky = weatherDataObservable.getWeatherData().darksky;
        LinearLayout outterLayout = homeSummaryBinding.getRoot().findViewById(R.id.hourly_forecast_linearLayout);
        List<DatumHourly> datumHourlyList = darksky.getHourly().getData();

        for(int i = 1; i <= 25; i++)
        {
            DatumHourly hourlyData = datumHourlyList.get(i);
            ConstraintLayout innerLayout = (ConstraintLayout)LayoutInflater.from(context).inflate(R.layout.forecast_list_item_container, null);
            ((TextView)innerLayout.findViewById(R.id.time)).setText(DataPrinter.printEpochAsDateTime(hourlyData.getTime()));
            ((TextView)innerLayout.findViewById(R.id.temperature)).setText(DataPrinter.printTemperatureF(hourlyData.getTemperature()));
            ((TextView)innerLayout.findViewById(R.id.apparent_temperature)).setText(DataPrinter.printApparentTemperatureF(hourlyData.getApparentTemperature()));
            ((TextView)innerLayout.findViewById(R.id.dewpoint)).setText(DataPrinter.printDewpoint(hourlyData.getDewPoint()));
            ((ImageView)innerLayout.findViewById(R.id.imageView)).setImageResource(WeatherIcon.get(hourlyData.getIcon()));
            ((TextView)innerLayout.findViewById(R.id.cloudCover)).setText(DataPrinter.printCloudCover(hourlyData.getCloudCover()));
            ((TextView)innerLayout.findViewById(R.id.summary)).setText(DataPrinter.printSummary(hourlyData.getSummary()));
            ((TextView)innerLayout.findViewById(R.id.pressure)).setText(DataPrinter.printPressure(hourlyData.getPressure()));
            ((TextView)innerLayout.findViewById(R.id.precipProbability)).setText("Chance of rain: " + DataPrinter.printPercentage(hourlyData.getPrecipProbability()));
            ((TextView)innerLayout.findViewById(R.id.humidity)).setText(DataPrinter.printHumidity(hourlyData.getHumidity()));
            ((TextView)innerLayout.findViewById(R.id.wind)).setText(DataPrinter.printWindSpeedAndDir(hourlyData.getWindSpeed(), hourlyData.getWindBearing()));
            ((TextView)innerLayout.findViewById(R.id.windGust)).setText(DataPrinter.printSpeedMPH(hourlyData.getWindGust()));
            ((TextView)innerLayout.findViewById(R.id.uvIndex)).setText(DataPrinter.getInt(hourlyData.getUvIndex()).toString());
            ((TextView)innerLayout.findViewById(R.id.ozone)).setText(DataPrinter.getInt(hourlyData.getOzone()).toString());

            if(i == 25)
            {
                innerLayout.setPadding(0, 0,0,175);
            }
            outterLayout.addView(innerLayout);
        }
    }
}
