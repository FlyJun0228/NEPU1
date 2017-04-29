package com.example.nedusoftware.myapplication;


import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nedusoftware.myapplication.bean.lov;

import cn.bmob.v3.listener.SaveListener;


public class AddLove extends BaseActivity implements OnClickListener {

    EditText edit_title, edit_photo, edit_describe;
    Button btn_back, btn_true;

    TextView tv_add;
    String from = "";


    String old_describe = "";

    @Override
    public void setContentView() {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_add_love);
    }

    @Override
    public void initViews() {
        // TODO Auto-generated method stub
        tv_add = (TextView) findViewById(R.id.tv_add);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);

        edit_describe = (EditText) findViewById(R.id.edit_describe);

    }

    @Override
    public void initListeners() {
        // TODO Auto-generated method stub
        btn_back.setOnClickListener(this);
        btn_true.setOnClickListener(this);
    }

    @Override
    public void initData() {
        old_describe = getIntent().getStringExtra("describe");
        edit_describe.setText(old_describe);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btn_true) {
            addByType();
        } else if (v == btn_back) {
            finish();
        }
    }
    String describe = "";

    /**�����������ʧ��/����
     * addByType
     * @Title: addByType
     * @throws
     */
    private void addByType(){
        describe = edit_describe.getText().toString();
        if(TextUtils.isEmpty(describe)) {
            ShowToast("请添加描述");
            return;
        }
        addLost();
    }

    private void addLost(){
        lov love = new lov();
        love.setDescribe(describe);
        love.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("发布成功!");
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                ShowToast("发布失败:"+arg0);
            }
        });
    }

}
