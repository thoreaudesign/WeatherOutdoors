package com.thoreaudesign.weatheroutdoors;

import android.util.Log;
import android.view.View;

public class Test implements View.OnClickListener
{
    private Weather currentActivity;
    private String lambda;

    public Test(Weather currentActivity, String lambda) {
        this.currentActivity = currentActivity;
        this.lambda = lambda;
        }

    @Override
    public void onClick(View view)
    {
        CacheController cache = new CacheController(this.currentActivity);

        String data = cache.read(this.lambda);

        Test.this.currentActivity.getContainer().setText(data);
    }
}
