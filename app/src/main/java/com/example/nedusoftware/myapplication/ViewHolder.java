package com.example.nedusoftware.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ning on 2016/7/15.
 */
public class ViewHolder {
    private SparseArray<View>mViews;
    private int mi;
    private View mview;

    public ViewHolder(Context context, ViewGroup viewGroup, int layoutId, int i){
        this.mi=i;
        this.mViews=new SparseArray<View>();
        mview= LayoutInflater.from(context).inflate(layoutId,viewGroup,false);
        mview.setTag(this);

    }

    public static ViewHolder get(Context context, View view, ViewGroup viewGroup, int layoutId, int i){
        if (view == null) {
            return new ViewHolder(context,viewGroup,layoutId,i);
        }else {
            ViewHolder holder= (ViewHolder) view.getTag();
            holder.mi=i;
            return holder;
        }
    }

    //通过viewId获取控件
    public <T extends View> T getView(int viewId){
        View viewd=  mViews.get(viewId);
        if (viewd == null) {
            viewd=  mview.findViewById(viewId);
            mViews.put(viewId,viewd);
        }
        return (T) viewd;
    }

    public View getview() {
        return mview;
    }

    //为TextView设置值的方法
    public ViewHolder setText(int viewId, String text){
        TextView tv=getView(viewId);
        tv.setText(text);
        return this;
    }

    //为ImageView设置值(Resource)
    public ViewHolder setImageResource(int viewId, int resId){
        ImageView view=getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    //为ImageView设置值(Bitmap)
    public ViewHolder setImageBitamp(int viewId, Bitmap bitmap){
        ImageView view=getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
    //为ImageView设置值(URL,加载网络图片)
    public ViewHolder setImageURL(int viewId, String url){
        ImageView view=getView(viewId);
        //Imageloader.getInstance.loadImg(view,url);
        return this;
    }

}
