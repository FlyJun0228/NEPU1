package com.example.nedusoftware.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Xiangce extends AppCompatActivity {

    int[] imageIds=new int[]
            {R.drawable.back,R.drawable.ground,R.drawable.nedu,
                    R.drawable.pugongying,R.drawable.xinyuan,R.drawable.yejing
            };
    private AdapterViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangce);
        flipper=(AdapterViewFlipper)findViewById(R.id.flipper);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView=new ImageView(Xiangce.this);
                imageView.setImageResource(imageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }
    public void prev(View source){
        flipper.showPrevious();
        flipper.stopFlipping();}
    public void next(View source){
        flipper.showNext();
        flipper.stopFlipping();}
    public void auto(View source){
        flipper.startFlipping();
    }
}

