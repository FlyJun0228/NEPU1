package com.example.nedusoftware.myapplication;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;


public class FindFragment extends Fragment {
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    List<Fragment> fragmentList;

    /**
     * 四个Fragment（页面）
     */
    Kuaidididian oneFragment;
    Zhoubianyinshi twoFragment;
    Zhoubianyouwan threeFragment;
    int screenWidth;
    //当前选中的项
    int currenttab = -1;
    //覆盖层
    ImageView imageviewOvertab;

    public static FindFragment newInstance(String param1) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public FindFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find, container, false);

        return rootView;
    }

}





