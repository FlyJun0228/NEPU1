package com.example.nedusoftware.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Ning on 2016/7/16.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T>mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    public CommonAdapter(Context context, List<T>datas,int layoutId) {
        this.mContext=context;
        this.mDatas=datas;
        this.layoutId=layoutId;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder = ViewHolder.get(mContext, view, viewGroup, layoutId, i);

        convert(holder,getItem(i));
        return holder.getview();
    }

    public abstract void convert(ViewHolder holder,T t);
}
