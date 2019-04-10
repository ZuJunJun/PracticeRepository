package com.example.jay.packageandroidproject.fourth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.packageandroidproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    public static FourthFragment newInstance() {
        FourthFragment messageFragment = new FourthFragment();
        return messageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

}
