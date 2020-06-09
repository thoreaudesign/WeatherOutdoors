package com.thoreaudesign.weatheroutdoors.fragments;

import com.thoreaudesign.weatheroutdoors.cache.CacheSingleton;

public interface IWeatherFragment
{
    void update();
    void setWeatherCacheHandler(CacheSingleton cacheSingleton);
}
