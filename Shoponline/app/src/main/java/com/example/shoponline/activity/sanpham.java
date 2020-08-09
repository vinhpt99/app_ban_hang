package com.example.shoponline.activity;

import java.io.Serializable;

public class sanpham implements Serializable {
    public int id;
    public String tenSanpham;
    public Integer giaSanpham;
    public String hinhanhSanpham;
    public String motaSanpham;
    public int idloaiSanpham;

    public sanpham(int id, String tenSanpham, Integer giaSanpham, String hinhanhSanpham, String motaSanpham, int idloaiSanpham) {
        this.id = id;
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.hinhanhSanpham = hinhanhSanpham;
        this.motaSanpham = motaSanpham;
        this.idloaiSanpham = idloaiSanpham;
    }

//    public sanpham(int idsanpham, String tensanpham, Integer giasanpham, String hinhanhsanpham, int idloaisanpham) {
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanpham() {
        return tenSanpham;
    }

    public void setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public Integer getGiaSanpham() {
        return giaSanpham;
    }

    public void setGiaSanpham(Integer giaSanpham) {
        this.giaSanpham = giaSanpham;
    }

    public String getHinhanhSanpham() {
        return hinhanhSanpham;
    }

    public void setHinhanhSanpham(String hinhanhSanpham) {
        this.hinhanhSanpham = hinhanhSanpham;
    }

    public String getMotaSanpham() {
        return motaSanpham;
    }

    public void setMotaSanpham(String motaSanpham) {
        this.motaSanpham = motaSanpham;
    }

    public int getIdloaiSanpham() {
        return idloaiSanpham;
    }

    public void setIdloaiSanpham(int idloaiSanpham) {
        this.idloaiSanpham = idloaiSanpham;
    }
}
