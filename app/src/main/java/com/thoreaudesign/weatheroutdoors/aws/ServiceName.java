package com.thoreaudesign.weatheroutdoors.aws;

public enum ServiceName
{
    DARKSKY,
    STORMGLASS,
    STORMGLASSASTRO,
    METOCEAN;

    public String toLower()
    {
        if (this == ServiceName.STORMGLASSASTRO)
        {
            return "stormglass_astro";
        }
        else
        {
            return this.toString().toLowerCase();
        }
    }

    public static boolean contains(String value)
    {
        for (ServiceName serviceName : ServiceName.values())
        {
            if (serviceName.name().equals(value))
            {
                return true;
            }
        }
        return false;
    }
}
