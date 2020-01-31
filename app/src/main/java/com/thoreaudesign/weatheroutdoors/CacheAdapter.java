package com.thoreaudesign.weatheroutdoors;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.aws.ServiceName;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;

public class CacheAdapter
{
    private String cacheDir;

    public CacheAdapter(Bundle bundle)
    {
        this.cacheDir = this.getCacheDirFromBundle(bundle);
    }

    private String getCacheDirFromBundle(@NonNull Bundle bundle)
    {
        Log.v("--- Begin ---");
        Log.v("Bundle contents: " + bundle.toString());

        String cacheDir = "";

        try
        {
            cacheDir = bundle.getString(Cache.BUNDLE_KEY_DIR);
            Log.v("Cache directory: " + cacheDir);
        }
        catch(NullPointerException e)
        {
            Log.e(e.getMessage());
        }

        Log.v("--- End ---");

        return cacheDir;
    }

    private void checkCacheDir()
    {
        if (this.cacheDir == null)
        {
            throw new NullPointerException("CacheDir is null.");
        }
    }

    public Darksky getCacheData(ServiceName serviceName)
    {
        Log.v("--- Begin ---");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = null;

        try
        {
            this.checkCacheDir();
            Cache cache = new Cache(this.cacheDir);
            Log.v("Cache file: " + cache.getFile());

            try
            {
                json = cache.getSection(serviceName.toLower());
            }
            catch (RuntimeException runtimeException)
            {
                cache.delete();
                Log.e("An error occurred retrieving cache data... Deleted existing cache.");
            }
        }
        catch (NullPointerException e)
        {
            Log.e(e.getMessage());
        }

        Log.v("--- End ---");

        return gson.fromJson(json, Darksky.class);
    }
}
