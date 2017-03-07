package com.example.nedusoftware.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/20.
 */

public class ZuocelanFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup cotainer, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_zuocelan,cotainer,false);
        return view;
    }
}
