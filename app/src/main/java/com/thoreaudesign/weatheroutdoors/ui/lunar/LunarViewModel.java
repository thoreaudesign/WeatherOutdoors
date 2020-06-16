package com.thoreaudesign.weatheroutdoors.ui.lunar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LunarViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public LunarViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}