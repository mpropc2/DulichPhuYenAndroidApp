package com.example.hongocman.doan2_android.models;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.hongocman.doan2_android.Adapter.DanhMucAdapter;
import com.example.hongocman.doan2_android.Adapter.RatedDanhMucAdapter;
import com.example.hongocman.doan2_android.DanhMuc_Activity;
import com.example.hongocman.doan2_android.RatedDanhMuc_Activity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoNgocMan on 11/28/2016.
 */

public class GetRatedDanhMucApiModelTask extends AsyncTask<String, Void, String> {
    ListView listView;
    Activity mContext;

    public GetRatedDanhMucApiModelTask(Activity context, ListView listView) {
        this.listView = listView;
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        if(!Helper.isNetworkConnected(mContext))
        {
            return null;
        }
        return Helper.docNoiDung_Tu_URL(urls[0]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        if(result != null) {
            try {
                JSONArray jsArr = new JSONArray(result);
                Gson g = new Gson();
                Type lstBanDo = new TypeToken<ArrayList<BanDoModel>>() {
                }.getType();
                Type lstHinhAnh = new TypeToken<ArrayList<HinhAnhModel>>() {
                }.getType();
                List<DanhMucApiModel> lst = new ArrayList<DanhMucApiModel>();//g.fromJson(jsObject.toString(), listType);

                //List<String> yourList = new Gson().fromJson(jsObject.get("dsfaf").toString(), listType);
                if (jsArr != null && jsArr.length() > 0) {
                    for (int i = 0; i < jsArr.length(); i++) {
                        JSONObject item = jsArr.getJSONObject(i);
                        DanhMucApiModel newItem = g.fromJson(item.toString(), DanhMucApiModel.class);
                        if (item.has("HinhDaiDien") && !item.isNull("HinhDaiDien")) {
                            List<HinhAnhModel> lstHA = g.fromJson(item.getJSONArray("HinhDaiDien").toString(), lstHinhAnh);
                            newItem.setLstHinhAnh(lstHA);
                        }
                        if (item.has("BanDo") && !item.isNull("BanDo")) {
                            List<BanDoModel> lstBD = g.fromJson(item.getJSONArray("BanDo").toString(), lstBanDo);
                            newItem.setLstBanDo(lstBD);
                        }
                        lst.add(newItem);

                    }
                }

                RatedDanhMucAdapter adapter = null;
                if (listView.getAdapter() != null) {
                    adapter = (RatedDanhMucAdapter) listView.getAdapter();
                    adapter.getLst().addAll(lst);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter = new RatedDanhMucAdapter(mContext, lst);
                    listView.setAdapter(adapter);
                }
            } catch (JSONException ex) {
                Log.d("JSON_ERROR", ex.getMessage());
            }
        } else {
            List<DanhMucApiModel> lst = new ArrayList<>();
            DanhMucTable danhMucTable = new DanhMucTable(mContext);
            BanDoTable banDoTable = new BanDoTable(mContext);
            int loaiDanhMuc = DanhMuc_Activity.convertRToDanhMuc(((RatedDanhMuc_Activity)mContext).loaiDanhMuc);
            int currentOffset = ((RatedDanhMuc_Activity)mContext).currentOffset;
            for (DanhMucModel dm : danhMucTable.getByLoai_FromOffset(loaiDanhMuc, currentOffset)) {
                DanhMucApiModel item = new DanhMucApiModel();
                item.setMaDanhMuc((int) dm.getMaDanhMuc());
                item.setTenDanhMuc(dm.getTenDanhMuc());
                item.setMoTa(dm.getMoTa());
                item.setSDT(dm.getSDT());
                item.setVideo(dm.getVideo());
                item.setLstBanDo(banDoTable.getBanDoByDM((int)dm.getMaDanhMuc()));
                lst.add(item);
            }

            DanhMucAdapter adapter = null;
            if (listView.getAdapter() != null) {
                adapter = (DanhMucAdapter) listView.getAdapter();
                adapter.getLst().addAll(lst);
                adapter.notifyDataSetChanged();
            } else {
                adapter = new DanhMucAdapter(mContext, lst);
                listView.setAdapter(adapter);
            }
        }

        ((RatedDanhMuc_Activity)mContext).pb.setVisibility(View.INVISIBLE);
    }
}
