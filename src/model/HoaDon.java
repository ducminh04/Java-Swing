/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class HoaDon {

    private int id_hoaDon;
    private int id_KhachHang;
    private int id_NhanVien;
    private int id_KhuyenMai;
    private int id_HinhThucThanhToan;
    private float tienHang;
    private float giamGia;
    private float tongTien;
    private Date ngayTao;
    private boolean trangThai;
     public HoaDon() {
    }

    public HoaDon(int id_hoaDon, int id_KhachHang, int id_NhanVien, int id_KhuyenMai, int id_HinhThucThanhToan, float tienHang, float giamGia, float tongTien, Date ngayTao, boolean trangThai) {
        this.id_hoaDon = id_hoaDon;
        this.id_KhachHang = id_KhachHang;
        this.id_NhanVien = id_NhanVien;
        this.id_KhuyenMai = id_KhuyenMai;
        this.id_HinhThucThanhToan = id_HinhThucThanhToan;
        this.tienHang = tienHang;
        this.giamGia = giamGia;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public int getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(int id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public int getId_NhanVien() {
        return id_NhanVien;
    }

    public void setId_NhanVien(int id_NhanVien) {
        this.id_NhanVien = id_NhanVien;
    }

    public int getId_KhuyenMai() {
        return id_KhuyenMai;
    }

    public void setId_KhuyenMai(int id_KhuyenMai) {
        this.id_KhuyenMai = id_KhuyenMai;
    }

    public int getId_HinhThucThanhToan() {
        return id_HinhThucThanhToan;
    }

    public void setId_HinhThucThanhToan(int id_HinhThucThanhToan) {
        this.id_HinhThucThanhToan = id_HinhThucThanhToan;
    }

    public float getTienHang() {
        return tienHang;
    }

    public void setTienHang(float tienHang) {
        this.tienHang = tienHang;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id_hoaDon=" + id_hoaDon + ", id_KhachHang=" + id_KhachHang + ", id_NhanVien=" + id_NhanVien + ", id_KhuyenMai=" + id_KhuyenMai + ", id_HinhThucThanhToan=" + id_HinhThucThanhToan + ", tienHang=" + tienHang + ", giamGia=" + giamGia + ", tongTien=" + tongTien + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + '}';
    }
     

}
