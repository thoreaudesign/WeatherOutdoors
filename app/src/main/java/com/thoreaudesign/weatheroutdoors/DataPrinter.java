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

    public static String printTemperatureF(Double d)
    {
        return getInt(d).toString() + "F";
    }

    public static String printApparentTemperatureF(Double d)
    {
        return "Feels like " + printTemperatureF(d);
    }

    public static String printWindSpeedAndDir(Double speed, Double bearing)
    {
        return "Wind " + printSpeedMPH(speed) + " " + Compass.getWindDirection(getInt(bearing));
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
        return getInt(d).toString() + " in";
    }

    public static String printSpeedMPH(Double d)
    {
        return getInt(d).toString() + " mph";
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

    public static String printEpochAsDateTime(Double epoch)
    {
        long longTime = Double.valueOf(epoch).longValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
        Instant instant = Instant.ofEpochSecond(longTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,ZoneId.systemDefault());
        return formatter.format(zonedDateTime);
    }
}
