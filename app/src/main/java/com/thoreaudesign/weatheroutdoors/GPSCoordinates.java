package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.security.Security;

public class GPSCoordinates
{
    private Context context;

    public GPSCoordinates(Context context)
    {
        this.context = context;
    }

    public Location getLocation()
    {
        LocationManager manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        try
        {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setHorizontalAccuracy((Criteria.ACCURACY_HIGH));
            criteria.setVerticalAccuracy((Criteria.ACCURACY_HIGH));

            String bestProvider = manager.getBestProvider(criteria, true);

            Location location = (Location) manager.getLastKnownLocation(bestProvider);

            return location;
        }
        catch (SecurityException e)
        {
            Log.e("Security Exception", e.getMessage());
            return null;
        }
    }
}
