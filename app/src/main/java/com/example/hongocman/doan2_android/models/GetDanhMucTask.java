package com.example.hongocman.doan2_android.models;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.hongocman.doan2_android.Adapter.DanhMucAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoNgocMan on 11/27/2016.
 */

public class GetDanhMucTask extends AsyncTask<String, Void, String> {
    Fragment fragment;

    public GetDanhMucTask(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray jsArr = new JSONArray(result);
            Gson g = new Gson();
            Type lstBanDo = new TypeToken<ArrayList<BanDoModel>>(){}.getType();
            Type lstHinhAnh = new TypeToken<ArrayList<HinhAnhModel>>(){}.getType();

            if(jsArr != null && jsArr.length() > 0){
                for(int i=0;i<jsArr.length();i++){
                    JSONObject item = jsArr.getJSONObject(i);
                    DanhMucApiModel newItem =  g.fromJson(item.toString(), DanhMucApiModel.class);
                    if(item.has("HinhDaiDien") && !item.isNull("HinhDaiDien")){
                        List<HinhAnhModel> lstHA = g.fromJson(item.getJSONArray("HinhDaiDien").toString(), lstHinhAnh);
                        newItem.setLstHinhAnh(lstHA);
                    }
                    if(item.has("BanDo")&& !item.isNull("BanDo")){
                        List<BanDoModel> lstBD = g.fromJson(item.getJSONArray("BanDo").toString(), lstBanDo);
                        newItem.setLstBanDo(lstBD);
                    }

                }
            }
            //-> Chi tiet -> thong DanhMuc
            //-> DS Hinh anh-> DS HA theo Ma DM
            //-> BanDo
        }catch (JSONException ex){
            Log.d("JSON_ERROR", ex.getMessage());
        }
    }
}
