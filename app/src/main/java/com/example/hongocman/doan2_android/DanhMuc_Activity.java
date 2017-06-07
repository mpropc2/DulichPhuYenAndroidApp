package com.example.hongocman.doan2_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.hongocman.doan2_android.Common.APIUrl;
import com.example.hongocman.doan2_android.LazyloadFiles.LazyLoader;
import com.example.hongocman.doan2_android.models.DanhMucApiModel;
import com.example.hongocman.doan2_android.models.GetDanhMucApiModelTask;
import com.example.hongocman.doan2_android.models.SynchronyzeDatabaseTask;

import java.util.ArrayList;
import java.util.List;

public class DanhMuc_Activity extends AppCompatActivity {
    private ListView lvDanhMuc;
    public long loaiDanhMuc;
    public int currentOffset = 0;
    public ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        lvDanhMuc = (ListView)findViewById(R.id.lvDanhMuc);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            loaiDanhMuc = bundle.getInt("LOAI_DANH_MUC");
            getSupportActionBar().setTitle(bundle.getString("TEN_LOAI"));
        }

        getDanhMuc();

        // CRUCIAL PART !! Add the LazyLoader as the onScrollListener for the ListView.
        lvDanhMuc.setOnScrollListener(new LazyLoader() {

            // This method is called when the user is nearing the end of the ListView
            // and the ListView is ready to add more items.
            @Override
            public void loadMore(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                // The ListView needs more data. So Fetch !!
                getDanhMuc();
            }
        });

    }



    public void onItemClick(int pos) {
        Intent intent = new Intent(DanhMuc_Activity.this, DanhMucChiTietActivity.class);
        DanhMucApiModel  item = (DanhMucApiModel)lvDanhMuc.getAdapter().getItem(pos);
        intent.putExtra("DANH_MUC_ITEM", item);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(DanhMuc_Activity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private void getDanhMuc(){
        pb.setVisibility(View.VISIBLE);
        int count = 0;
        if(lvDanhMuc.getAdapter() != null)
            count = lvDanhMuc.getAdapter().getCount() - 1;

        String url = APIUrl.API_NEXT_5 + convertRToDanhMuc(loaiDanhMuc) + "/" + count;
        currentOffset += count;
        new GetDanhMucApiModelTask(this, lvDanhMuc).execute(url);
    }

    public static int convertRToDanhMuc(long rCode){
        if(rCode == R.string.danh_lam)
            return 2;
        if(rCode == R.string.di_tich)
            return 3;
        if(rCode == R.string.le_hoi)
            return 4;
        if(rCode == R.string.am_thuc)
            return 5;
        if(rCode == R.string.lang_nghe)
            return 6;
        if(rCode == R.string.nha_hang)
            return 7;
        if(rCode == R.string.khach_sang)
            return 8;
        if(rCode == R.string.mua_sam)
            return 9;
        if(rCode == R.string.benh_vien)
            return 11;
        return 0;
    }
}
