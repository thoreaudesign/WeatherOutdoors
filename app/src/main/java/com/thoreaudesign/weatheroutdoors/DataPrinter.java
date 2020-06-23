package com.thoreaudesign.weatheroutdoors;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DataPrinter
{
    public static Integer getInt(Double paramDouble)
    {
        return (int)Math.round(paramDouble);
    }

    public static String printEpochAsDateTime(Double epoch)
    {
        long longTime = Double.valueOf(epoch).longValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
        Instant instant = Instant.ofEpochSecond(longTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,ZoneId.systemDefault());
        return formatter.format(zonedDateTime);
    }

    public static String printTemperatureF(Double d)
    {
        return getInt(d).toString() + "F";
    }

    public static String printApparentTemperatureF(Double d)
    {
        return "Feels like " + printTemperatureF(d);
    }

    public static String printWind(Double speed, Double bearing)
    {
        return "Wind " + printSpeedMPH(speed) + " " + Compass.getCompassFromBearing(getInt(bearing));
    }

    public static String printWindGusts(Double d)
    {
        return "Gusts up to " + printSpeedMPH(d);
    }

    public static String printPercentage(Double d)
    {
        return getInt(d * 100).toString() + "%";
    }

    public static String printSummary(String s)
    {
        if(s.equals("partly-cloudy-day") || s.equals("partly-cloudy-night"))
            return "Partly Cloudy";
        else
            return s;
    }

    public static String printPressure(Double d)
    {
        return getInt(d).toString() + "in";
    }

    public static String printSpeedMPH(Double d)
    {
        return getInt(d).toString() + "mph";
    }

    public static String printDistanceMI(Double d)
    {
        return getInt(d).toString() + "mi";
    }

    public static String printHumidity(Double d)
    {
        return "Humidity: " + printPercentage(d);
    }

    public static String printDewpoint(Double d)
    {
        return "Dew point: " + printTemperatureF(d);
    }

    public static String printCloudCover(Double d)
    {
        return "Cloud cover: " + printPercentage(d);
    }

    public static String printUv(Double d)
    {
        return "UV Index: " + getInt(d);
    }

    public static String printOzone(Double d)
    {
        return "Ozone: " + getInt(d);
    }

    public static String printNearestStorm(Double distance, Double bearing)
    {
        return "Nearest storm: " + printDistanceMI(distance) + " " + Compass.getCompassFromBearing(getInt(bearing));
    }

    public static String printChanceOfRain(Double d)
    {
        return "Chance of rain: " + printPercentage(d);
    }

    public static String printPrecipitationIntensity(Double d)
    {
        return "Precipitation Intensity: " + getInt(d) + "in/hr";
    }

    public static String printVisibility(Double d)
    {
        return "Visibility: " + printDistanceMI(d);
    }
}
