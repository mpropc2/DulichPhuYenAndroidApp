package com.example.hongocman.doan2_android.models;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hongocman.doan2_android.Common.APIUrl;
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

/**
 * Created by HoNgocMan on 11/27/2016.
 */

public class BanDoTask extends AsyncTask<Integer, Void, String> {
    Fragment_bando fragment;

    public BanDoTask(Fragment_bando fragment){
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(Integer... params) {
        String url = APIUrl.API_BAN_DO + params[0];
        String str = Helper.docNoiDung_Tu_URL(url);
        return str;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            List<BanDoModel> lstBanDo = new ArrayList<>();
            JSONArray jsArr = new JSONArray(result);
            Gson g = new Gson();
            Type lstbando = new TypeToken<ArrayList<BanDoModel>>(){}.getType();

            if(jsArr != null && jsArr.length() > 0){
                lstBanDo = g.fromJson(result, lstbando);
                if(lstBanDo != null)
                    paintMarker(fragment.mMap, lstBanDo);
            }

        }catch (JSONException ex){
            Log.d("JSON_ERROR", ex.getMessage());
        }
    }

    private void paintMarker(GoogleMap googleMap, List<BanDoModel> lstBanDo){
        if(googleMap != null){
            // Add a marker in Sydney and move the camera
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            for(int i = 0; i < lstBanDo.size(); i++){
                LatLng sydney = new LatLng(lstBanDo.get(i).getLatitude(), lstBanDo.get(i).getLongitude());
                MarkerOptions marker = new MarkerOptions().position(sydney).title(fragment.danhMucApiModel.getTenDanhMuc());
                fragment.haspMap.put(marker, lstBanDo.get(i));
                googleMap.addMarker(marker);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
            }
        }


    }
}
