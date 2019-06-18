package com.thoreaudesign.weatheroutdoors.aws;

public enum ServiceName
{
    DARKSKY,
    STORMGLASS,
    STORMGLASSASTRO,
    METOCEAN;

    public String toLower()
    {
        if (this == this.STORMGLASSASTRO)
        {
            return "stormglass_astro";
        }
        else
        {
            return this.toString().toLowerCase();
        }
    }
}
