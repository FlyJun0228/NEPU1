
package com.example.nedusoftware.myapplication;

public class ChatMsgEntity {
    private static final String TAG = ChatMsgEntity.class.getSimpleName();

    private String name;

    private String date;

    private String text;
    private int ComMeg ;

    private boolean isComMeg = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMsgType(boolean b) {
        return isComMeg;
    }
    public int getMsgTypee(int b) {
        return ComMeg;
    }
    public void setMsgType(boolean isComMsg) {
        isComMeg = isComMsg;
    }
    public void setMsgTypee(int ComMsg) {
        ComMeg = ComMsg;
    }
    public ChatMsgEntity() {
    }

    public ChatMsgEntity (String name, String date, String text, int ComMsg) {
        super();
        this.name = name;
        this.date = date;
        this.text = text;
        this.ComMeg = ComMsg;
    }

}
