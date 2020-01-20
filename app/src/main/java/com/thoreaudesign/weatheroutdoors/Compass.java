package com.thoreaudesign.weatheroutdoors;

public class Compass
{
    public static String getWindDirection(Integer paramInteger)
    {
        switch (Integer.valueOf((int) Math.round(paramInteger.intValue() / 22.5D)).intValue())
        {
            default:
                return "N";
            case 15:
                return "NNW";
            case 14:
                return "NW";
            case 13:
                return "WNW";
            case 12:
                return "W";
            case 11:
                return "WSW";
            case 10:
                return "SW";
            case 9:
                return "SSW";
            case 8:
                return "S";
            case 7:
                return "SSE";
            case 6:
                return "SE";
            case 5:
                return "ESE";
            case 4:
                return "E";
            case 3:
                return "ENE";
            case 2:
                return "NE";
            case 1:
                return "NNE";
            case 0:
                break;
        }
        return "N";
    }
}
