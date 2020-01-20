package com.thoreaudesign.weatheroutdoors;

public class WeatherIcon
{
    public static Integer get(String iconText)
    {
        switch (iconText)
        {
            case "clear-day":
            case "clear-night":
                return R.mipmap.sunny_foreground;
            case "rain":
                return R.mipmap.rainy_foreground;
            case "snow":
                return R.mipmap.snowy_foreground;
            case "sleet":
                return R.mipmap.sleet_foreground;
            case "wind":
                return R.mipmap.windy_foreground;
            case "fog":
                return R.mipmap.foggy_foreground;
            case "cloudy":
                return R.mipmap.cloudy_foreground;
            case "partly-cloudy-day":
            case "partly-cloudy-night":
                return R.mipmap.partly_cloudy_foreground;
            case "hail":
            case "thunderstorm":
            case "tornado":
                return R.mipmap.thunderstorms_foreground;
            default:
                throw new IllegalStateException("Unexpected value: " + iconText);
        }
    }
}
