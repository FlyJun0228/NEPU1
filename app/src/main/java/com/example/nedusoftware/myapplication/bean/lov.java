package com.example.nedusoftware.myapplication.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/25.
 */

public class lov  extends BmobObject {
    private String title;//����
    private String describe;//����
    private String phone;//��ϵ�ֻ�
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
