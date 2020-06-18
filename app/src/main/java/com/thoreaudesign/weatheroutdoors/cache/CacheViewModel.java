package com.thoreaudesign.weatheroutdoors.cache;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.serialization.WeatherDataResponse;

public class CacheViewModel extends ViewModel implements LifecycleObserver
{
    private static final long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    private CacheMutableLiveData<Cache> cache;

    public CacheViewModel(Cache cache)
    {
        getCache().setValue(cache);
    }

    private Cache getCacheData()
    {
        return this.cache.getValue();
    }

    public CacheMutableLiveData<Cache> getCache()
    {
        if(cache == null)
        {
            cache = new CacheMutableLiveData<>();
        }

        return cache;
    }

    public void serialize()
    {
        getCacheData().setData(getCacheData().weatherDataResponse.toString());
        cache.setValue(getCacheData());
    }

    public void deserialize()
    {
        Gson gson = new Gson();

        WeatherDataResponse data = gson.fromJson(getCacheData().getData(), WeatherDataResponse.class);

        getCacheData().setWeatherDataResponse(data);

        cache.setValue(getCacheData());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private boolean readCache()
    {
        return cache.getValue().read();
    }

    private boolean isCacheEmpty()
    {
        boolean isEmpty = false;

        if(this.getCacheData().getData() == null)
        {
            Log.i("Cache is empty.");
            isEmpty = true;
        }
        else
        {
            Log.i("Cache is populated.");
        }

        return isEmpty;
    }

    private boolean isCacheExpired()
    {
        boolean isExpired = false;

        long now = System.currentTimeMillis();
        long lastModified = this.getCacheData().getFile().lastModified();

        Log.v("Current time: " + now);
        Log.v("Last modified: " + lastModified);
        Log.v("Fileize: " + this.getCacheData().getFile().length());

        if (now - lastModified > CACHE_LIFE_MILLIS)
        {
            Log.i("Cache is out-of-date.");
            isExpired = true;
        }
        else
        {
            Log.i("Cache is current.");
        }

        return isExpired;
    }

    public boolean isCacheOutdated()
    {
        boolean isOutdated = false;

        Log.i("Validating cache file...");
        Log.v("Cache file: " + this.getCacheData().getFile().toString());

        if(this.getCacheData().getFile().exists())
        {
            this.readCache();

            if (this.isCacheEmpty() || this.isCacheExpired())
            {
                isOutdated = true;
            }
            else
            {
                Log.i("Cache is up-to-date.");
            }
        }
        else
        {
            isOutdated = true;
        }

        return isOutdated;
    }

    public void populateCache(WeatherDataResponse cacheData)
    {
        this.getCacheData().setData(cacheData);

        if (this.getCacheData().write())
        {
            serialize();
            Log.i("Lambda response succeeeded. Updated cache with latest data.");
        }
        else
        {
            Log.w("Failed to write cache data.");
        }
    }

    public void populateCache(String lambdaResponse)
    {
        this.getCacheData().setData(lambdaResponse);

        if (this.getCacheData().write())
        {
            deserialize();
            Log.i("Lambda response succeeeded. Updated cache with latest data.");
        }
        else
        {
            Log.w("Failed to write cache data.");
        }
    }
}
