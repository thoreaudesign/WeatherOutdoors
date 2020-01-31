package com.thoreaudesign.weatheroutdoors;

import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cache extends ViewModel
{
    public static final String BUNDLE_KEY_DIR = "cacheDir";
    private String data;
    private File dir;
    private File file;
    private String name = "weatheroutdoors";

    public Cache(File paramFile)
    {
        this.dir = paramFile;
        this.file = new File(paramFile, this.name);
        this.data = null;
    }

    public Cache(String paramString)
    {
        this.dir = new File(paramString);
        this.file = new File(paramString, this.name);
    }

    public boolean delete()
    {
        String str = getFile().getAbsolutePath();
        if (getFile().exists())
        {
            if (getFile().delete())
            {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("'");
                stringBuilder2.append(getName());
                stringBuilder2.append("' file deleted: ");
                stringBuilder2.append(str);
                Log.i(stringBuilder2.toString());
                return true;
            }
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Failed to delete '");
            stringBuilder1.append(getName());
            stringBuilder1.append("' file: ");
            stringBuilder1.append(str);
            Log.i(stringBuilder1.toString());
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to delete '");
        stringBuilder.append(getName());
        stringBuilder.append("' file - does not exist: ");
        stringBuilder.append(str);
        Log.i(stringBuilder.toString());
        return false;
    }

    public boolean exists()
    {
        return getFile().exists();
    }

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
        return this.name;
    }

    public String getSection(String paramString)
    {
        if (exists())
        {
            read();
            try
            {
                return (new JSONObject(getData())).getString(paramString);
            } catch (JSONException jSONException)
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to parse section '");
                stringBuilder.append(paramString);
                stringBuilder.append("' from JSON cache.");
                Log.e(stringBuilder.toString());
                throw new RuntimeException(jSONException);
            }
        }
        return null;
    }

    public boolean read()
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFile()));
            for (String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine())
            {
                stringBuilder.append(str);
                stringBuilder.append("\n");
            }
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("'");
            stringBuilder1.append(getName());
            stringBuilder1.append("' file read: ");
            stringBuilder1.append(getFile().getAbsolutePath());
            Log.i(stringBuilder1.toString());
            setData(stringBuilder.toString());
            return true;
        } catch (FileNotFoundException fileNotFoundException)
        {
            Log.e(fileNotFoundException.toString());
            return false;
        } catch (IOException iOException)
        {
            Log.e(iOException.toString());
            return false;
        }
    }

    public void setData(String paramString)
    {
        this.data = paramString;
    }

    public void setDir(File paramFile)
    {
        this.dir = paramFile;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public String toString()
    {
        return getData();
    }

    public boolean write()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(getFile());
            fileWriter.write(getData());
            fileWriter.close();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            stringBuilder.append(getName());
            stringBuilder.append("' file written: ");
            stringBuilder.append(getFile().getAbsolutePath());
            Log.i(stringBuilder.toString());
            return true;
        } catch (IOException iOException)
        {
            Log.e(iOException.toString());
            return false;
        }
    }
}
