package com.example;
import de.greenrobot.daogenerator.*;

public class MyClass {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.example.hongocman.doan2_android.models");

        addLoaiDanhMuc(schema);
        addDanhMuc(schema);
        addHinhAnh(schema);
        addBanDo(schema);
        String path = "D:\\Do an 2\\MyApplication\\app\\src\\main\\java";
        new DaoGenerator().generateAll(schema, path);
    }
    //Loai Danh Muc
    private static void addLoaiDanhMuc(Schema schema) {
        Entity entity = schema.addEntity("LoaiDanhMucModel");
        entity.addLongProperty("MaLoai").primaryKey().notNull().autoincrement();
        entity.addStringProperty("TenLoai");
    }

    //Danh Muc
    private static void addDanhMuc(Schema schema){
        Entity entity = schema.addEntity("DanhMucModel");
        entity.addLongProperty("MaDanhMuc").primaryKey().notNull().autoincrement();
        entity.addStringProperty("TenDanhMuc");
        entity.addStringProperty("MoTa");
        entity.addIntProperty("Loai");
        entity.addStringProperty("Video");
        entity.addStringProperty("SDT");
    }

    //Hinh Anh
    private static void addHinhAnh(Schema schema){
        Entity entity = schema.addEntity("HinhAnhModel");
        entity.addLongProperty("MaHinhAnh").primaryKey().notNull().autoincrement();
        entity.addStringProperty("TieuDe");
        entity.addStringProperty("DuongDan");
        entity.addIntProperty("MaDanhMuc");
    }

    //Ban Do
    private static void addBanDo(Schema schema){
        Entity entity = schema.addEntity("BanDoModel");
        entity.addLongProperty("MaDiaDiem").primaryKey().notNull().autoincrement();
        entity.addStringProperty("DiaChi");
        entity.addStringProperty("LoaiDiaDiem");
        entity.addFloatProperty("latitude").notNull();
        entity.addFloatProperty("longitude").notNull();
        entity.addIntProperty("MaDanhMuc");
    }
}
