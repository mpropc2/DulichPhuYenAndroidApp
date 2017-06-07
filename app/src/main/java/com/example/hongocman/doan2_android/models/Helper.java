package com.example.hongocman.doan2_android.models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hongocman.doan2_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Laptop on 4/28/2016.
 */
public class Helper {

    public  static final String TAG_DB_NAME = "DuLichPhuYen";
    public  static final String TAG_FORMATE_DATE_VN = "dd/MM/yyyy";
    public static Date convertStringToDate(String dateStr, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(dateStr);
            return  date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
    }

    public static String convertDateToString(Date date, String formatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        try {
            String datetime = dateFormat.format(date);
            return datetime;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
    }

    public static Calendar convertStringToCalendar(String EndDate, String formatStr){
        Calendar result = null;
        try {
            SimpleDateFormat  format = new SimpleDateFormat(formatStr);
            Date date;
            date = format.parse(EndDate);
            result = Calendar.getInstance();
            result.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertCalendarToString(Calendar cal, String format){
        try {
            if(cal != null){
                SimpleDateFormat format1 = new SimpleDateFormat(format);
                return format1.format(cal.getTime());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    public static void alertDialogResult(Context mContext, String html)
    {
        new AlertDialog.Builder(mContext).setTitle("Thông báo").setMessage(html)
                .setPositiveButton("OK", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MyApp.currentActivity().finish();
                        dialog.cancel();
                    }
                })
                .setCancelable(true).create().show();
    }

    public static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

            URL url = null;
            HttpURLConnection conn = null;
            try {
                url = new URL(theUrl);
                conn = (HttpURLConnection) url.openConnection();
                //conn.setDoInput(false);
                //conn.setDoOutput(true);
                int status = conn.getResponseCode();
                InputStreamReader input = new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8"));
                BufferedReader in =new BufferedReader(input);
                String aux;
                while ((aux = in.readLine()) != null) {
                    content.append(aux);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(conn != null)
                    conn.disconnect();
            }
            String str = content.toString();
            return str;
    }

    public  static Bitmap covertStringToBitmap(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static ProgressDialog showProgressBar(Context mContext, String message){
        return ProgressDialog.show(mContext, "", message, true, false);
    }

    public static Marker addMarkerOnMap(String title, LatLng coordinate, int drawableID, boolean isDraggable, GoogleMap googleMap){
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(coordinate);
        markerOption.title(title);
        markerOption.icon(BitmapDescriptorFactory.fromResource(drawableID));
        if(isDraggable){
            markerOption.draggable(true);
        }
        Marker startPerc = googleMap.addMarker(markerOption);
        return startPerc;
    }


    public static void animeToLocation(GoogleMap map, LatLng point){
        if(point != null){
            if(map != null)
                map.moveCamera(CameraUpdateFactory.newLatLng(point));
        }
    }

    /**
     * zoom map to specific size
     */
    public static void setMapZoom (int zoomSize, GoogleMap googleMap){
        if(googleMap != null)
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomSize));
    }

    public static void alertDialog(String html, Context mContext)
    {
        new AlertDialog.Builder(mContext).setTitle("Thông Báo").setMessage(html)
                .setPositiveButton(R.string.lbl_ok, null)
                .setCancelable(true).create().show();
    }
}

