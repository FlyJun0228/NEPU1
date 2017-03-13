package com.example.nedusoftware.myapplication;

/**
 * Created by NEDUsoftware on 2016/10/31.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;


public class LocationFragment extends Fragment {
private ViewFlipper viewFlipper;
    private Button button;
    private Button button1,button2,button3;

    public static LocationFragment newInstance(String param1) {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    public LocationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.fragment_location,container,false);
        button=(Button)rootView.findViewById(R.id.button_1);

        button.setOnClickListener(new View.OnClickListener()
    {

        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity().getApplicationContext(),DOverview.class);
            startActivity(i);
        }
    });
        button1=(Button)rootView.findViewById(R.id.button_25);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(),Xiaoyuanfengguang.class);
                startActivity(intent);
            }
        });
        button2=(Button)rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(),Sousuo.class);
                startActivity(intent);
            }
        });

        button3=(Button)rootView.findViewById(R.id.net);
        button3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(),Zhuyaowangzhan.class);
                startActivity(intent);
            }
        });
        viewFlipper=(ViewFlipper)rootView.findViewById(R.id.details);
        viewFlipper.showPrevious();
        viewFlipper.startFlipping();
        return rootView;

    }


}