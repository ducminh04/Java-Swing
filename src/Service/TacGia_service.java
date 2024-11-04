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
import model.TacGia;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class TacGia_service {

    public ArrayList<TacGia> getAllTacGia() {
        ArrayList<TacGia> listTG = new ArrayList<>();
        String sql = "select * from tacGia";

        try (Connection conn = DBConnect.getConnectDAO(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setId_tacGia(rs.getInt("id_TacGia"));
                tg.setTenTacGia(rs.getString("tenTacGia"));
                tg.setNamSinh(rs.getDate("namSinh"));
                tg.setNamMat(rs.getDate("namMat"));
                tg.setGioiTinh(rs.getBoolean("gioiTinh"));
                tg.setQueQuan(rs.getString("queQuan"));
                tg.setNgayTao(rs.getDate("ngayTao"));
                tg.setNgaySua(rs.getDate("ngaySua"));

                tg.setTrangThai(rs.getBoolean("trangThai"));
                listTG.add(tg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTG;
    }

    public ArrayList<TacGia> getAllByTT() {
        ArrayList<TacGia> listTG = new ArrayList<>();
        String sql = "SELECT * FROM tacGia WHERE trangThai = 1";

        try (Connection conn = DBConnect.getConnectDAO(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setId_tacGia(rs.getInt("id_TacGia"));
                tg.setTenTacGia(rs.getString("tenTacGia"));
                tg.setNamSinh(rs.getDate("namSinh"));
                tg.setNamMat(rs.getDate("namMat"));
                tg.setGioiTinh(rs.getBoolean("gioiTinh"));
                tg.setQueQuan(rs.getString("queQuan"));

                tg.setNgayTao(rs.getDate("ngayTao"));
                tg.setNgaySua(rs.getDate("ngaySua"));

                tg.setTrangThai(rs.getBoolean("trangThai"));
                listTG.add(tg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTG;
    }

    public void addTacGia(TacGia tg) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "INSERT INTO tacGia"
                + "(tenTacGia, namSinh, namMat, gioiTinh, queQuan, ngayTao,trangThai)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tg.getTenTacGia());
            ps.setDate(2, (Date) tg.getNamSinh());
            ps.setDate(3, (Date) tg.getNamMat());
            ps.setBoolean(4, tg.isGioiTinh());
            ps.setString(5, tg.getQueQuan());
            ps.setDate(6, (Date) tg.getNgayTao());
            ps.setBoolean(7, tg.isTrangThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTacGia(int id, TacGia tg) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "UPDATE tacGia SET namMat=?,ngaySua = ?, trangThai = ? WHERE id_TacGia=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(4, tg.getId_tacGia());
            ps.setDate(1, (Date) tg.getNamMat());
            ps.setDate(2, (Date) tg.getNgaySua());
            ps.setBoolean(3, tg.isTrangThai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TacGia> searchTacGia(String tenTG) {
        ArrayList<TacGia> listTG = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "select * from tacGia WHERE tenTacGia LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenTG + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setId_tacGia(rs.getInt("id_TacGia"));
                tg.setTenTacGia(rs.getString("tenTacGia"));
                tg.setNamSinh(rs.getDate("namSinh"));
                tg.setNamMat(rs.getDate("namMat"));
                tg.setGioiTinh(rs.getBoolean("gioiTinh"));
                tg.setQueQuan(rs.getString("queQuan"));
                Date ngayTao = rs.getDate("ngayTao");
                tg.setNgayTao(rs.getDate("ngayTao"));
                tg.setNgaySua(rs.getDate("ngaySua"));
                tg.setTrangThai(rs.getBoolean("trangThai"));
                listTG.add(tg);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTG;
    }
}
