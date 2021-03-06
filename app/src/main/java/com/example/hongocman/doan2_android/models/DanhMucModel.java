package com.example.hongocman.doan2_android.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DANH_MUC_MODEL.
 */
public class DanhMucModel {

    private long MaDanhMuc;
    private String TenDanhMuc;
    private String MoTa;
    private Integer Loai;
    private String Video;
    private String SDT;

    public DanhMucModel() {
    }

    public DanhMucModel(long MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public DanhMucModel(long MaDanhMuc, String TenDanhMuc, String MoTa, Integer Loai, String Video, String SDT) {
        this.MaDanhMuc = MaDanhMuc;
        this.TenDanhMuc = TenDanhMuc;
        this.MoTa = MoTa;
        this.Loai = Loai;
        this.Video = Video;
        this.SDT = SDT;
    }

    public long getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(long MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public Integer getLoai() {
        return Loai;
    }

    public void setLoai(Integer Loai) {
        this.Loai = Loai;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
