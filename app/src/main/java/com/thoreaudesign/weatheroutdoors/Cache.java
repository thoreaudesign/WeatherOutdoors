package com.thoreaudesign.weatheroutdoors;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//public class Cache implements Parcelable
public class Cache
{
    private File file;
    private File dir;
    private String name = Weather.CACHE_NAME;
    private String data;

    public Cache(String dir)
    {
        this.dir = new File(dir);
        this.file = new File(dir, name);
    }

    public Cache(File dir)
    {
        this.dir =  dir;
        this.file = new File(dir, name);
        this.data = null;
    }

    public String getSection(String name)
    {
        String section = null;

        if(this.exists())
        {
            this.read();

            try
            {
                JSONObject json = new JSONObject(this.getData());

                section = json.getString(name);
            }
            catch (JSONException e)
            {
                Log.e("Failed to parse section '" + name + "' from JSON cache.");
                throw new RuntimeException(e);
            }
        }

        return section;
    }

    public File getFile()
    {
        return this.file;
    }

    public File getDir()
    {
        return this.dir;
    }

    public String getName()
    {
        return name;
    }

    public String getData()
    {
        return data;
    }

    public void setDir(File dir)
    {
        this.dir = dir;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public boolean exists()
    {
        return this.getFile().exists();
    }

    public boolean write()
    {
        try
        {
            FileWriter writer = new FileWriter(this.getFile());
            writer.write(this.getData());
            writer.close();

            Log.i("'" + this.getName() + "' file written: " + this.getFile().getAbsolutePath());

            return true;
        }
        catch (IOException e)
        {
            Log.e(e.toString());
            return false;
        }
    }

    public boolean read()
    {
        StringBuilder builder = new StringBuilder();

        try
        {
            BufferedReader buffer = new BufferedReader(new FileReader(this.getFile()));

            String line = buffer.readLine();

            while (line != null)
            {
                 builder.append(line).append("\n");
                 line = buffer.readLine();
            }

            Log.i("'" + this.getName() + "' file read: " + this.getFile().getAbsolutePath());

            this.setData(builder.toString());

            return true;
        }
        catch (FileNotFoundException e)
        {
            Log.e(e.toString());
            return false;
        }
        catch (IOException e)
        {
            Log.e(e.toString());
            return false;
        }
    }

    public boolean delete()
    {
        String path = this.getFile().getAbsolutePath();

        if(this.getFile().exists())
        {
            if (this.getFile().delete())
            {
                Log.i("'" + this.getName() + "' file deleted: " + path);
                return true;
            }
            else
            {
                Log.i("Failed to delete '" + this.getName() + "' file: " + path);
                return false;
            }
        }
        else
        {
            Log.i("Unable to delete '" + this.getName() + "' file - does not exist: " + path);
            return false;
        }
    }

    public String toString()
    {
        return this.getData();
    }

}
