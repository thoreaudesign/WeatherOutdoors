package com.thoreaudesign.weatheroutdoors.cache;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoreaudesign.weatheroutdoors.Log;

public class CacheViewModel extends ViewModel
{
    private static final long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    MutableLiveData<Cache> cache;

    public CacheViewModel(Cache cache)
    {
        getCacheLive().setValue(cache);
        loadCacheData();
    }

    private Cache getCache()
    {
        return this.cache.getValue();
    }

    public MutableLiveData<Cache> getCacheLive()
    {
        if(cache == null)
        {
            cache = new MutableLiveData<>();
        }

        return cache;
    }

    private boolean loadCacheData()
    {
        return cache.getValue().read();
    }

    private boolean isCacheEmpty()
    {
        boolean isEmpty = false;

        if(this.getCache().getData() == null)
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
        long lastModified = this.getCache().getFile().lastModified();

        Log.v("Current time: " + now);
        Log.v("Last modified: " + lastModified);
        Log.v("Fileize: " + this.getCache().getFile().length());

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
        Log.v("Cache file: " + this.getCache().getFile().toString());

        if(this.getCache().getFile().exists())
        {
            this.loadCacheData();

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

    public void populateCache(String lambdaResponse)
    {
        this.getCache().setData(lambdaResponse);

        if (this.getCache().write())
        {
            Log.i("Lambda response succeeeded. Updated cache with latest data.");
        }
        else
        {
            Log.w("Failed to write cache data.");
        }
    }

    public void setLastModified(long time)
    {
        this.getCache().getFile().setLastModified(time);
    }

    public String getCacheData()
    {
        return this.getCache().getData();
    }

    public void deleteCache()
    {
        this.getCache().delete();
    }
}
