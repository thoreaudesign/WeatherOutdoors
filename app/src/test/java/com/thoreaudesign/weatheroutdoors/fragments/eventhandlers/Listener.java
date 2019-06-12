package com.thoreaudesign.weatheroutdoors.fragments.eventhandlers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.Cache;

public class Listener implements View.OnClickListener
{
    private Context currentActivity;
    private String lambda;
    private TextView displayData;

    public Listener(Context currentActivity, String lambda, TextView displayData)
    {
        this.currentActivity = currentActivity;
        this.lambda = lambda;
        this.displayData = displayData;
    }

    @Override
    public void onClick(View view)
    {
        Cache cache = new Cache(this.currentActivity);

        String data = cache.read(this.lambda);

        displayData.setText(data);
    }
}
