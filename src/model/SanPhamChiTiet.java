/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class SanPhamChiTiet {
    private int id_SanPhamChiTiet ;
    private int id_NXB ;
    private int id_TacGia ;
    private int id_NhaCungCap ;
    private int id_SanPham ;
    private int id_theLoai;
    private float giaBan ;
    private int  soLuong ;
    private Date ngayTao ;
    private Date ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private boolean trangThai ;
    private int id_Danhmuc;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int id_SanPhamChiTiet, int id_NXB, int id_TacGia, int id_NhaCungCap, int id_SanPham, int id_theLoai, float giaBan, int soLuong, Date ngayTao, Date ngaySua, String nguoiTao, String nguoiSua, boolean trangThai, int id_Danhmuc) {
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
        this.id_NXB = id_NXB;
        this.id_TacGia = id_TacGia;
        this.id_NhaCungCap = id_NhaCungCap;
        this.id_SanPham = id_SanPham;
        this.id_theLoai = id_theLoai;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.trangThai = trangThai;
        this.id_Danhmuc = id_Danhmuc;
    }

    public int getId_Danhmuc() {
        return id_Danhmuc;
    }

    public void setId_Danhmuc(int id_Danhmuc) {
        this.id_Danhmuc = id_Danhmuc;
    }



    public int getId_SanPhamChiTiet() {
        return id_SanPhamChiTiet;
    }

    public void setId_SanPhamChiTiet(int id_SanPhamChiTiet) {
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
    }

    public int getId_NXB() {
        return id_NXB;
    }

    public void setId_NXB(int id_NXB) {
        this.id_NXB = id_NXB;
    }

    public int getId_TacGia() {
        return id_TacGia;
    }

    public void setId_TacGia(int id_TacGia) {
        this.id_TacGia = id_TacGia;
    }

    public int getId_NhaCungCap() {
        return id_NhaCungCap;
    }

    public void setId_NhaCungCap(int id_NhaCungCap) {
        this.id_NhaCungCap = id_NhaCungCap;
    }

    public int getId_SanPham() {
        return id_SanPham;
    }

    public void setId_SanPham(int id_SanPham) {
        this.id_SanPham = id_SanPham;
    }

    public int getId_theLoai() {
        return id_theLoai;
    }

    public void setId_theLoai(int id_theLoai) {
        this.id_theLoai = id_theLoai;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    
    
        public Object[] toDataRow(){
        return new Object[]
        {
            this.id_SanPhamChiTiet,this.id_SanPham  ,this.id_NXB,
            this.id_TacGia,this.id_NhaCungCap,this.ngayTao,
            this.ngaySua, this.nguoiTao,this.nguoiSua, 
            this.soLuong,this.giaBan, this.id_theLoai,
            this.trangThai == true ? "Dang ban" : "Ngung ban"
        };
    }

}
