package com.thoreaudesign.weatheroutdoors;

import android.app.Activity;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Darksky
{
    public JsonObject getDarkskyData(Activity activity)
    {
        CacheController cache = new CacheController(activity);

        String data = cache.read(LambdaFunctions.DARKSKY);

        JsonParser parser = new JsonParser();

        JsonObject darkskyJson = (JsonObject)parser.parse(data);

        return darkskyJson.getAsJsonObject("currently");
    }

    public String getWindData(JsonObject data)
    {
        return
                "Wind speed: " + data.get("windSpeed").getAsString() + "mph \n" +
                "Wind Direction: " + data.get("windBearing").getAsString() + "° \n" +
                "Wind Gust: " + data.get("windGust").getAsString() + "mph\n";
    }

    public String getWeatherData(JsonObject data)
    {
        return
                "Summary: " + data.get("summary").getAsString() + "\n" +
                "Icon: " + data.get("icon").getAsString() + "\n" +
                "Nearst Storm: " + data.get("nearestStormDistance").getAsString() + "mi\n" +
                "Nearst Storm Direction: " + data.get("nearestStormBearing").getAsString() + "°\n" +
                "Precipition Intensity: " + data.get("precipIntensity").getAsString() + "\n" +
                "Precipition Probability: " + data.get("precipProbability").getAsString() + "%\n" +
                "Current Temperature: " + data.get("temperature").getAsString() + "F\n" +
                "Apparent Temperature: " + data.get("apparentTemperature").getAsString() + "F\n" +
                "Dew Point: " + data.get("dewPoint").getAsString() + "F\n" +
                "Humidity: " + data.get("humidity").getAsString() + "%\n";
    }
}
