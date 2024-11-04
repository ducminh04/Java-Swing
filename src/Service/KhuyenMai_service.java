/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class KhuyenMai_service {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public ArrayList<KhuyenMai> getAllKhuyenMai2() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "UPDATE KhuyenMai\n"
                + "SET trangThai = CASE\n"
                + "    WHEN ngayBatDau > GETDATE() THEN 0\n"
                + "    WHEN ngayKetThuc < GETDATE() THEN 0\n"
                + "    ELSE 1\n"
                + "END;"
                + "select * from KhuyenMai";

        Connection conn = DBConnect.getConnectDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId_KhuyenMai(rs.getInt("id_KhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setNgayTao(rs.getDate("ngayTao").toLocalDate());
                km.setNguoiTao(rs.getString("nguoiTao"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setGiaTriGiam(rs.getInt("giaTriGiam"));
                km.setHoaDonToiThieu(rs.getFloat("hoaDonToiThieu"));
                km.setTrangThai(rs.getBoolean("trangThai"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addKhuyenMai(KhuyenMai km) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "INSERT INTO KhuyenMai"
                + "(tenKhuyenMai,soLuong,ngayTao,nguoiTao, ngayBatDau, ngayKetThuc, giaTriGiam, hoaDonToiThieu, trangThai )"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(km.getNgayBatDau().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(km.getNgayKetThuc().getTime());
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, km.getTenKhuyenMai());
            ps.setInt(2, km.getSoLuong());
            ps.setDate(3, Date.valueOf(km.getNgayTao()));
            ps.setString(4, km.getNguoiTao());
            ps.setDate(5, sqlStartDate);
            ps.setDate(6, sqlEndDate);
            ps.setInt(7, km.getGiaTriGiam());
            ps.setFloat(8, km.getHoaDonToiThieu());
            ps.setBoolean(9, km.getTrangThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateKhuyenMai(int id_km, KhuyenMai km) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "UPDATE KhuyenMai SET tenKhuyenMai=?,soLuong=?, ngayBatDau=?, ngayKetThuc=?, giaTriGiam=?,hoaDonToiThieu = ?, trangThai = ? WHERE id_KhuyenMai=?";
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(km.getNgayBatDau().getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(km.getNgayKetThuc().getTime());
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(8, km.getId_KhuyenMai());
            ps.setString(1, km.getTenKhuyenMai());
            ps.setInt(2, km.getSoLuong());
            ps.setDate(3, sqlStartDate);
            ps.setDate(4, sqlEndDate);
            ps.setInt(5, km.getGiaTriGiam());
            ps.setFloat(6, km.getHoaDonToiThieu());
            ps.setBoolean(7, km.getTrangThai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhuyenMai> timKiemsanPham(String tenkm) {
        ArrayList<KhuyenMai> listkm = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "SELECT * FROM KhuyenMai WHERE tenKhuyenMai LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenkm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId_KhuyenMai(rs.getInt("id_KhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setNgayTao(rs.getDate("ngayTao").toLocalDate());
                km.setNguoiTao(rs.getString("nguoiTao"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setGiaTriGiam(rs.getInt("giaTriGiam"));
                km.setHoaDonToiThieu(rs.getFloat("hoaDonToiThieu"));
                km.setTrangThai(rs.getBoolean("trangThai"));
                listkm.add(km);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listkm;
    }

}
