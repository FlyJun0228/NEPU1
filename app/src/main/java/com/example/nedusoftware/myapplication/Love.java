package com.example.nedusoftware.myapplication;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nedusoftware.myapplication.adapter.BaseAdapterHelper;
import com.example.nedusoftware.myapplication.adapter.QuickAdapter;
import com.example.nedusoftware.myapplication.adapter.base.EditPopupWindow;
import com.example.nedusoftware.myapplication.bean.lov;
import com.example.nedusoftware.myapplication.config.Constants;
import com.example.nedusoftware.myapplication.i.IPopupItemClick;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

import static com.example.nedusoftware.myapplication.R.id.tv_describe;
import static com.example.nedusoftware.myapplication.R.id.tv_photo;
import static com.example.nedusoftware.myapplication.R.id.tv_title;

public class Love extends BaseActivity implements View.OnClickListener,
        IPopupItemClick, AdapterView.OnItemLongClickListener {

    RelativeLayout layout_action;//
    LinearLayout layout_all;
    TextView tv_lost;
    ListView listview;
    Button btn_add;

    protected QuickAdapter<lov> LostAdapter;// ʧ��

    private Button layout_found;
    private Button layout_lost;
    PopupWindow morePop;

    RelativeLayout progress;
    LinearLayout layout_no;
    TextView tv_no;

    @Override
    public void setContentView() {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_love);
        Toast.makeText(Love.this,"温馨小提示，表白一旦发出就不能被删除哦~~",Toast.LENGTH_LONG).show();
    }

    @Override
    public void initViews() {
        // TODO Auto-generated method stub
        progress = (RelativeLayout) findViewById(R.id.progress);
        layout_no = (LinearLayout) findViewById(R.id.layout_no);
        tv_no = (TextView) findViewById(R.id.tv_no);

        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        // Ĭ����ʧ�����
        tv_lost = (TextView) findViewById(R.id.tv_lost);
        listview = (ListView) findViewById(R.id.list_lost);
        btn_add = (Button) findViewById(R.id.btn_add);
        // ��ʼ����������

    }

    @Override
    public void initListeners() {
        // TODO Auto-generated method stub
        listview.setOnItemLongClickListener(this);
        btn_add.setOnClickListener(this);
        layout_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == layout_all) {
            showListPop();
        } else if (v == btn_add) {
            Intent intent = new Intent(this, AddLove.class);
            startActivityForResult(intent, Constants.REQUESTCODE_ADD);
        } else if (v == layout_found) {
            changeTextView(v);
            morePop.dismiss();
        } else if (v == layout_lost) {
            changeTextView(v);
            morePop.dismiss();
            queryLosts();
        }
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        if (LostAdapter == null) {
            LostAdapter = new QuickAdapter<lov>(this, R.layout.loveitem) {
                @Override
                protected void convert(BaseAdapterHelper helper, lov lost) {
                    helper.setText(tv_title, lost.getTitle())
                            .setText(tv_describe, lost.getDescribe())
                            .setText(tv_photo, lost.getPhone());
                }
            };
        }

        listview.setAdapter(LostAdapter);
        // Ĭ�ϼ���ʧ�����
        queryLosts();
    }

    private void changeTextView(View v) {
        if (v == layout_found) {
            tv_lost.setTag("Found");
            tv_lost.setText("Found");
        } else {
            tv_lost.setTag("Lost");
            tv_lost.setText("Lost");
        }
    }

    @SuppressWarnings("deprecation")
    private void showListPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_lost, null);
        // ע��
        layout_found = (Button) view.findViewById(R.id.layout_found);
        layout_lost = (Button) view.findViewById(R.id.layout_lost);
        layout_found.setOnClickListener(this);
        layout_lost.setOnClickListener(this);
        morePop = new PopupWindow(view, mScreenWidth, 600);

        morePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    morePop.dismiss();
                    return true;
                }
                return false;
            }
        });

        morePop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        morePop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        morePop.setTouchable(true);
        morePop.setFocusable(true);
        morePop.setOutsideTouchable(true);
        morePop.setBackgroundDrawable(new BitmapDrawable());
        morePop.setAnimationStyle(R.style.MenuPop);
    }

    EditPopupWindow mPopupWindow;
    int position;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.REQUESTCODE_ADD:// ��ӳɹ�֮��Ļص�
                break;
        }
    }

    /**
     * ��ѯȫ��ʧ����Ϣ queryLosts
     *
     * @return void
     * @throws
     */
    private void queryLosts() {
        showView();
        BmobQuery<lov> query = new BmobQuery<lov>();
        query.order("-createdAt");// ����ʱ�併��
        query.findObjects(this, new FindListener<lov>() {

            @Override
            public void onSuccess(List<lov> losts) {
                // TODO Auto-generated method stub
                LostAdapter.clear();
                if (losts == null || losts.size() == 0) {
                    showErrorView(0);
                    LostAdapter.notifyDataSetChanged();
                    return;
                }
                progress.setVisibility(View.GONE);
                LostAdapter.addAll(losts);
                listview.setAdapter(LostAdapter);
            }

            @Override
            public void onError(int code, String arg0) {
                // TODO Auto-generated method stub
                showErrorView(0);
            }
        });
    }


    /**
     * ����������������ʱ����ʾ�Ľ��� showErrorView
     *
     * @return void
     * @throws
     */
    private void showErrorView(int tag) {
        progress.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        layout_no.setVisibility(View.VISIBLE);
        if (tag == 0) {
            tv_no.setText(getResources().getText(R.string.list_no_data_lost));
        } else {
            tv_no.setText(getResources().getText(R.string.list_no_data_found));
        }
    }

    private void showView() {
        listview.setVisibility(View.VISIBLE);
        layout_no.setVisibility(View.GONE);
    }

    @Override
    public void onEdit(View v) {
        // TODO Auto-generated method stub
        String tag = tv_lost.getTag().toString();
        Intent intent = new Intent(this, AddActivity.class);
        String title = "";
        String describe = "";
        String phone = "";
        if (tag.equals("Lost")) {
            title = LostAdapter.getItem(position).getTitle();
            describe = LostAdapter.getItem(position).getDescribe();
            phone = LostAdapter.getItem(position).getPhone();
        } else {
        }
        intent.putExtra("describe", describe);
        intent.putExtra("phone", phone);
        intent.putExtra("title", title);
        intent.putExtra("from", tag);
        startActivityForResult(intent, Constants.REQUESTCODE_ADD);
    }

    @Override
    public void onDelete(View v) {
        // TODO Auto-generated method stub
        String tag = tv_lost.getTag().toString();
        if (tag.equals("Lost")) {
            deleteLost();
        } else {

        }
    }

    private void deleteLost() {
        lov lost = new lov();
        lost.setObjectId(LostAdapter.getItem(position).getObjectId());
        lost.delete(this, new DeleteListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                LostAdapter.remove(position);
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
