package com.thoreaudesign.weatheroutdoors.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thoreaudesign.weatheroutdoors.R;

public class WeatherParentFragment extends Fragment
{
    ViewPager2 viewPager;
    WeatherPagerAdapter weatherPagerAdapter;

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.weather_tabs, container, false);
    }

    public void onViewCreated(final View view, final Bundle savedInstanceState)
    {
        weatherPagerAdapter = new WeatherPagerAdapter(this);
        viewPager = view.findViewById(R.id.weather_viewPager);
        viewPager.setAdapter(weatherPagerAdapter);

        final TabLayout tabLayout = view.findViewById(R.id.weather_tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy()
        {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position)
            {

                switch(position)
                {
                    case 0:
                        tab.setText("Today");
                        break;

                    case 1:
                        tab.setText("24-Hour");
                        break;

                    case 2:
                        tab.setText("10-Day");
                        break;
                }
            }
        });

        tabLayoutMediator.attach();
    }

    static class WeatherPagerAdapter extends FragmentStateAdapter
    {
        WeatherPagerAdapter(Fragment fragment)
        {
            super(fragment);
        }

        @Override
        public int getItemCount() {
            return 3; // One for each tab, 3 in our example.
        }

        @NonNull
        @Override
        public Fragment createFragment(int position)
        {
            switch(position) {
                case 0:
                    return new WeatherTodayFragment();
                case 1:
                    return new Weather24HourFragment();
                case 2:
                    return new Weather10DayFragment();
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}