package com.thoreaudesign.weatheroutdoors.fragments.eventhandlers;

import android.view.View;
import android.widget.TextView;

public class DataFragmentOnClickListener implements View.OnClickListener
{
    private String data;

    private TextView displayData;

    public DataFragmentOnClickListener(String paramString, TextView paramTextView)
    {
        this.displayData = paramTextView;
        this.data = paramString;
    }

    public void onClick(View paramView)
    {
        this.displayData.setText(this.data);
    }
}
