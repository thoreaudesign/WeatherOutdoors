package com.thoreaudesign.weatheroutdoors.aws;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.thoreaudesign.weatheroutdoors.Log;
import com.thoreaudesign.weatheroutdoors.fragments.IWeatherFragment;

public class WeatherFragmentDecorator
{
    private Fragment fragment;

    public WeatherFragmentDecorator(Fragment fragment)
    {
        this.fragment = fragment;
    }

    public void updateFragment()
    {
        if (this.fragment instanceof IWeatherFragment)
        {
            ((IWeatherFragment) fragment).update();
        }
        else if(this.fragment instanceof NavHostFragment)
        {
            Log.i("Detected NavHostFragment. No defined actions.");
        }
        else
        {
            String className = fragment.getClass().getName();

            Log.e("Detected unknown fragment type: " + className);
        }
    }
}
