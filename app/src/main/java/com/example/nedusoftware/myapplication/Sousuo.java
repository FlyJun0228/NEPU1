package com.example.nedusoftware.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sousuo extends Activity implements View.OnClickListener
    {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 加载/res/layout目录下的activity_book_twopane.xml布局文件
        setContentView(R.layout.activity_sousuo);
        Button button=(Button)findViewById(R.id.jiaoxuelou);
        button.setOnClickListener(this);
        Button button1=(Button)findViewById(R.id.ditu1);
        button1.setOnClickListener(this);
        Button button2=(Button)findViewById(R.id.kongjiaoshi);
        button2.setOnClickListener(this);
        Button button3=(Button)findViewById(R.id.chengji);
        button3.setOnClickListener(this);
        Button button4=(Button)findViewById(R.id.kebiao);
        button4.setOnClickListener(this);
    }
@Override
        public void onClick(View v) {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    switch (v.getId()) {
        case R.id.ditu1:
            Xiaoeiditu fragment = new Xiaoeiditu();
            transaction.replace(R.id.right_layout, fragment);
            break;
        case R.id.jiaoxuelou:
            Jiaoxuelou fragment1 = new Jiaoxuelou();
            transaction.replace(R.id.right_layout, fragment1);
            break;
        case R.id.kongjiaoshi:
            Kongjiaoshi fragment2=new Kongjiaoshi();
            transaction.replace(R.id.right_layout,fragment2);
            break;
        case R.id.chengji:
            Chengji fragment3= new Chengji();
            transaction.replace(R.id.right_layout,fragment3);
            break;
        case R.id.kebiao:
            Kebiao fragment4= new Kebiao();
            transaction.replace(R.id.right_layout,fragment4);
            break;
        default:
            break;
    }
    transaction.commit();
}
}
