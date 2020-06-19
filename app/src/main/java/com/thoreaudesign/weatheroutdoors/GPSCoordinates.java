package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSCoordinates
{
    private Weather activity;

    public GPSCoordinates(Weather paramWeather)
    {
        this.activity = paramWeather;
    }

    public Location getLocation()
    {
        LocationManager locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        Log.v("Successfully instansiated LocationManager from Context.LOCATION_SERVICE.");
        try
        {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            Log.v("Set Accuraccy: ACCURACY_FINE");
            criteria.setHorizontalAccuracy(3);
            Log.v("Set Horizontal Accuraccy: ACCURACY_HIGH");
            criteria.setVerticalAccuracy(3);
            Log.v("Set Vertical Accuraccy: ACCURACY_HIGH");
            String str = locationManager.getBestProvider(criteria, true);
            if (str == null)
            {
                Log.e("Failed to obtain BestProvider from LocationManager.");
                return null;
            }
            Location location = locationManager.getLastKnownLocation(str);
            Log.v("Successfully instansiated Location from device.");
            return location;
        } catch (SecurityException securityException)
        {
            Log.e(securityException.getMessage());
            return null;
        }
    }
}
