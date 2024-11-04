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
import java.time.LocalDate;
import java.util.ArrayList;
import model.NhaCungCap;
import model.NhaXuatBan;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class NhaXuatBan_service {

    private Connection conn = DBConnect.getConnectDAO();

    public ArrayList<NhaXuatBan> getAllNhaXuatban() {
        ArrayList<NhaXuatBan> listNXB = new ArrayList<>();
        String sql = "SELECT id_NXB, tenNhaXuatBan, ngayTao, ngaySua, trangThai FROM nxb";
        try (
                Connection conn = DBConnect.getConnectDAO(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setId_NXB(rs.getInt("id_NXB"));
                nxb.setTenNhaXuatBan(rs.getString("tenNhaXuatBan"));

                nxb.setNgayTao(rs.getString("ngayTao"));
                nxb.setNgaySua(rs.getString("ngaySua"));

                nxb.setTragThai(rs.getBoolean("trangThai"));
                listNXB.add(nxb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNXB;
    }

    public ArrayList<NhaXuatBan> getAllByTT() {
        ArrayList<NhaXuatBan> listNXB = new ArrayList<>();
        String sql = "select * from nxb where trangThai like 1";
        Connection conn = DBConnect.getConnectDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setId_NXB(rs.getInt("id_NXB"));
                nxb.setTenNhaXuatBan(rs.getString("tenNhaXuatBan"));
                nxb.setNgayTao(rs.getString("ngayTao"));
                nxb.setNgaySua(rs.getString("ngaySua"));
                nxb.setTragThai(rs.getBoolean("trangThai"));
                listNXB.add(nxb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNXB;
    }

    public void addNhaXuatBan(NhaXuatBan nxb) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "INSERT INTO nxb"
                + "(tenNhaXuatBan,ngayTao, trangThai)"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nxb.getTenNhaXuatBan());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setBoolean(3, nxb.isTragThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateNhaXuatBan(NhaXuatBan nxb) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "UPDATE nxb SET tenNhaXuatBan=?,ngaySua=?,trangThai=? WHERE id_NXB=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(4, nxb.getId_NXB());
            ps.setString(1, nxb.getTenNhaXuatBan());
           ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setBoolean(3, nxb.isTragThai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NhaXuatBan> searchNhaXuatban(String tenNXB) {
        ArrayList<NhaXuatBan> listNXB = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "SELECT * FROM nxb WHERE tenNhaXuatban LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenNXB + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setId_NXB(rs.getInt("id_NXB"));
                nxb.setTenNhaXuatBan(rs.getString("tenNhaXuatBan"));
                nxb.setNgayTao(rs.getString("ngayTao"));
                nxb.setNgaySua(rs.getString("ngaySua"));
                nxb.setTragThai(rs.getBoolean("trangThai"));
                listNXB.add(nxb);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNXB;
    }

    public int getCount() {
        int count = 0;
        String sql = "select count(id_NXB) from nxb";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt("id_NXB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
