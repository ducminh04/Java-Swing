/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class BanHangSanPham {
    private int id_SP;
    private String tenSP ; 
    private Float giaSP ; 
    private int soluong ;
    private boolean status;

    public BanHangSanPham() {
    }

    public BanHangSanPham(int id_SP, String tenSP, Float giaSP, int soluong) {
        this.id_SP = id_SP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soluong = soluong;
    }

    public int getId_SP() {
        return id_SP;
    }

    public void setId_SP(int id_SP) {
        this.id_SP = id_SP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Float getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(Float giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "BanHangSanPham{" + "id_SP=" + id_SP + ", tenSP=" + tenSP + ", giaSP=" + giaSP + ", soluong=" + soluong + '}';
    }
    
}
