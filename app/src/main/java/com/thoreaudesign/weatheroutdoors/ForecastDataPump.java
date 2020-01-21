package com.thoreaudesign.weatheroutdoors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForecastDataPump
{
    public static HashMap<String, List<String>> getData()
    {
        HashMap<String, List<String>> forecastData = new HashMap<String, List<String>>();

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