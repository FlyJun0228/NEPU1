package com.example.nedusoftware.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nedusoftware.myapplication.R;
import com.example.nedusoftware.myapplication.bean.ListBean;

import java.util.List;

/**
 * Created by NEDUsoftware on 2017/4/24.
 */

public class KdAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private List<ListBean> list;
    public KdAdapter(Context context, List<ListBean> list){
            this.minflater=LayoutInflater.from(context);
            this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        int pos=getCount()-position-1;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=minflater.inflate(R.layout.item_kuaidi,null);
            viewHolder.remark=(TextView)convertView.findViewById(R.id.t_remark);
            viewHolder.time=(TextView)convertView.findViewById(R.id.t_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.remark.setText((String)list.get(pos).getRemark());
        viewHolder.time.setText((String)list.get(pos).getDatetime());
        return convertView;
    }
    public class ViewHolder{
        public TextView remark;
        public TextView time;
    }
}
