package com.example.hongocman.doan2_android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HoNgocMan on 11/19/2016.
 */

public class DanhMucApiModel  implements Parcelable {
    String TenDanhMuc;
    String MoTa;
    String SDT;
    int Loai;
    int DanhGia;

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    String Video;
    List<HinhAnhModel> lstHinhAnh;
    List<BanDoModel> lstBanDo;

    protected DanhMucApiModel(Parcel in) {
        TenDanhMuc = in.readString();
        MoTa = in.readString();
        SDT = in.readString();
        Loai = in.readInt();
        DanhGia = in.readInt();
        Video = in.readString();
        MaDanhMuc = in.readInt();
    }

    public DanhMucApiModel(){}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TenDanhMuc);
        dest.writeString(MoTa);
        dest.writeString(SDT);
        dest.writeInt(Loai);
        dest.writeInt(DanhGia);
        dest.writeString(Video);
        dest.writeInt(MaDanhMuc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DanhMucApiModel> CREATOR = new Creator<DanhMucApiModel>() {
        @Override
        public DanhMucApiModel createFromParcel(Parcel in) {
            return new DanhMucApiModel(in);
        }

        @Override
        public DanhMucApiModel[] newArray(int size) {
            return new DanhMucApiModel[size];
        }
    };

    public List<BanDoModel> getLstBanDo() {
        return lstBanDo;
    }

    public void setLstBanDo(List<BanDoModel> lstBanDo) {
        this.lstBanDo = lstBanDo;
    }

    public List<HinhAnhModel> getLstHinhAnh() {
        return lstHinhAnh;
    }

    public void setLstHinhAnh(List<HinhAnhModel> lstHinhAnh) {
        this.lstHinhAnh = lstHinhAnh;
    }

    int MaDanhMuc;

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getSDT() {

        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMoTa() {

        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
    }

}
