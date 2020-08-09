package com.example.shoponline.activity;

public class loaiSP
{
    private int id;
    private String tenSp;
    private String hinhanhSp;

    public loaiSP(int id, String tenSp, String hinhanhSp) {
        this.id = id;
        this.tenSp = tenSp;
        this.hinhanhSp = hinhanhSp;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getHinhanhSp() {
        return hinhanhSp;
    }

    public void setHinhanhSp(String hinhanhSp) {
        this.hinhanhSp = hinhanhSp;
    }
}
