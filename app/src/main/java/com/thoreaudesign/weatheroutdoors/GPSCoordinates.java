package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSCoordinates
{
    private Weather activity;

    public GPSCoordinates(Weather activity)
    {
        this.activity = activity;
    }

    public Location getLocation()
    {
        LocationManager manager = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);

        Log.v("Successfully instansiated LocationManager from Context.LOCATION_SERVICE.");

        try
        {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            Log.v("Set Accuraccy: ACCURACY_FINE");
            criteria.setHorizontalAccuracy((Criteria.ACCURACY_HIGH));
            Log.v("Set Horizontal Accuraccy: ACCURACY_HIGH");
            criteria.setVerticalAccuracy((Criteria.ACCURACY_HIGH));
            Log.v("Set Vertical Accuraccy: ACCURACY_HIGH");
            String bestProvider = manager.getBestProvider(criteria, true);

            if(bestProvider == null)
            {
                Log.e("Failed to obtain BestProvider from LocationManager.");
                return null;
            }
            else
            {

                Location location = manager.getLastKnownLocation(bestProvider);
                Log.v("Successfully instansiated Location from device.");
                return location;
            }
        }
        catch (SecurityException e)
        {
            Log.e(e.getMessage());
            return null;
        }
    }
}
