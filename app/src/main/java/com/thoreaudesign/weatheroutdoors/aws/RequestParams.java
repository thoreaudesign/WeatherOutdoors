package com.thoreaudesign.weatheroutdoors.aws;

public class RequestParams
{
    private String lat;

    private String lon;

    public RequestParams(String paramString1, String paramString2)
    {
        this.lat = paramString1;
        this.lon = paramString2;
    }

    public String getLat()
    {
        return this.lat;
    }

    public String getLon()
    {
        return this.lon;
    }
}
