package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CacheController
{
    private Context context;
    private static final String LOG_TAG = "CacheController";

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

            Log.i(LOG_TAG, "Cache file written: " + cache.getAbsolutePath());
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, e.toString());
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

            Log.i(LOG_TAG, "Cache file read: " + cache.getAbsolutePath());
        }
        catch (FileNotFoundException e)
        {
            Log.e("CacheController::ready", e.toString());
        }
        catch (IOException e)
        {
            Log.e("CacheController::read", e.toString());
        }

        return  builder.toString();
    }
}
