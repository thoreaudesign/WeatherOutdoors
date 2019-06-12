package com.thoreaudesign.weatheroutdoors.parsers;

public interface Parser
{
    String data = null;

    void setData(String jsonData);
    String getData();
}
