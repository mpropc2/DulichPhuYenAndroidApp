package com.example.hongocman.doan2_android.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by HoNgocMan on 12/5/2016.
 */

public class BanDoTable {
    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private BanDoModelDao myDao;

    public BanDoTable(Context context){
        this.mContext = context;
        helper = new DaoMaster.OpenHelper(mContext, Helper.TAG_DB_NAME, null) {

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub

            }
        };
    }

    private void openDB(){
        try{
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            myDao = daoSession.getBanDoModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public List<BanDoModel> getBanDoByDM(int maDanhMuc)
    {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<BanDoModel> result = myDao.queryBuilder().where(BanDoModelDao.Properties.MaDanhMuc.eq(maDanhMuc)).list();
        closeDB();
        return result;
    }

    public long insert(BanDoModel item) {
        long result = 0;
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.insert(item);
        }catch (Exception e){
            myDao.update(item);
            result = item.getMaDiaDiem();
        }
        closeDB();
        return  result;
    }

    public long insert(List<BanDoModel> items) {
        int count = 0;
        if(items != null){
            for (BanDoModel item: items) {
                if(insert(item) > 0)
                    count++;
            }
        }
        return  count;
    }

    public void delete(BanDoModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void update(BanDoModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(BanDoModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<BanDoModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<BanDoModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public void xoaTatCa(){
        openDB();
        if (myDao == null){
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.deleteAll();
    }

    public BanDoModel getItem(String id){
        List<BanDoModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder().where(BanDoModelDao.Properties.MaDanhMuc.eq(id)).limit(1).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return  (result != null && result.size()> 0)? result.get(0):null;
    }
}
