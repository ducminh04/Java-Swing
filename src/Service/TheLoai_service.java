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
import model.NhaXuatBan;
import model.TheLoai;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class TheLoai_service {

    public ArrayList<TheLoai> getAllTheLoai() {
        ArrayList<TheLoai> listTL = new ArrayList<>();
        String sql = "SELECT id_TheLoai, tenTheLoai, ngayTao, ngaySua FROM TheLoai";
        try (
                Connection conn = DBConnect.getConnectDAO();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TheLoai the_loai = new TheLoai();
                the_loai.setId_TheLoai(rs.getInt("id_TheLoai"));
                the_loai.setTenTheLoai(rs.getString("tenTheLoai"));

                the_loai.setNgayTao(rs.getString("ngayTao"));
                the_loai.setNgaySua(rs.getString("ngaySua"));
                listTL.add(the_loai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTL;

    }

    public Integer addTheLoai(TheLoai theLoai) {
        Integer row = null;
        try {
            String sql = "INSERT INTO TheLoai (tenTheLoai, ngayTao) VALUES (?, ?)";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, theLoai.getTenTheLoai());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            row = ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (Exception e) {
            // Xử lý exception
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateTheLoai(TheLoai theLoai) {
        Integer row = null;
        try {
            String sql = "UPDATE TheLoai SET tenTheLoai = ?, ngaySua = ? WHERE id_TheLoai = ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, theLoai.getTenTheLoai());
            ps.setDate(2, Date.valueOf(LocalDate.now()));

            ps.setInt(3, theLoai.getId_TheLoai());
            row = ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (Exception e) {
            // Xử lý exception
            e.printStackTrace();
        }
        return row;
    }
        public ArrayList<TheLoai> searchTheLoai(String tenTheLoai) {
        ArrayList<TheLoai> listTL = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "SELECT * FROM TheLoai WHERE tenTheLoai LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenTheLoai + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setId_TheLoai(rs.getInt("id_TheLoai"));
                tl.setTenTheLoai(rs.getString("tenTheLoai"));
                tl.setNgayTao(rs.getString("ngayTao"));
                tl.setNgaySua(rs.getString("ngaySua"));
                listTL.add(tl);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTL;
    }
}
