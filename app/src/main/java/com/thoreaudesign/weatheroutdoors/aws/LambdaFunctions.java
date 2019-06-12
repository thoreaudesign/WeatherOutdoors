package com.thoreaudesign.weatheroutdoors.aws;

public class LambdaFunctions
{
    public static String[] getFunctionNames()
    {
        String[] names = new String[4];

        names[0] = "darksky";
        names[1] = "metocean";
        names[2] = "stormglass";
        names[3] = "stormglass_astro";

        return names;
    }
}
