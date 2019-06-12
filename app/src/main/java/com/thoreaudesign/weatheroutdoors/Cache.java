package com.thoreaudesign.weatheroutdoors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//public class Cache implements Parcelable
public class Cache
{
    private File cache;
    private File dir;
    private String name;
    private String data;

//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Cache createFromParcel(Parcel in) {
//            return new Cache(in);
//        }
//
//        public Cache[] newArray(int size) {
//            return new Cache[size];
//        }
//    };
//
//    public Cache(Parcel in)
//    {
//        this.dir = new File(in.readString());
//        this.cache = new File(in.readString());
//        this.name = in.readString();
//        this.data = in.readString();
//    }

    public Cache(String dir, String name)
    {
        this.dir = new File(dir);
        this.name = name;
        this.cache = new File(dir, name);
    }

    public Cache(File dir, String name)
    {
        this.dir =  dir;
        this.name = name;
        this.cache = new File(dir, name);
        this.data = null;
    }

    public File getCache()
    {
        return this.cache;
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
        return this.getCache().exists();
    }

    public boolean write()
    {
        try
        {
            FileWriter writer = new FileWriter(this.getCache());
            writer.write(this.getData());
            writer.close();

            Log.i("'" + this.getName() + "' cache written: " + this.getCache().getAbsolutePath());

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
            BufferedReader buffer = new BufferedReader(new FileReader(this.getCache()));

            String line = buffer.readLine();

            while (line != null)
            {
                 builder.append(line).append("\n");
                 line = buffer.readLine();
            }

            Log.i("'" + this.getName() + "' cache read: " + this.getCache().getAbsolutePath());

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
        String path = this.getCache().getAbsolutePath();

        if(this.getCache().exists())
        {
            if (this.getCache().delete())
            {
                Log.i("'" + this.getName() + "' cache deleted: " + path);
                return true;
            }
            else
            {
                Log.i("Failed to delete '" + this.getName() + "' cache: " + path);
                return false;
            }
        }
        else
        {
            Log.i("Unable to delete '" + this.getName() + "' cache - does not exist: " + path);
            return false;
        }
    }

    public String toString()
    {
        String cache = "Location: " + this.getCache().getAbsolutePath() + "\n" +
                       "Data:\n" +
                       this.getData();
        return cache;
    }

//    @Override
//    public int describeContents()
//    {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags)
//    {
//        dest.writeString(this.dir.getAbsolutePath());
//        dest.writeString(this.cache.getAbsolutePath());
//        dest.writeString(this.name);
//        dest.writeString(this.data);
//    }
}
