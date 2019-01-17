package com.thoreaudesign.weatheroutdoors;

import android.app.Activity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoreaudesign.weatheroutdoors.aws.LambdaFunctions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Darksky
{
    public String getSummaryData(Activity activity)
    {
        CacheController cache = new CacheController(activity);

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat hourFormat = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(hourFormat.format(date));

        String dataDarksky = cache.read(LambdaFunctions.darksky);
        String dataStormglass = cache.read(LambdaFunctions.stormglass);
        String dataStormglass_astro = cache.read(LambdaFunctions.stormglass_astro);

        JsonParser parser = new JsonParser();

        JsonObject darkskyJson = (JsonObject)parser.parse(dataDarksky);
        JsonObject stormglassJson = (JsonObject)parser.parse(dataStormglass);
        JsonObject stormglass_astroJson = (JsonObject)parser.parse(dataStormglass_astro);

        JsonObject darksky = darkskyJson.getAsJsonObject("currently");
        JsonObject stormglass = stormglassJson.getAsJsonArray("hours").get(hour).getAsJsonObject();
        JsonObject stormglass_astro = stormglass_astroJson.getAsJsonArray("days").get(1).getAsJsonObject();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        MoonPhase moon = new MoonPhase(calendar);

        DecimalFormat format = new DecimalFormat("#");
        format.setRoundingMode(RoundingMode.HALF_UP);
        String phasePercent = format.format(moon.getPhase()) + "%";

        String age = moon.getMoonAgeAsDays();

        DateTimeFormatter inputTimeParser = ISODateTimeFormat.dateTimeNoMillis();
        DateTimeFormatter inputMoonTimeParser = ISODateTimeFormat.dateTimeParser();

        DateTimeFormatter outputTime = DateTimeFormat.shortTime();

        DateTime aDawn = inputTimeParser.parseDateTime(stormglass_astro.get("astronomicalDawn").getAsString());
        DateTime nDawn = inputTimeParser.parseDateTime(stormglass_astro.get("nauticalDawn").getAsString());
        DateTime cDawn = inputTimeParser.parseDateTime(stormglass_astro.get("civilDawn").getAsString());
        DateTime sunrise = inputTimeParser.parseDateTime(stormglass_astro.get("sunrise").getAsString());
        DateTime sunset = inputTimeParser.parseDateTime(stormglass_astro.get("sunset").getAsString());
        DateTime cDusk = inputTimeParser.parseDateTime(stormglass_astro.get("civilDusk").getAsString());
        DateTime nDusk = inputTimeParser.parseDateTime(stormglass_astro.get("nauticalDusk").getAsString());
        DateTime aDusk = inputTimeParser.parseDateTime(stormglass_astro.get("astronomicalDusk").getAsString());
        DateTime moonrise = inputMoonTimeParser.parseDateTime(stormglass_astro.get("moonrise").getAsString());
        DateTime moonset = inputMoonTimeParser.parseDateTime(stormglass_astro.get("moonset").getAsString());

        String summary =
            "\nDaily Forecast \n" +
            "--------------------------\n" +
            "Wind speed: " + darksky.get("windSpeed").getAsString() + "mph \n" +
            "Wind Direction: " + darksky.get("windBearing").getAsString() + "° \n" +
            "Wind Gust: " + darksky.get("windGust").getAsString() + "mph\n" +
            "Summary: " + darksky.get("summary").getAsString() + "\n" +
            "Precipition Intensity: " + darksky.get("precipIntensity").getAsString() + "\n" +
            "Precipition Probability: " + darksky.get("precipProbability").getAsString() + "%\n" +
            "Current Temperature: " + darksky.get("temperature").getAsString() + "F\n" +
            "Apparent Temperature: " + darksky.get("apparentTemperature").getAsString() + "F\n" +
            "Humidity: " + darksky.get("humidity").getAsString() + "%\n" +
            "Pressure: " + darksky.get("pressure").getAsString() + "mb \n" +
            "\nWater Conditions \n" +
            "--------------------------\n" +
            "Tide (MLLW): " + stormglass.get("seaLevel").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "m\n" +
            "Water Temperature: " + stormglass.get("waterTemperature").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "C\n" +
            "Swell Direction: " + stormglass.get("swellDirection").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "°\n" +
            "Swell Height: " + stormglass.get("swellHeight").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "m\n" +
            "Swell Period: " + stormglass.get("swellPeriod").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "s\n" +
            "Wave Direction: " + stormglass.get("waveDirection").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "°\n" +
            "Wave Height: " + stormglass.get("waveHeight").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "m\n" +
            "Wave Period: " + stormglass.get("wavePeriod").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "s\n" +
            "Wind Wave Direction: " + stormglass.get("windWaveDirection").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "°\n" +
            "Wind Wave Height: " + stormglass.get("windWaveHeight").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "m\n" +
            "Wind Wave Period: " + stormglass.get("windWavePeriod").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString() + "s\n" +
            "\nAstronomical\n" +
            "--------------------------\n" +
            "Astronomical Dawn: " + outputTime.print(aDawn) + "\n" +
            "Nautical Dawn (First light): " + outputTime.print(nDawn) + "\n" +
            "Civil Dawn (Shooting light): " + outputTime.print(cDawn) + "\n" +
            "Sunrise: " + outputTime.print(sunrise) + "\n" +
            "Sunset: " +  outputTime.print(sunset) + "\n" +
            "Civil Dusk (End Shooting Light): " + outputTime.print(cDusk) + "\n" +
            "Nautical Dusk (Last Light): " +  outputTime.print(nDusk) + "\n" +
            "Astronomical Dusk: " +  outputTime.print(aDusk) + "\n" +
            "Moonrise: " +  outputTime.print(moonrise) + "\n" +
            "Moonset: " +  outputTime.print(moonset) + "\n" +
            "Moon Phase (API): " + stormglass_astro.get("moonFraction").getAsString() + "%\n" +
            "Moon Phase (Calc.): " + phasePercent + "\n" +
            "Moon Type: " + stormglass_astro.get("moonPhase").getAsJsonObject().get("current").getAsJsonObject().get("text") + "\n" +
            "Moon Age: " + age;

        return summary;
    }


    public String getWindData(JsonObject data)
    {
        return
                "Wind speed: " + data.get("windSpeed").getAsString() + "mph \n" +
                "Wind Direction: " + data.get("windBearing").getAsString() + "° \n" +
                "Wind Gust: " + data.get("windGust").getAsString() + "mph\n";
    }

    public String getMoonData()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        MoonPhase moon = new MoonPhase(calendar);

        DecimalFormat format = new DecimalFormat("#");
        format.setRoundingMode(RoundingMode.HALF_UP);
        String phasePercent = format.format(moon.getPhase()) + "%";

        String age = moon.getMoonAgeAsDays();
        String phaseName = moon.getPhaseName();

        return
            "Moon Phase: " + phasePercent + "\n" +
            "Moon Age: " + age + "\n" +
            "Moon Phase Index: " + phaseName + "\n";
    }

    public String getWeatherData(JsonObject data)
    {
        return
                "Summary: " + data.get("summary").getAsString() + "\n" +
//                "Icon: " + data.get("icon").getAsString() + "\n" +
//                "Nearst Storm: " + data.get("nearestStormDistance").getAsString() + "mi\n" +
//                "Nearst Storm Direction: " + data.get("nearestStormBearing").getAsString() + "°\n" +
                "Precipition Intensity: " + data.get("precipIntensity").getAsString() + "\n" +
                "Precipition Probability: " + data.get("precipProbability").getAsString() + "%\n" +
                "Current Temperature: " + data.get("temperature").getAsString() + "F\n" +
                "Apparent Temperature: " + data.get("apparentTemperature").getAsString() + "F\n" +
//                "Dew Point: " + data.get("dewPoint").getAsString() + "F\n" +
                "Humidity: " + data.get("humidity").getAsString() + "%\n";
    }

    public String getPressureData(JsonObject data)
    {
        return "Pressure: " + data.get("pressure").getAsString() + "mb \n";
    }

    public String getTideData(JsonObject data)
    {
        return
            "Tide Phase: Ebb \n" +
            "Tide Level: +/- 6ft \n" +
            "Tide Change: 6pm EST\n";
    }
}
