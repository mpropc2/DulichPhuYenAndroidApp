package com.example.hongocman.doan2_android.Common;

/**
 * Created by HoNgocMan on 11/19/2016.
 */

public class APIUrl {
    public static String IP = "42.117.54.1";
    private final static String DOMAIN_URL = "http://"+ IP +"/api/";
    public final static String API_DANH_MUC = DOMAIN_URL + "DanhMuc/GetAll";
    public final static String API_DANH_MUC_BY_LOAI = DOMAIN_URL + "DanhMuc/GetDanhMucByLoai/";
    public final static String API_NEXT_5= DOMAIN_URL + "DanhMuc/GetNext5/";
    public final static String API_DANH_MUC_BY_ID = DOMAIN_URL + "DanhMuc/GetDanhMucById/";
    public final static String API_HINH_ANH = DOMAIN_URL + "HinhAnh/GetHinhAnhByMaDanhMuc/";
    public final static String API_BAN_DO = DOMAIN_URL + "BanDo/GetBanDoByMaDanhMuc/";
    public final static String API_ALL_TABLE = DOMAIN_URL + "Database";

//    public  static  String getIP(Context context){
//        SharedPreferences pref = context.getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//        String ip = pref.getString("ip", null);
//        if(ip== null) {
//
//        }
//
//        return pref.getString("ip", null);
//    }

}
