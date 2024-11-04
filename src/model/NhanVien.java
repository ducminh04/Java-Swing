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
public class NhanVien {

    private int id_NV;
    private String tenNhanVien;
    private Date ngaysinh;
    private boolean gioiTinh;
    private String email;
    private String sdt;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;
    private String username;
    private String passwork;
    private boolean vaiTro;

    public NhanVien() {
    }

    public NhanVien(int id_NV, String tenNhanVien, Date ngaysinh, boolean gioiTinh, String email, String sdt, Date ngayTao, Date ngaySua, boolean trangThai, String username, String passwork, boolean vaiTro) {
        this.id_NV = id_NV;
        this.tenNhanVien = tenNhanVien;
        this.ngaysinh = ngaysinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.username = username;
        this.passwork = passwork;
        this.vaiTro = vaiTro;
    }

    public int getId_NV() {
        return id_NV;
    }

    public void setId_NV(int id_NV) {
        this.id_NV = id_NV;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    

    public Object[] toDataRow() {
        return new Object[]{this.getId_NV(), this.getTenNhanVien(), this.getNgaysinh(),
            this.isGioiTinh() == true ? "Nam" : "Nu", this.getEmail(),
            this.getSdt(), this.getNgayTao(), this.getNgaySua(),
            this.isTrangThai() == true ? "Dang lam" : "Da nghi", this.getUsername(), this.getPasswork(),
            this.isVaiTro() == true ? "Quan Ly" : "Nhan Vien"};

    }

}
