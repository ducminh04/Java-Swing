/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author HP
 */
public class KhuyenMai {

    private int id_KhuyenMai;
    private String tenKhuyenMai;
    private int soLuong;
    private LocalDate ngayTao;
    private String nguoiTao;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int giaTriGiam;
    private Float hoaDonToiThieu;
    private Boolean trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(int id_KhuyenMai, String tenKhuyenMai, int soLuong, LocalDate ngayTao, String nguoiTao, Date ngayBatDau, Date ngayKetThuc, int giaTriGiam, Float hoaDonToiThieu, Boolean trangThai) {
        this.id_KhuyenMai = id_KhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTriGiam = giaTriGiam;
        this.hoaDonToiThieu = hoaDonToiThieu;
        this.trangThai = trangThai;
    }

    public int getId_KhuyenMai() {
        return id_KhuyenMai;
    }

    public void setId_KhuyenMai(int id_KhuyenMai) {
        this.id_KhuyenMai = id_KhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(int giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public Float getHoaDonToiThieu() {
        return hoaDonToiThieu;
    }

    public void setHoaDonToiThieu(Float hoaDonToiThieu) {
        this.hoaDonToiThieu = hoaDonToiThieu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenKhuyenMai;
    }

   
    
}
