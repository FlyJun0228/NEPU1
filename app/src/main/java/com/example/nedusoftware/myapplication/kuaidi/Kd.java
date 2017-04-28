package com.example.nedusoftware.myapplication.kuaidi;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nedusoftware.myapplication.BaseActivity;
import com.example.nedusoftware.myapplication.Netutil.PureNetUtil;
import com.example.nedusoftware.myapplication.R;
import com.example.nedusoftware.myapplication.adapter.KdAdapter;
import com.example.nedusoftware.myapplication.bean.Kdbean;
import com.example.nedusoftware.myapplication.bean.ListBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by NEDUsoftware on 2017/4/24.
 */

public class Kd extends BaseActivity implements View.OnClickListener {
    private EditText et_kuaidi;
    private Button btn_kuaidi;
    private ListView listView;
    private Spinner spinner;
    private String[] arr = {"顺丰", "申通", "圆通", "韵达", "天天", "EMS", "中通",
            "京东快递", "汇通", "全峰", "德邦", "国通", "如风达",
            "宅急送", "EMS国际", "FedEx国际", "邮政国内（挂号信）",
            "Ups国际快递", "中铁快运"};
    private String[] arrid = {"sf", "sto", "yt", "yd", "tt", "ems", "zto", "jd", "ht", "qf", "db",
            "gt", "rfd", "zjs", "emsg", "fedex", "yzgn", "ups", "ztky"};
    private String com;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_kuaidi);
    }

    @Override
    public void initViews() {
        et_kuaidi = (EditText) findViewById(R.id.et_kuaidi_id);
        btn_kuaidi = (Button) findViewById(R.id.btn_kuaidi);
        listView = (ListView) findViewById(R.id.list_kuaidi);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        spinner.setAdapter(adpter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                com = arrid[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void initListeners() {
        btn_kuaidi.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kuaidi:
                if (et_kuaidi.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入快递号！", Toast.LENGTH_SHORT).show();
                } else {
                    Async async = new Async();
                    async.execute(et_kuaidi.getText().toString());
                }
                break;
        }
    }

    private class Async extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = "http://v.juhe.cn/exp/index?key=9850474c28d7c218e3ea68ef45dba99e&com=" + com + "&no=" + params[0];
            return PureNetUtil.get(url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Gson gson = new Gson();
                Kdbean kd = gson.fromJson(s, Kdbean.class);
                if(kd.getResultcode().equals("200"))
                {
                    List<ListBean> listBeen = kd.getResult().getList();
                    KdAdapter kdAdapter = new KdAdapter(Kd.this, listBeen);
                    listView.setAdapter(kdAdapter);
                }
                else{
                    Toast.makeText(Kd.this, "查询失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
