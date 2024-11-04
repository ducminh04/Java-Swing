/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class BanHangGioHang {
    private int id_SPCT;
    private int id_sanPham;
    private int id_hoaDon;
    private String tenSanPham;
    private float giaBan;
    private int soLuong;
    private Float thanhTien;

    public BanHangGioHang() {
    }

    public BanHangGioHang(int id_SPCT, int id_sanPham, int id_hoaDon, String tenSanPham, float giaBan, int soLuong, Float thanhTien) {
        this.id_SPCT = id_SPCT;
        this.id_sanPham = id_sanPham;
        this.id_hoaDon = id_hoaDon;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public int getId_SPCT() {
        return id_SPCT;
    }

    public void setId_SPCT(int id_SPCT) {
        this.id_SPCT = id_SPCT;
    }

    public int getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(int id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    public int getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(int id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }
    public int ThanhTien(int giaban, int sL) {
        return giaban * sL;
    }

    @Override
    public String toString() {
        return "BanHangGioHang{" + "id_SPCT=" + id_SPCT + ", id_sanPham=" + id_sanPham + ", id_hoaDon=" + id_hoaDon + ", tenSanPham=" + tenSanPham + ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + '}';
    }

    
}
