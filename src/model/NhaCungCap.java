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
public class NhaCungCap {
    private int id_NCC;
    private String tenNCC;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private Boolean trangThai;

    public NhaCungCap() {
    }

    public NhaCungCap(int id_NCC, String tenNCC, LocalDate ngayTao, LocalDate ngaySua, Boolean trangThai) {
        this.id_NCC = id_NCC;
        this.tenNCC = tenNCC;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId_NCC() {
        return id_NCC;
    }

    public void setId_NCC(int id_NCC) {
        this.id_NCC = id_NCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenNCC;
    }

    
 

    
    
}
