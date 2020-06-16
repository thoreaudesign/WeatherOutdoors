package com.thoreaudesign.weatheroutdoors.ui.marine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MarineViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public MarineViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}