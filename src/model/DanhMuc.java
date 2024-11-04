/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class DanhMuc {
   private int id_danhMuc;
   private String tenDanhMuc;

    public DanhMuc() {
    }

    public DanhMuc(int id_danhMuc, String tenDanhMuc) {
        this.id_danhMuc = id_danhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getId_danhMuc() {
        return id_danhMuc;
    }

    public void setId_danhMuc(int id_danhMuc) {
        this.id_danhMuc = id_danhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public String toString() {
        return tenDanhMuc;
    }
   
}
