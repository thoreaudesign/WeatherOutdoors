package com.thoreaudesign.weatheroutdoors.fragments.eventhandlers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.CacheController;

public class DataFragmentOnClickListener implements View.OnClickListener
{
    private Context currentActivity;
    private String lambda;
    private TextView displayData;

    public DataFragmentOnClickListener(Context currentActivity, String lambda, TextView displayData)
    {
        this.currentActivity = currentActivity;
        this.lambda = lambda;
        this.displayData = displayData;
    }

    @Override
    public void onClick(View view)
    {
        CacheController cache = new CacheController(this.currentActivity);

        String data = cache.read(this.lambda);

        displayData.setText(data);
    }
}
