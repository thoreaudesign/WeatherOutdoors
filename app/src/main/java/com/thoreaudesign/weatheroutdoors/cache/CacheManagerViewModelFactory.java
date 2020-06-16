package com.thoreaudesign.weatheroutdoors.cache;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CacheManagerViewModelFactory implements ViewModelProvider.Factory
{
    private Cache cache;

    public CacheManagerViewModelFactory(Cache cache)
    {
        this.cache = cache;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CacheViewModel(cache);
    }
}