/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author HP
 */
public class TacGia {
    private int id_tacGia;
    private String tenTacGia;
    private Date  namSinh;
    private Date namMat;
    private boolean gioiTinh;
    private String queQuan;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public TacGia() {
    }

    public TacGia(int id_tacGia, String tenTacGia, Date namSinh, Date namMat, boolean gioiTinh, String queQuan, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id_tacGia = id_tacGia;
        this.tenTacGia = tenTacGia;
        this.namSinh = namSinh;
        this.namMat = namMat;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId_tacGia() {
        return id_tacGia;
    }

    public void setId_tacGia(int id_tacGia) {
        this.id_tacGia = id_tacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public Date getNamMat() {
        return namMat;
    }

    public void setNamMat(Date namMat) {
        this.namMat = namMat;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
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

    

    @Override
    public String toString() {
        return tenTacGia;
    }
    

    
    

}