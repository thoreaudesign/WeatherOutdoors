package com.thoreaudesign.weatheroutdoors;

import androidx.databinding.BaseObservable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cache extends BaseObservable
{
    private String data;
    private File file;

    private static final String CACHE_NAME = "weatheroutdoors";
    private static final int CACHE_LIFE_MILLIS = 15 * 60 * 1000;

    public Cache(File paramFile)
    {
        this.file = new File(paramFile, Cache.CACHE_NAME);
    }

    public Cache(String cacheDirPath)
    {
        this.file = new File(cacheDirPath, Cache.CACHE_NAME);
    }

    //<editor-fold desc="/** Getters & Setters **/">
    //<editor-fold desc="/** Getters **/">

    public File getFile()
    {
        return file;
    }

    public String getName()
    {
        return Cache.CACHE_NAME;
    }

    public String getData()
    {
        return data;
    }
    //</editor-fold>

    //<editor-fold desc="/** Setters **/">
    public void setData(String cacheData)
    {
        this.data = cacheData;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="/** IO Ops (R, W, D) **/">
    public void read()
    {
        StringBuilder cacheData = new StringBuilder();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFile()));
            for (String cacheLine = bufferedReader.readLine(); cacheLine != null; cacheLine = bufferedReader.readLine())
            {
                cacheData.append(cacheLine);
                cacheData.append("\n");
            }

            Log.i("'" + getName() + "' file read: " + getFile().getAbsolutePath());

            setData(cacheData.toString());
        }
        catch (IOException fileNotFoundException)
        {
            Log.e(fileNotFoundException.toString());
        }
    }

    public void write()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(getFile());
            fileWriter.write(data);
            fileWriter.close();

            Log.i("'" + getName() + "' file written: " + getFile().getAbsolutePath());
            Log.v("Wrote cache data: " + data);
        }
        catch (IOException iOException)
        {
            Log.e(iOException.toString());
        }
    }

    public void delete()
    {
        String str = getFile().getAbsolutePath();
        if (getFile().exists())
        {
            if (getFile().delete())
            {
                Log.i("'" + getName() + "' file deleted: " + str);
            }
            else
            {
                Log.w("Failed to delete '" + getName() + "' file: " + str);
            }
        }
        else
        {
            Log.w("Unable to delete '" + getName() + "' file - does not exist: ");
        }
    }
    //</editor-fold>

    private boolean isCacheEmpty()
    {
        boolean isEmpty = false;

        if(data == null)
        {
            Log.w("Cache is empty.");
            Log.w("Verify Cache.read() was called prior to Cache.isOutdated().");
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
        long lastModified = file.lastModified();

        Log.v("Current time: " + now);
        Log.v("Last modified: " + lastModified);
        Log.v("Fileize: " + file.length());

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

        Log.i("Validating cache file: " + file.toString());

        if(file.exists())
        {
            if (isCacheEmpty() || isCacheExpired())
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
            Log.i("Cache file does not exist.");
            isOutdated = true;
        }

        return isOutdated;
    }

    public void setWeatherDataResponseFromData()
    {
    }
}
