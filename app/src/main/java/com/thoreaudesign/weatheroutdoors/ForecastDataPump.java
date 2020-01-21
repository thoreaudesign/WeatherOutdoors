package com.thoreaudesign.weatheroutdoors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ForecastDataPump
{
    public static LinkedHashMap<String, List<String>> getData()
    {
        LinkedHashMap<String, List<String>> forecastData = new LinkedHashMap<String, List<String>>();

        List<String> hourly = new ArrayList<String>();
        for (Integer i = 1; i <= 24; i++)
        {
            hourly.add("Hour " + i.toString());
        }

        List<String> tenDay = new ArrayList<String>();
        for(Integer i = 1; i <= 10; i++)
        {
            tenDay.add("Day " + i.toString());
        }

        forecastData.put("Hourly Forecast", hourly);
        forecastData.put("10-Day Forecast", tenDay);

        return forecastData;
    }
}