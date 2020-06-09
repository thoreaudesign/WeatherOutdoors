package com.thoreaudesign.weatheroutdoors.cache;

import com.thoreaudesign.weatheroutdoors.Log;

public class CacheManager
{
    private static final long CACHE_LIFE_MILLIS = 30 * 60 * 1000;

    private Cache cache;

    public CacheManager(Cache cache)
    {
        this.cache = cache;
    }

    private boolean isCacheEmpty()
    {
        boolean isEmpty = true;

        if(this.cache.getData() != null)
        {
            Log.v("Cache is empty.");
            isEmpty = false;
        }

        return isEmpty;
    }

    private boolean isCacheExpired()
    {
        boolean isExpired = true;

        long now = System.currentTimeMillis();
        long lastModified = this.cache.getFile().lastModified();

        Log.v("Current time: " + now);
        Log.v("Last modified: " + lastModified);
        Log.v("Fileize: " + this.cache.getFile().length());

        if (now - lastModified > CACHE_LIFE_MILLIS)
        {
            Log.i("Cache is out-of-date.");
            isExpired = true;
        } else
        {
            Log.i("Cache is current.");
            isExpired = false;
        }

        return isExpired;
    }

    public boolean isCacheOutdated()
    {
        boolean isOutdated = false;

        if(this.cache.getFile().exists())
        {
            if (this.isCacheEmpty() || this.isCacheExpired())
            {
                isOutdated = true;
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
        cache.setData(lambdaResponse);

        if (cache.write())
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
        this.cache.getFile().setLastModified(time);
    }

    public String getCacheData()
    {
        return this.cache.getData();
    }
}

//<editor-fold desc="/** TO-DO **/">
    /** This needs to be part of the implementation, not definition.
     * Cache should not handle bundle, only strings.
     */
     /*
    private String getCacheDir()
    {
        Log.v("--- Begin ---");

        if(cacheDir == null)
        {
            if(bundle == null)
            {
                throw new NullPointerException("Bundle property is not set - call setBundle(bundle).");
            }
            else
            {
                Log.v("Bundle contents: " + bundle.toString());

                try
                {
                    cacheDir = bundle.getString(Cache.BUNDLE_KEY_DIR);
                    Log.v("Cache directory: " + cacheDir);
                }
                catch(NullPointerException e)
                {
                    throw new NullPointerException("Unable to set cache directory - bunlde is missing value \"" + Cache.BUNDLE_KEY_DIR + "\"");
                }
            }
        }

        Log.v("--- End ---");

        return cacheDir;
    }
      */
    //</editor-fold>

