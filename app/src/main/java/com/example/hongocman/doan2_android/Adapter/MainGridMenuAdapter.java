package com.example.hongocman.doan2_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongocman.doan2_android.R;

import java.util.ArrayList;

/**
 * Created by HoNgocMan on 11/15/2016.
 */

public class MainGridMenuAdapter extends BaseAdapter {
    Context context;
    ArrayList<GvMenuItem> mList;

    public MainGridMenuAdapter(Context context, ArrayList<GvMenuItem> lst){
        this.context = context;
        this.mList = lst;
    }

    @Override
    public int getCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mList != null)
            return  mList.get(position);
        return  null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GvMenuItem gvmenuitem = (GvMenuItem)getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_menu_cell, null);
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.img_cell);
        img.setImageResource(getIcon(gvmenuitem.getTitle()));

        TextView lblTitle = (TextView)convertView.findViewById(R.id.lbl_title);
        lblTitle.setText(gvmenuitem.getTitle());
        return convertView;
    }


    private int getIcon(int loaidanhmuc){
        if(loaidanhmuc == R.string.gioi_thieu_menu)
            return R.mipmap.gioithieu;
        if(loaidanhmuc == R.string.danh_lam)
            return R.mipmap.danhlam;
        if(loaidanhmuc == R.string.di_tich)
            return R.mipmap.ditich;
        if(loaidanhmuc == R.string.le_hoi)
            return R.mipmap.lehoi;
        if(loaidanhmuc == R.string.am_thuc)
            return R.mipmap.dacsan;
        if(loaidanhmuc == R.string.lang_nghe)
            return R.mipmap.langnghe;
        if(loaidanhmuc == R.string.nha_hang)
            return R.mipmap.nhahang;
        if(loaidanhmuc == R.string.khach_sang)
            return R.mipmap.khachsan;
        if(loaidanhmuc == R.string.mua_sam)
            return R.mipmap.giaitri;
        if(loaidanhmuc == R.string.hot_line)
            return R.mipmap.hotline;
        if(loaidanhmuc == R.string.about_us)
            return R.mipmap.aboutus;
        return 0;
    }
}
