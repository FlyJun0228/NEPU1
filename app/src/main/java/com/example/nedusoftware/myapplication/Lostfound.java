package com.example.nedusoftware.myapplication;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
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

import com.example.nedusoftware.myapplication.adapter.BaseAdapterHelper;
import com.example.nedusoftware.myapplication.adapter.QuickAdapter;
import com.example.nedusoftware.myapplication.adapter.base.EditPopupWindow;
import com.example.nedusoftware.myapplication.bean.Found;
import com.example.nedusoftware.myapplication.bean.Lost;
import com.example.nedusoftware.myapplication.config.Constants;
import com.example.nedusoftware.myapplication.i.IPopupItemClick;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.nedusoftware.myapplication.R.id.tv_describe;
import static com.example.nedusoftware.myapplication.R.id.tv_photo;
import static com.example.nedusoftware.myapplication.R.id.tv_title;

public class Lostfound extends BaseActivity implements View.OnClickListener,
        IPopupItemClick, AdapterView.OnItemLongClickListener {

    RelativeLayout layout_action;//
    LinearLayout layout_all;
    TextView tv_lost;
    ListView listview;
    Button btn_add;

    protected QuickAdapter<Lost> LostAdapter;// ʧ��

    protected QuickAdapter<Found> FoundAdapter;// ����

    private Button layout_found;
    private Button layout_lost;
    PopupWindow morePop;

    RelativeLayout progress;
    LinearLayout layout_no;
    TextView tv_no;

    @Override
    public void setContentView() {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        // TODO Auto-generated method stub
        progress = (RelativeLayout) findViewById(R.id.progress);
        layout_no = (LinearLayout) findViewById(R.id.layout_no);
        tv_no = (TextView) findViewById(R.id.tv_no);

        layout_action = (RelativeLayout) findViewById(R.id.layout_action);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);
        // Ĭ����ʧ�����
        tv_lost = (TextView) findViewById(R.id.tv_lost);
        tv_lost.setTag("丢失");
        listview = (ListView) findViewById(R.id.list_lost);
        btn_add = (Button) findViewById(R.id.btn_add);
        // ��ʼ����������
        initEditPop();
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
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("from", tv_lost.getTag().toString());
            startActivityForResult(intent, Constants.REQUESTCODE_ADD);
        } else if (v == layout_found) {
            changeTextView(v);
            morePop.dismiss();
            queryFounds();
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
            LostAdapter = new QuickAdapter<Lost>(this, R.layout.item_list) {
                @Override
                protected void convert(BaseAdapterHelper helper, Lost lost) {
                    helper.setText(tv_title, lost.getTitle())
                            .setText(tv_describe, lost.getDescribe())
                            .setText(tv_photo, lost.getPhone());
                }
            };
        }

        if (FoundAdapter == null) {
            FoundAdapter = new QuickAdapter<Found>(this, R.layout.item_list) {
                @Override
                protected void convert(BaseAdapterHelper helper, Found found) {
                    helper.setText(tv_title, found.getTitle())
                            .setText(tv_describe, found.getDescribe())
                            .setText(tv_photo, found.getPhone());
                }
            };
        }
        listview.setAdapter(LostAdapter);
        // Ĭ�ϼ���ʧ�����
        queryLosts();
    }

    private void changeTextView(View v) {
        if (v == layout_found) {
            tv_lost.setTag("已找到");
            tv_lost.setText("已找到");
        } else {
            tv_lost.setTag("丢失");
            tv_lost.setText("丢失");
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
        // ����Ч�� �Ӷ�������
        morePop.setAnimationStyle(R.style.MenuPop);
        morePop.showAsDropDown(layout_action, 0, -dip2px(this, 2.0F));
    }

    private void initEditPop() {
        mPopupWindow = new EditPopupWindow(this, 200, 48);
        mPopupWindow.setOnPopupItemClickListner(this);
    }

    EditPopupWindow mPopupWindow;
    int position;

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
        // TODO Auto-generated method stub
        position = arg2;
        int[] location = new int[2];
        arg1.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(arg1, Gravity.RIGHT | Gravity.TOP,
                location[0], getStateBar() + location[1]);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.REQUESTCODE_ADD:// ��ӳɹ�֮��Ļص�
                String tag = tv_lost.getTag().toString();
                if (tag.equals("丢失")) {
                    queryLosts();
                } else {
                    queryFounds();
                }
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
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.order("-createdAt");// ����ʱ�併��
        query.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> losts, BmobException e) {
                if (e == null) {
                    // TODO Auto-generated method stub
                    LostAdapter.clear();
                    FoundAdapter.clear();
                    if (losts == null || losts.size() == 0) {
                        showErrorView(0);
                        LostAdapter.notifyDataSetChanged();
                        return;
                    }
                    progress.setVisibility(View.GONE);
                    LostAdapter.addAll(losts);
                    listview.setAdapter(LostAdapter);
                } else {
                    showErrorView(0);
                }
            }
        });
    }

    public void queryFounds() {
        showView();
        BmobQuery<Found> query = new BmobQuery<Found>();
        query.order("-createdAt");// ����ʱ�併��
        query.findObjects(new FindListener<Found>() {

            @Override
            public void done(List<Found> arg0, BmobException e) {
                if (e == null) {
                    // TODO Auto-generated method stub
                    LostAdapter.clear();
                    FoundAdapter.clear();
                    if (arg0 == null || arg0.size() == 0) {
                        showErrorView(1);
                        FoundAdapter.notifyDataSetChanged();
                        return;
                    }
                    FoundAdapter.addAll(arg0);
                    listview.setAdapter(FoundAdapter);
                    progress.setVisibility(View.GONE);
                } else {
                    showErrorView(1);
                }
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
        if (tag.equals("丢失")) {
            title = LostAdapter.getItem(position).getTitle();
            describe = LostAdapter.getItem(position).getDescribe();
            phone = LostAdapter.getItem(position).getPhone();
        } else {
            title = FoundAdapter.getItem(position).getTitle();
            describe = FoundAdapter.getItem(position).getDescribe();
            phone = FoundAdapter.getItem(position).getPhone();
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
        if (tag.equals("丢失信息")) {
            deleteLost();
        } else {
            deleteLost();
        }
    }

    private void deleteLost() {
        Lost lost = new Lost();
        lost.setObjectId(LostAdapter.getItem(position).getObjectId());
        lost.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    LostAdapter.remove(position);
                }
            }
        });
    }


}
