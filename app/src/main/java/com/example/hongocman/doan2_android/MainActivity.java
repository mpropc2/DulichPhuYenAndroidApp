package com.example.hongocman.doan2_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.hongocman.doan2_android.Adapter.DanhMucAdapter;
import com.example.hongocman.doan2_android.Adapter.GvMenuItem;
import com.example.hongocman.doan2_android.Adapter.MainGridMenuAdapter;
import com.example.hongocman.doan2_android.Common.APIUrl;
import com.example.hongocman.doan2_android.models.DanhMucModel;
import com.example.hongocman.doan2_android.models.DanhMucTable;
import com.example.hongocman.doan2_android.models.SynchronyzeDatabaseTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    GridView gv;
    ArrayList<GvMenuItem> gvMenuItemList;
    int[] title = new int[]{R.string.gioi_thieu_menu, R.string.danh_lam, R.string.di_tich, R.string.le_hoi, R.string.am_thuc,
            R.string.lang_nghe, R.string.nha_hang, R.string.khach_sang, R.string.mua_sam, R.string.hot_line, R.string.about_us};
    int[] img = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Have a nice Trip in Phu Yen!");

        //khoi tao gridview cho phan menu chinh
        gv = (GridView) findViewById(R.id.gvMenu);

        gvMenuItemList = new ArrayList<GvMenuItem>();
        for(int i = 0; i < 11; i++){
            gvMenuItemList.add(new GvMenuItem(title[i], img[i]));
        }

        MainGridMenuAdapter adapter = new MainGridMenuAdapter(this, gvMenuItemList);
        gv.setAdapter(adapter);

        //set gridview_item_onclickListener
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GvMenuItem gvMenuItem = (GvMenuItem)gv.getAdapter().getItem(position);
                if(gvMenuItem.getTitle() == R.string.about_us) {
                    Intent intent = new Intent(MainActivity.this, AboutUs_Activity.class);
                    startActivity(intent);
                }
                else if(gvMenuItem.getTitle() == R.string.gioi_thieu_menu){
                    Intent intent = new Intent(MainActivity.this, GioiThieu_Activity.class);
                    startActivity(intent);
                }else if(gvMenuItem.getTitle() == R.string.nha_hang || gvMenuItem.getTitle() == R.string.khach_sang){
                    Intent intent = new Intent(MainActivity.this, RatedDanhMuc_Activity.class);
                    intent.putExtra("LOAI_DANH_MUC", gvMenuItem.getTitle());
                    intent.putExtra("TEN_LOAI", getString(gvMenuItem.getTitle()));
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, DanhMuc_Activity.class);
                    intent.putExtra("LOAI_DANH_MUC", gvMenuItem.getTitle());
                    intent.putExtra("TEN_LOAI", getString(gvMenuItem.getTitle()));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_danh_muc_chi_tiet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new SynchronyzeDatabaseTask(MainActivity.this).execute(APIUrl.API_ALL_TABLE);
            return true;
        }

        if (id == R.id.ip_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

