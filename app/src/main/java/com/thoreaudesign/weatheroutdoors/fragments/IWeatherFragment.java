package com.thoreaudesign.weatheroutdoors.fragments;

import com.thoreaudesign.weatheroutdoors.CacheAdapter;

public interface IWeatherFragment
{
    void update();
    void setWeatherCacheHandler(CacheAdapter cacheAdapter);
}
