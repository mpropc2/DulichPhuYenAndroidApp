package com.example.hongocman.doan2_android.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hongocman.doan2_android.DanhMuc_Activity;
import com.example.hongocman.doan2_android.LazyloadFiles.ImageLoader;
import com.example.hongocman.doan2_android.R;
import com.example.hongocman.doan2_android.RatedDanhMuc_Activity;
import com.example.hongocman.doan2_android.models.DanhMucApiModel;
import com.example.hongocman.doan2_android.models.HinhAnhModel;
import com.example.hongocman.doan2_android.models.PbAndImage;

import java.util.List;

/**
 * Created by HoNgocMan on 11/15/2016.
 */

public class RatedDanhMucAdapter extends BaseAdapter{
    List<DanhMucApiModel> lst;
    private Activity activity;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public RatedDanhMucAdapter(Activity activity, List<DanhMucApiModel> lst){
        this.activity = activity;
        this.lst = lst;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public List<DanhMucApiModel> getLst() {
        return lst;
    }

    public void setLst(List<DanhMucApiModel> lst) {
        this.lst = lst;
    }

    @Override
    public int getCount() {
        if(lst != null)
            return lst.size();
        return  0;
    }

    @Override
    public DanhMucApiModel getItem(int position) {
        if(lst != null)
            return  lst.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(lst != null && lst.get(position) != null)
            return  lst.get(position).getMaDanhMuc();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DanhMucApiModel item = lst.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_rated_danh_muc, null);
            holder.lblTenDanhMuc = (TextView)convertView.findViewById(R.id.tv_ten_danh_muc);
            holder.lblDiaChi = (TextView)convertView.findViewById(R.id.tvdia_chi);
            holder.img = (ImageView)convertView.findViewById(R.id.imgDanhMuc);
            holder.ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
            holder.lblNumberRateSrar = (TextView)convertView.findViewById(R.id.lbl_rating_number);

            convertView.setTag(holder);
        }
        holder = (ViewHolder)convertView.getTag();
        ///
        if(item.getLstBanDo()!=null && item.getLstBanDo().size() >0){
            holder.lblDiaChi.setText("Địa chỉ: " + item.getLstBanDo().get(0).getDiaChi());
        }

        holder.lblTenDanhMuc.setText(item.getTenDanhMuc());
        ImageView image = holder.img;
        holder.ratingBar.setRating(item.getDanhGia());
        holder.lblNumberRateSrar.setText(item.getDanhGia() + "");
       if (item.getLstHinhAnh() != null && item.getLstHinhAnh().size() > 0) {
           HinhAnhModel hinhAnh = item.getLstHinhAnh().get(0);
           if (hinhAnh.getDuongDan() != null) {
               imageLoader.displayImage(hinhAnh.getDuongDan(), image);
           }
       }
        convertView.setOnClickListener(new OnItemClickListener(position));
        return convertView;
    }

    private class OnItemClickListener implements OnClickListener {

        private int position;

        OnItemClickListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            RatedDanhMuc_Activity sct = (RatedDanhMuc_Activity) activity;
            sct.onItemClick(position);
        }
    }


    class ViewHolder{
        public ImageView img;
        public TextView lblTenDanhMuc;
        public TextView lblDiaChi;
        public TextView lblNumberRateSrar;
        public RatingBar ratingBar;
    }

}
