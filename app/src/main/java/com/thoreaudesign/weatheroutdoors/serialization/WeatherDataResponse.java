package com.thoreaudesign.weatheroutdoors.serialization;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.Stormglass.Stormglass;
import com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro.StormglassAstro;

public class WeatherDataResponse
{
    @SerializedName("darksky")
    public Darksky darksky;

    @SerializedName("stormglass")
    public Stormglass stormglass;

    @SerializedName("stormglass_astro")
    public StormglassAstro stormglassAstro;

    public String toString()
    {
        return new Gson().toJson(this);
    }
}
