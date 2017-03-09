package com.example.nedusoftware.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Robot extends Activity implements OnClickListener {
    /**
     * Called when the activity is first created.
     */

    private Button mBtnSend;
    private Button mBtnBack;
    private EditText mEditTextContent;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
     initData();


}


    public void initView()
    {
        mListView = (ListView) findViewById(R.id.id_listView);
        mBtnSend = (Button) findViewById(R.id.btn_add_send);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);

        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private String[]msgArray = new String[]{"您好，我是棒棒糖。","那代价是什么？", "有！？", "我也有", "那上吧",
           };


    public void initData() {

        String contString = mEditTextContent.getText().toString();
        ChatMsgEntity entity = new ChatMsgEntity();
        if (contString.isEmpty()) {
            entity.setDate(getDate());
            entity.setMsgType(true);
            entity.setText(msgArray[0]);
            mDataArrays.add(entity);
        } else {
            if (contString.equals("asd")) {
                entity.setDate(getDate());
                entity.setMsgType(true);
                entity.setText(msgArray[1]);
                mDataArrays.add(entity);
            } else {
                entity.setDate(getDate());
                entity.setMsgType(true);
                entity.setText(msgArray[2]);
                mDataArrays.add(entity);
            }
        }
        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.btn_add_send:
                send();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void send() {
        String contString = mEditTextContent.getText().toString();
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(getDate());
        entity.setMsgType(false);
        entity.setText(contString);
            mDataArrays.add(entity);

        initData();
    }

public void initRespond() {

    String contString = mEditTextContent.getText().toString();
    ChatMsgEntity entity = new ChatMsgEntity();

}
    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));


        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);


        return sbBuffer.toString();
    }


    public void head_xiaohei(View v) {     //标题栏 返回按钮
        Intent intent = new Intent (Robot.this,BookFragment.class);
        startActivity(intent);
    }
}