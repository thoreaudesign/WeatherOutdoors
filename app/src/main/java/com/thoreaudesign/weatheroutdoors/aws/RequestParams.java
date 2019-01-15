package com.thoreaudesign.weatheroutdoors.aws;

public class RequestParams {
    private String lat;
    private String lon;

    public RequestParams(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
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
