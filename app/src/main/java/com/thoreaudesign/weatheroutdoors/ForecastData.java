package com.thoreaudesign.weatheroutdoors;

import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.util.LinkedHashMap;
import java.util.List;

public class ForecastData
{
    Darksky data;

    public ForecastData(Darksky data)
    {
        this.data = data;
    }

    public LinkedHashMap<String, List<DatumHourly>> getData()
    {
        LinkedHashMap<String, List<DatumHourly>> forecastData = new LinkedHashMap<String, List<DatumHourly>>();

        forecastData.put("Hourly Forecast", this.data.getHourly().getData());

        return forecastData;
    }
}