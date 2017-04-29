package com.example.nedusoftware.myapplication.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nedusoftware.myapplication.R;
import com.example.nedusoftware.myapplication.bean.User;
import com.example.nedusoftware.myapplication.config.Constants;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by NEDUsoftware on 2017/4/24.
 */

public class Register extends Activity implements View.OnClickListener{
    private EditText et_register_id;
    private EditText et_register_password;
    private EditText et_register_name;
    private Button btn_register_in;
    private String psd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
        Bmob.initialize(this, Constants.Bmob_APPID);
    }
    private void initView() {
        btn_register_in = (Button) findViewById(R.id.btn_register_in);
        et_register_id = (EditText) findViewById(R.id.et_register_id);
        et_register_password = (EditText) findViewById(R.id.et_register_password);
        et_register_name = (EditText) findViewById(R.id.et_register_name);


    }

    private void initListener() {
        btn_register_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_in:
                adduser();
                break;
        }
    }
    private void adduser() {
        if ((et_register_id.getText().toString().equals("")) || (et_register_password.getText().toString().equals(""))
                ||et_register_name.getText().toString().equals("")) {
            Toast.makeText(Register.this, "请输入账号密码和昵称", Toast.LENGTH_SHORT).show();
        } else {
            Md5 md5 = new Md5();
            psd = md5.getmd5(et_register_password.getText().toString());
            BmobQuery<User> bmobQuery = new BmobQuery<User>();
            bmobQuery.addWhereEqualTo("userId", et_register_id.getText());
            bmobQuery.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e==null){
                        if (list.size() == 0) {
                            User user = new User();
                            user.setUserId(et_register_id.getText().toString());
                            user.setUserPassword(psd);
                            user.setUserName(et_register_name.getText().toString());
                            user.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(Register.this, "添加成功", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(Register.this,Login.class);
                                        startActivity(intent);
                                        Register.this.finish();
                                    }else {

                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
