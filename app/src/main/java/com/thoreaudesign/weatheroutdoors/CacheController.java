package com.thoreaudesign.weatheroutdoors;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CacheController
{
    private Context context;

    public CacheController(Context context)
    {
        this.context = context;
    }

    public void write(String label, String data)
    {
        try
        {
            File cache = new File(this.context.getCacheDir(), label);
            FileWriter writer = new FileWriter(cache);
            writer.write(data);
            writer.close();

            Log.i("Cache file written: " + cache.getAbsolutePath());
        }
        catch (IOException e)
        {
            Log.e(e.toString());
        }
    }

    public String read(String label)
    {
        File cache = new File(this.context.getCacheDir(), label);
        StringBuilder builder = new StringBuilder();

        try
        {
            BufferedReader buffer = new BufferedReader(new FileReader(cache));
            String line = buffer.readLine();

            while (line != null)
            {
                 builder.append(line).append("\n");
                line = buffer.readLine();
            }

            Log.i("Cache file read: " + cache.getAbsolutePath());
        }
        catch (FileNotFoundException e)
        {
            Log.e(e.toString());
        }
        catch (IOException e)
        {
            Log.e(e.toString());
        }

        return  builder.toString();
    }
}
