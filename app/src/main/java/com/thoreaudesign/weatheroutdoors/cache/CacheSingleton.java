package com.thoreaudesign.weatheroutdoors.cache;

public class CacheSingleton
{
    private static CacheSingleton instance = null;

    private CacheManager cacheManager;

    protected CacheSingleton()
    {
    }

    public void setCacheManager(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    public CacheManager getCacheManager()
    {
        return cacheManager;
    }

    public static CacheSingleton getInstance()
    {
        if (instance == null)
        {
            synchronized (CacheSingleton.class)
            {
                if (instance == null)
                {
                    instance = new CacheSingleton();
                }
            }
        }

        return instance;
    }
}

