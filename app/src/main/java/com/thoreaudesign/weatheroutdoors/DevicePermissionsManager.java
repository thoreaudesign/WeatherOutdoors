package com.thoreaudesign.weatheroutdoors;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class DevicePermissionsManager
{
    public int REQUEST_RESULT_FINE;

    private Activity activity;

    public DevicePermissionsManager(Activity paramActivity)
    {
        this.activity = paramActivity;
    }

    public boolean permissionRequired()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(
            this.activity.getApplicationContext(),
            "android.permission.ACCESS_FINE_LOCATION");

        if (permissionCheck != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void requestPermissions()
    {
        Log.v("Requesting permissions from user...");
        Activity activity = this.activity;
        int i = this.REQUEST_RESULT_FINE;
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, i);
    }
}
