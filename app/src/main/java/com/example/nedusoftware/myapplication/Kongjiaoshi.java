package com.example.nedusoftware.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Kongjiaoshi extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup cotainer, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_kongjiaoshi,cotainer,false);
        return view;
    }
}
