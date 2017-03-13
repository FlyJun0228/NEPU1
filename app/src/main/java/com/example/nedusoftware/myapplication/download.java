package com.example.nedusoftware.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class download extends AppCompatActivity {
    private ListView mListView;
    private List<Bean> mDatas;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_favoriters);
        initDatas();
        initView();
    }
    public void launchAppDetail(String appPkg,String marketPkg){
        try{
            if (TextUtils.isEmpty(appPkg)) return;;

            Uri uri= Uri.parse("market://details?id="+appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_listView);

        // mListView.setAdapter(mAdapterWithCommonViewHolder);
        mListView.setAdapter(new CommonAdapter<Bean>(download.this, mDatas, R.layout.item_download) {

            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                holder.setImageResource(R.id.id_icon,bean.getIcon())
                        .setText(R.id.id_name, bean.getName())
                        .setText(R.id.id_mark, bean.getMark())
                        .setText(R.id.id_mbs, bean.getMbs())
                        .setText(R.id.id_num, bean.getNum())
                        .setText(R.id.id_des, bean.getDes());
                final Button button=holder.getView(R.id.id_button1);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            /*Intent intent =new Intent(download.this,sceneryActivity.class);
                            startActivity(intent);*/

                        //这里开始执行一个应用市场跳转逻辑，默认this为Context上下文对象
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("market://details?id=" + getPackageName())); //跳转到应用市场，非Google Play市场一般情况也实现了这个接口
//存在手机里没安装应用市场的情况，跳转会包异常，做一个接收判断
                        if (intent.resolveActivity(getPackageManager()) != null) { //可以接收
                            startActivity(intent);
                        } else { //没有应用市场，我们通过浏览器跳转到Google Play
                            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        }
                        Uri.parse("market://search?q=pub:Author Name"); //跳转到商店搜索界面，并搜索开发者姓名
                        Uri.parse("market://search?q=Keyword"); //跳转到商店搜索界面，并搜索关键词

                    }
                });
            }
        });
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