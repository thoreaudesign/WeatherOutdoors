package com.thoreaudesign.weatheroutdoors;

public class MarineRequest {
    private String lat;
    private String lon;

    public MarineRequest(String lat, String lon) {
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
