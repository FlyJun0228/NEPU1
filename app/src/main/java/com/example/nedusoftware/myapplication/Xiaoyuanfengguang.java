package com.example.nedusoftware.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Xiaoyuanfengguang extends Activity {
    private LinearLayout layout;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoyuanfengguang);

        layout = (LinearLayout) findViewById(R.id.layout);

        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.ting);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.changlang);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.denglong);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.shitoulou);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.qiuting);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.light);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.lu);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.tongm);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.xue);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.xinyuan);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }
        {
            //  通过资源文件来获得指定一个Drawable对象
            Drawable drawable = getResources().getDrawable(R.drawable.xuejing);
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            layout.addView(imageView);
        }


    }
}
