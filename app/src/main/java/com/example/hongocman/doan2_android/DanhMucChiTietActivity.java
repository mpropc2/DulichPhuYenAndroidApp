package com.example.hongocman.doan2_android;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.hongocman.doan2_android.models.BanDoModel;
import com.example.hongocman.doan2_android.models.BanDoTable;
import com.example.hongocman.doan2_android.models.DanhMucApiModel;
import com.example.hongocman.doan2_android.models.Helper;
import com.example.hongocman.doan2_android.models.HinhAnhTable;

import layout.Fragment_bando;
import layout.Fragment_chitiet;
import layout.Fragment_video;

public class DanhMucChiTietActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static DanhMucApiModel danhMucApiModel;

    @Override
    protected void onStart() {
        super.onStart();
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_chi_tiet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Chi tiết");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    setActionBarTitle("Chi tiết");
                }
                if(position == 1){
                    setActionBarTitle("Bản đồ");
                }
                if(position == 2){
                    setActionBarTitle("Video");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //set tab icon
        tabLayout.getTabAt(0).setIcon(R.drawable.dictionary);
        tabLayout.getTabAt(1).setIcon(R.drawable.map_marker);
        tabLayout.getTabAt(2).setIcon(R.drawable.video);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            danhMucApiModel = (DanhMucApiModel)bundle.getParcelable("DANH_MUC_ITEM");
            BanDoTable banDoTable = new BanDoTable(DanhMucChiTietActivity.this);
            danhMucApiModel.setLstBanDo(banDoTable.getBanDoByDM(danhMucApiModel.getMaDanhMuc()));
            getSupportActionBar().setTitle(bundle.getString("TEN_LOAI"));
        }
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
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            /*Intent intent = new Intent(DanhMucChiTietActivity.this, DanhMuc_Activity.class);
            startActivity(intent);*/
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
                rootView = inflater.inflate(R.layout.fragment_danh_muc_chi_tiet, container, false);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_fragment_bando, container, false);
            }if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_fragment_video, container, false);
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Place   holderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment

                    return Fragment_chitiet.newInstance(danhMucApiModel, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return Fragment_bando.newInstance(danhMucApiModel, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return Fragment_video.newInstance(danhMucApiModel, "Page # 3");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
            }
            return null;
        }
    }
}
