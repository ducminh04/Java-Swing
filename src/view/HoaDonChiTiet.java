/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class HoaDonChiTiet {

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    private int id_HoaDonChiTiet;
    private int id_HoaDon;
    private String id_SanPhamChiTiet;
    private double giaBan;
    private int soLuong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id_HoaDonChiTiet, int id_HoaDon, String id_SanPhamChiTiet, double giaBan, int soLuong) {
        this.id_HoaDonChiTiet = id_HoaDonChiTiet;
        this.id_HoaDon = id_HoaDon;
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public NumberFormat getCurrencyFormat() {
        return currencyFormat;
    }

    public void setCurrencyFormat(NumberFormat currencyFormat) {
        this.currencyFormat = currencyFormat;
    }

    public int getId_HoaDonChiTiet() {
        return id_HoaDonChiTiet;
    }

    public void setId_HoaDonChiTiet(int id_HoaDonChiTiet) {
        this.id_HoaDonChiTiet = id_HoaDonChiTiet;
    }

    public int getId_HoaDon() {
        return id_HoaDon;
    }

    public void setId_HoaDon(int id_HoaDon) {
        this.id_HoaDon = id_HoaDon;
    }

    public String getId_SanPhamChiTiet() {
        return id_SanPhamChiTiet;
    }

    public void setId_SanPhamChiTiet(String id_SanPhamChiTiet) {
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Object[] addRowHDCT() {
        return new Object[]{id_HoaDonChiTiet, id_HoaDon, id_SanPhamChiTiet, currencyFormat.format(giaBan), soLuong};
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "currencyFormat=" + currencyFormat + ", id_HoaDonChiTiet=" + id_HoaDonChiTiet + ", id_HoaDon=" + id_HoaDon + ", id_SanPhamChiTiet=" + id_SanPhamChiTiet + ", giaBan=" + giaBan + ", soLuong=" + soLuong + '}';
    }

}
