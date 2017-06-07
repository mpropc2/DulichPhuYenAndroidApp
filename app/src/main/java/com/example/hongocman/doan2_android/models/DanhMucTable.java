package com.example.hongocman.doan2_android.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by HoNgocMan on 11/17/2016.
 */

public class DanhMucTable {
    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DanhMucModelDao myDao;

    public DanhMucTable(Context context){
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
            myDao = daoSession.getDanhMucModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public long insert(DanhMucModel item) {
        long result = 0;
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.insert(item);
        }catch (Exception e){
            myDao.update(item);
            result = item.getMaDanhMuc();
        }
        closeDB();
        return  result;
    }

    public long insert(List<DanhMucModel> items) {
        int count = 0;
        if(items != null){
           for (DanhMucModel item: items) {
               if(insert(item) > 0)
                   count++;
           }
        }
        return  count;
    }

    public void delete(DanhMucModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void update(DanhMucModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(DanhMucModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<DanhMucModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<DanhMucModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public List<DanhMucModel> getByLoai_FromOffset(int loai, int offset){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<DanhMucModel> result = myDao.queryBuilder().where(DanhMucModelDao.Properties.Loai.eq(loai)).offset(offset).limit(7).list();
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

    public DanhMucModel getItem(String id){
        List<DanhMucModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder().where(DanhMucModelDao.Properties.MaDanhMuc.eq(id)).limit(1).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return  (result != null && result.size()> 0)? result.get(0):null;
    }
}
