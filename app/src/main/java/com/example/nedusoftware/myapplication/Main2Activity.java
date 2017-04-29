package com.example.nedusoftware.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Main2Activity extends Fragment implements View.OnClickListener{
    private Button button,button1;
    public static Main2Activity newInstance(String param1) {
        Main2Activity fragment = new Main2Activity();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2,container,false);
        initView(view);
        initListener();
        return view;
    }
    public void initView(View view){
        button = (Button)view.findViewById(R.id.location);
        button1 = (Button)view.findViewById(R.id.mylocation);
    }
    public void initListener(){
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location:
                Intent intent = new Intent(getActivity(),Loction.class);
                startActivity(intent);
                break;
            case R.id.mylocation:
                Intent intent1 = new Intent(getActivity(),MyLocation.class);
                startActivity(intent1);
                break;
        }
    }
}

