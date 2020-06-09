package com.thoreaudesign.weatheroutdoors.cache;

import com.thoreaudesign.weatheroutdoors.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cache
{
    /**
     * @param data Cache data in string format.
     */
    private String data;

    /**
     * @param dir File object representing directory that stores cache - "cacheDir"
     */
    private File dir;

    /**
     * @param file File object representing the cache file itself.
     */
    private File file;

    /**
     * @param CACHE_NAME Static string used to CACHE_NAME the cache file.
     */
    private static final String CACHE_NAME = "weatheroutdoors";

    public static final String BUNDLE_KEY_DATA = "cacheData";

    public Cache(File paramFile)
    {
        this.dir = paramFile;
        this.file = new File(paramFile, this.CACHE_NAME);
        this.data = null;
    }

    public Cache(String cacheDirPath)
    {
        this.dir = new File(cacheDirPath);
        this.file = new File(cacheDirPath, this.CACHE_NAME);
    }

    public boolean exists()
    {
        return getFile().exists();
    }

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
    public String getData()
    {
        return this.data;
    }

    public File getDir()
    {
        return this.dir;
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
    public void setData(String paramString)
    {
        this.data = paramString;
    }

    public void setDir(File paramFile)
    {
        this.dir = paramFile;
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
        catch (FileNotFoundException fileNotFoundException)
        {
            Log.e(fileNotFoundException.toString());
            return false;
        }
        catch (IOException iOException)
        {
            Log.e(iOException.toString());
            return false;
        }
    }

    public boolean write()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(getFile());
            fileWriter.write(getData());
            fileWriter.close();

            Log.i("'" + getName() + "' file written: " + getFile().getAbsolutePath());
            Log.v("Wrote cache data: " + getData());

            return true;

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
