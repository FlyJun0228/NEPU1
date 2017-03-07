package com.example.nedusoftware.myapplication;

/**
 * Created by Ning on 2016/7/15.
 */
public class Bean {
    private int id_icon;
    private String name;
    private String mark;
    private String mbs;
    private String num;
    private String des;
   // private Button button;

    public void setIcon(int id_icon) {
        this.id_icon = id_icon;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setMbs(String mbs) {
        this.mbs = mbs;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setDes(String des) {
        this.des = des;
    }

   /* public void setButton(Button button) {
        this.button = button;
    }*/


    public int getIcon() {

        return id_icon;
    }

    public String getName() {
        return name;
    }

    public String getMark() {
        return mark;
    }

    public String getMbs() {
        return mbs;
    }

    public String getNum() {
        return num;
    }

    public String getDes() {
        return des;
    }

   /* public Button getButton() {
        return button;
    }*/


    public Bean(int id_icon, String name, String mark, String mbs, String num, String des) {

        this.id_icon=id_icon;
        this.name = name;
        this.mark = mark;
        this.mbs = mbs;
        this.num = num;
        this.des = des;
        //this.button=button;
    }

}
