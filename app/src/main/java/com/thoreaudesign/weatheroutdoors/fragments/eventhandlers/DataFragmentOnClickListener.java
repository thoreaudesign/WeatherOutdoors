package com.thoreaudesign.weatheroutdoors.fragments.eventhandlers;

import android.view.View;
import android.widget.TextView;

public class DataFragmentOnClickListener implements View.OnClickListener
{
    private String data;
    private TextView displayData;

    public DataFragmentOnClickListener(String data, TextView displayData)
    {
        this.displayData = displayData;
        this.data = data;
    }

    @Override
    public void onClick(View view)
    {
        displayData.setText(this.data);
    }
}
