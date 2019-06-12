package com.thoreaudesign.weatheroutdoors.parsers;

public class DarkskyParser implements Parser
{
    private String data;

    public DarkskyParser() {}

    public DarkskyParser(String data)
    {
        this.data = data;
    }

    @Override
    public String getData()
    {
        return this.data;
    }

    @Override
    public void setData(String data)
    {
        this.data = data;
    }
}
