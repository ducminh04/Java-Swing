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
public class NhaXuatBan {
    private int id_NXB;
    private String tenNhaXuatBan;
    private String ngayTao;
    private String ngaySua;
    private boolean tragThai;

    public NhaXuatBan() {
    }

    public NhaXuatBan(int id_NXB, String tenNhaXuatBan, String ngayTao, String ngaySua, boolean tragThai) {
        this.id_NXB = id_NXB;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tragThai = tragThai;
    }

    public int getId_NXB() {
        return id_NXB;
    }

    public void setId_NXB(int id_NXB) {
        this.id_NXB = id_NXB;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
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

    public boolean isTragThai() {
        return tragThai;
    }

    public void setTragThai(boolean tragThai) {
        this.tragThai = tragThai;
    }

   
        
    @Override
    public String toString() {
        return tenNhaXuatBan;
    }

   
    
}
