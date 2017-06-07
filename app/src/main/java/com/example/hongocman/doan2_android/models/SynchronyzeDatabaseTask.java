package com.example.hongocman.doan2_android.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.hongocman.doan2_android.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoNgocMan on 12/5/2016.
 */

public class SynchronyzeDatabaseTask extends AsyncTask<String, Void, String>{
    private DanhMucTable danhMucTb;
    private BanDoTable banDoTb;
    private HinhAnhTable hinhAnhTb;
    private Context mContext;
    private ProgressDialog pd;

    public  SynchronyzeDatabaseTask(Context context)
    {
        this.mContext = context;

        danhMucTb = new DanhMucTable(mContext);
        banDoTb = new BanDoTable(mContext);
        hinhAnhTb = new HinhAnhTable(mContext);
    }

    protected void onPreExecute() {
        pd = Helper.showProgressBar(this.mContext, mContext.getResources().getString(R.string.msg_pd_search));
    }

    @Override
    protected String doInBackground(String... urls) {
        String str = Helper.docNoiDung_Tu_URL(urls[0]);
        return str;
    }

    @Override
    protected void onPostExecute(String result)
    {
        try {
            pd.dismiss();
            JSONObject allTable = new JSONObject(result);
            Gson g = new Gson();
            Type lstBanDoType = new TypeToken<ArrayList<BanDoModel>>(){}.getType();
            Type lstHinhAnhType = new TypeToken<ArrayList<HinhAnhModel>>(){}.getType();
            Type lstDanhMucType = new TypeToken<ArrayList<DanhMucModel>>(){}.getType();
            Type lstLoaiDanhMucType = new TypeToken<ArrayList<LoaiDanhMucModel>>(){}.getType();
            List<BanDoModel> lstBanDo = new ArrayList<>();
            List<DanhMucModel> lstDanhMuc = new ArrayList<>();
            List<HinhAnhModel> lstHinhAnh = new ArrayList<>();
            List<LoaiDanhMucModel> lstLoaiDanhMuc = new ArrayList<>();


            lstDanhMuc = g.fromJson(allTable.getJSONArray("ListDanhMuc").toString(), lstDanhMucType);
            lstHinhAnh = g.fromJson(allTable.getJSONArray("ListHinhAnh").toString(), lstHinhAnhType);
            lstLoaiDanhMuc = g.fromJson(allTable.getJSONArray("ListLoaiDanhMuc").toString(), lstLoaiDanhMucType);
            lstBanDo = g.fromJson(allTable.getJSONArray("ListBanDo").toString(), lstBanDoType);

            for (DanhMucModel dm:lstDanhMuc) {
                danhMucTb.insert(dm);
            }

            for (HinhAnhModel ha:lstHinhAnh) {
                hinhAnhTb.insert(ha);
            }

            for (BanDoModel bd:lstBanDo) {
                banDoTb.insert(bd);
            }
        }catch (JSONException ex){
            Log.d("JSON_ERROR", ex.getMessage());
        }

        CharSequence text = "Update completed!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(mContext, text, duration);
        toast.show();
    }
}
