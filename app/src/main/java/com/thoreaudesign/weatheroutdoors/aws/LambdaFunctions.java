package com.thoreaudesign.weatheroutdoors.aws;

import java.lang.reflect.Field;

public class LambdaFunctions
{
    public static final String darksky = "darksky";
    public static final String metocean = "metocean";
    public static final String stormglass = "stormglass";
    public static final String stormglass_astro = "stormglass_astro";

    public Field[] getAll()
    {
        return this.getClass().getDeclaredFields();
    }
}
