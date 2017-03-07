package com.example.nedusoftware.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Start extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final Intent it=new Intent(this,LocationFragment.class);
        Timer timer=new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                startActivity(it);
            }
        };
        timer.schedule(task,1000*3);
    }
}
