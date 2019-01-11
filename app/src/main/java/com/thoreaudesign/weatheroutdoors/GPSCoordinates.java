package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

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

        try
        {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setHorizontalAccuracy((Criteria.ACCURACY_HIGH));
            criteria.setVerticalAccuracy((Criteria.ACCURACY_HIGH));

            String bestProvider = manager.getBestProvider(criteria, true);

            if(bestProvider == null)
            {
                return null;
            }
            else
            {

                Location location = (Location) manager.getLastKnownLocation(bestProvider);

                return location;
            }
        }
        catch (SecurityException e)
        {
            Log.e("Security Exception", e.getMessage());
            return null;
        }
    }
}
