package com.thoreaudesign.weatheroutdoors.cache;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.thoreaudesign.weatheroutdoors.BR;
import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.serialization.Darksky.Darksky;
import com.thoreaudesign.weatheroutdoors.serialization.WeatherDataResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cache extends BaseObservable
{
    protected WeatherDataResponse weatherDataResponse;
    private String data;
    private String icon;
    private File dir;
    private File file;

    private static final String CACHE_NAME = "weatheroutdoors";

    public Cache(File paramFile)
    {
        this.dir = paramFile;
        this.file = new File(paramFile, Cache.CACHE_NAME);
        this.data = null;
    }

    public Cache(String cacheDirPath)
    {
        this.dir = new File(cacheDirPath);
        this.file = new File(cacheDirPath, Cache.CACHE_NAME);
    }

    public boolean exists()
    {
        return getFile().exists();
    }

    @NonNull
    public String toString()
    {
        return getData();
    }

    public String getSection(String paramString)
    {
        if (exists())
        {
            read();
        }
        return null;
    }

    //<editor-fold desc="/** Getters & Setters **/">
    //<editor-fold desc="/** Getters **/">
    @Bindable
    public String getIcon()
    {
        return weatherDataResponse.darksky.getCurrently().getIcon();
    }
    @Bindable
    public String getData()
    {
        return data;
    }

    public Darksky getDarkskyData()
    {
        return weatherDataResponse.darksky;
    }

    public File getFile()
    {
        return this.file;
    }

    public String getName()
    {
        return Cache.CACHE_NAME;
    }
    //</editor-fold>

    //<editor-fold desc="/** Setters **/">

    public void setWeatherDataResponse(WeatherDataResponse weatherDataResponse)
    {
        this.weatherDataResponse = weatherDataResponse;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
        Log.v("notifyPropertyChanged on icon occurred in setIcon()");
        notifyPropertyChanged(BR.icon);
    }

    public void setData(String cacheData)
    {
        this.data = cacheData;
    }

    public void setData(WeatherDataResponse cacheData)
    {
        this.weatherDataResponse = cacheData;
        Log.v("notifyPropertyChanged on icon occurred in setData()");
        notifyPropertyChanged(BR.icon);
    }

    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="/** IO Ops (R, W, D) **/">
    public boolean read()
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

            return true;
        }
        catch (IOException fileNotFoundException)
        {
            Log.e(fileNotFoundException.toString());
            return false;
        }
    }

    public boolean write()
    {
        try
        {
            if(data == null && weatherDataResponse == null)
            {
                throw new IOException("Failed to write cache data. Cache data is null. No data to write.");
            }
            else
            {
                if (data == null)
                {
                    data = weatherDataResponse.toString();
                }

                FileWriter fileWriter = new FileWriter(getFile());
                fileWriter.write(getData());
                fileWriter.close();

                Log.i("'" + getName() + "' file written: " + getFile().getAbsolutePath());
                Log.v("Wrote cache data: " + getData());

                return true;
            }

        }
        catch (IOException iOException)
        {
            Log.e(iOException.toString());
            return false;
        }
    }

    public boolean delete()
    {
        String str = getFile().getAbsolutePath();
        if (getFile().exists())
        {
            if (getFile().delete())
            {
                Log.i("'" + getName() + "' file deleted: " + str);
                return true;
            }
            else
            {
                Log.w("Failed to delete '" + getName() + "' file: " + str);
                return false;
            }
        }
        else
        {
            Log.w("Unable to delete '" + getName() + "' file - does not exist: ");
            return false;
        }
    }

    //</editor-fold>
}
