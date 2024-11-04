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
public class TheLoai {

    private int id_TheLoai;
    private String tenTheLoai;
    private String ngayTao;
    private String ngaySua;
    private boolean trangThai;

    public TheLoai() {
    }

    public TheLoai(int id_TheLoai, String tenTheLoai, String ngayTao, String ngaySua, boolean trangThai) {
        this.id_TheLoai = id_TheLoai;
        this.tenTheLoai = tenTheLoai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId_TheLoai() {
        return id_TheLoai;
    }

    public void setId_TheLoai(int id_TheLoai) {
        this.id_TheLoai = id_TheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
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
        return tenTheLoai;
    }

    
    
}
