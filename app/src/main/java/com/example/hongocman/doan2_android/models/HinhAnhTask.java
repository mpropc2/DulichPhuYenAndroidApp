package com.example.hongocman.doan2_android.models;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.hongocman.doan2_android.Common.APIUrl;
import com.example.hongocman.doan2_android.LazyloadFiles.ImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import layout.Fragment_bando;
import layout.Fragment_chitiet;

/**
 * Created by HoNgocMan on 11/27/2016.
 */

public class HinhAnhTask extends AsyncTask<Integer, Void, String> {
    Fragment_chitiet fragment;
    ImageView img;

    public HinhAnhTask(Fragment_chitiet fragment, ImageView img){
        this.fragment = fragment;
        this.img = img;
    }

    @Override
    protected String doInBackground(Integer... params) {
        String url = APIUrl.API_HINH_ANH + params[0];
        String str = Helper.docNoiDung_Tu_URL(url);;
        return str;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            List<HinhAnhModel> lstHinhAnh = new ArrayList<>();
            JSONArray jsArr = new JSONArray(result);
            Gson g = new Gson();
            Type lsthinhanh = new TypeToken<ArrayList<HinhAnhModel>>(){}.getType();

            if(jsArr != null && jsArr.length() > 0){
                lstHinhAnh = g.fromJson(result, lsthinhanh);
                fragment.danhMucApiModel.setLstHinhAnh(lstHinhAnh);
            }


            ImageLoader imageLoader = new ImageLoader(fragment.getContext());
            if(fragment.danhMucApiModel.getLstHinhAnh() != null && fragment.danhMucApiModel.getLstHinhAnh().size() != 0)
                imageLoader.displayImage(fragment.danhMucApiModel.getLstHinhAnh().get(fragment.currentIndex).getDuongDan(), img);

        }catch (JSONException ex){
            Log.d("JSON_ERROR", ex.getMessage());
        }
    }
}
