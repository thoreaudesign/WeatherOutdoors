package com.thoreaudesign.weatheroutdoors.livedata;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Stormglass.Stormglass;
import com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro.StormglassAstro;

public class WeatherData
{
    @SerializedName("darksky")
    public Darksky darksky;

    @SerializedName("stormglass")
    public Stormglass stormglass;

    @SerializedName("stormglass_astro")
    public StormglassAstro stormglassAstro;

    @NonNull
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
