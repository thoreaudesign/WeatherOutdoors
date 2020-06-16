package com.thoreaudesign.weatheroutdoors.ui.marine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thoreaudesign.weatheroutdoors.R;

public class MarineFragment extends Fragment
{

    private MarineViewModel marineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        marineViewModel =
                ViewModelProviders.of(this).get(MarineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marine, container, false);
        final TextView textView = root.findViewById(R.id.text_marine);
        marineViewModel.getText().observe(getActivity(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });
        return root;
    }
}