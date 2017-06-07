package com.example.hongocman.doan2_android.Adapter;

/**
 * Created by HoNgocMan on 11/15/2016.
 */

public class GvMenuItem {
    int title;
    int img;

    public  GvMenuItem(int title, int img){
        this.title = title;
        this.img = img;
    }
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }



    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
