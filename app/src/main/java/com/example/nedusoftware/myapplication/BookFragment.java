package com.example.nedusoftware.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.nedusoftware.myapplication.kuaidi.Kd;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */
public class BookFragment extends Fragment {

    private Button mRobotBtn;
    private Button mLostBtn;
    private Button mXiangce;
    private Button btn_Kd;
    private ViewFlipper viewFlipper1;

    public static BookFragment newInstance(String param1) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public BookFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_book, container, false);
        mLostBtn = (Button) rootView.findViewById(R.id.lost);
        mLostBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Lostfound.class);
                startActivity(intent);
            }
        });
        mXiangce = (Button) rootView.findViewById(R.id.xiangce);
        mXiangce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Xiangce.class);
                startActivity(i);
            }
        });
        mRobotBtn = (Button) rootView.findViewById(R.id.robot);

        mRobotBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Robot.class);
                startActivity(i);
            }
        });
        btn_Kd = (Button) rootView.findViewById(R.id.btn_Kd);
        btn_Kd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Kd.class);
                startActivity(intent);
            }
        });
        viewFlipper1 = (ViewFlipper) rootView.findViewById(R.id.details1);
        viewFlipper1.showPrevious();
        viewFlipper1.startFlipping();
        return rootView;

    }
}
