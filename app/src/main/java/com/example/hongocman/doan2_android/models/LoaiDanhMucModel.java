package com.example.hongocman.doan2_android.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table LOAI_DANH_MUC_MODEL.
 */
public class LoaiDanhMucModel {

    private long MaLoai;
    private String TenLoai;

    public LoaiDanhMucModel() {
    }

    public LoaiDanhMucModel(long MaLoai) {
        this.MaLoai = MaLoai;
    }

    public LoaiDanhMucModel(long MaLoai, String TenLoai) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
    }

    public long getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(long MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

}