package com.thoreaudesign.weatheroutdoors;

import androidx.navigation.fragment.NavHostFragment;

import com.thoreaudesign.weatheroutdoors.fragments.IWeatherFragment;

public class NavigationHost extends NavHostFragment implements IWeatherFragment
{
    public void update()
    {
        Log.v("NavigationHost update() method.");
    }
}
