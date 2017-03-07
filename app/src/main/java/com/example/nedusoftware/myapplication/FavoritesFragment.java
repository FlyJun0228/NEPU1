package com.example.nedusoftware.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */
public class FavoritesFragment extends Fragment {
    private ListView mListView;
    private List<Bean> mDatas;
    public static FavoritesFragment newInstance(String param1) {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    public FavoritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favoriters, container, false);
        initDatas();
        mListView = (ListView) rootView.findViewById(R.id.id_listView);

        // mListView.setAdapter(mAdapterWithCommonViewHolder);
        mListView.setAdapter(new CommonAdapter<Bean>(getActivity(), mDatas, R.layout.item_download) {

            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                holder.setImageResource(R.id.id_icon,bean.getIcon())
                        .setText(R.id.id_name, bean.getName())
                        .setText(R.id.id_mark, bean.getMark())
                        .setText(R.id.id_mbs, bean.getMbs())
                        .setText(R.id.id_num, bean.getNum())
                        .setText(R.id.id_des, bean.getDes());
            }
        });
    return rootView;
    }


    private void initDatas() {
        mDatas = new ArrayList<Bean>();
        //Button button = null;
        Bean bean = new Bean(R.drawable.wchat, "微信", "4.9分", "34.06MB", "5.5亿次下载",
                "微信，超过3亿人使用，能够通过手机网络给好友发送语音、文字消息、表情和视频。");
        mDatas.add(bean);
        bean = new Bean(R.drawable.toutiao, "今日头条", "4.7分", "23.49MB", "5422万次下载",
                "阅读资讯平台，推荐新闻视频和话题！");
        mDatas.add(bean);
        bean = new Bean(R.drawable.youdao, "网易有道词典", "4.62分", "35.38MB", "2928万次下载",
                "多语种互译词典，离线也可轻松查询。");
        mDatas.add(bean);
        bean = new Bean(R.drawable.biaobiao, "超级课程表", "4.68分", "11.28MB", "781万次下载",
                "导入课程表，邂逅同课堂的ta吧。");
        mDatas.add(bean);
        bean = new Bean(R.drawable.zuoyebang, "作业帮", "4.53分", "15.24MB", "3557万次下载",
                "快准全的拍照解题工具，搞定作业！");
        mDatas.add(bean);
        bean = new Bean(R.drawable.gezi, "课程格子", "4.57分", "12.35MB", "719万次下载",
                "专为90后大学生设计的课表应用。");
        mDatas.add(bean);
        bean = new Bean(R.drawable.iqiyi, "爱奇艺", "4.37分", "20.89MB", "2.7亿次下载",
                "超清播放快速流畅，离线缓存边走边看。");
        mDatas.add(bean);
        bean = new Bean(R.drawable.kugou, "酷狗", "4.59分", "17.01MB", "3.1亿次下载",
                "高人气播放器，贴心音乐伴侣，海量音乐等你听！");
        mDatas.add(bean);
        bean = new Bean(R.drawable.zhifubao, "支付宝", "4.69分", "43.73MB", "1.6亿次下载",
                "吃喝玩乐尽享优惠i，跨行转账全免费！");
        mDatas.add(bean);
        bean = new Bean(R.drawable.eleme, "饿了么", "4.39分", "4.82MB", "2092万次下载",
                "直观方便，优惠多多的订外卖神器！");
        mDatas.add(bean);
    }
}


