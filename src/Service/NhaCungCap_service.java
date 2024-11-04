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
import java.util.List;
import model.NhaCungCap;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class NhaCungCap_service {

    public ArrayList<NhaCungCap> getAllNCC() {
        ArrayList<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT id_NhaCungCap, tenNhaCungCap, ngayTao, ngaySua, trangThai FROM NhaCungCap";
        try (Connection conn = DBConnect.getConnectDAO(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setId_NCC(rs.getInt("id_NhaCungCap"));
                ncc.setTenNCC(rs.getString("tenNhaCungCap"));

                // Kiểm tra giá trị null trước khi gọi toLocalDate()
                Date ngayTao = rs.getDate("ngayTao");
                if (ngayTao != null) {
                    ncc.setNgayTao(ngayTao.toLocalDate());
                } else {
                    ncc.setNgayTao(null);
                }

                Date ngaySua = rs.getDate("ngaySua");
                if (ngaySua != null) {
                    ncc.setNgaySua(ngaySua.toLocalDate());
                } else {
                    ncc.setNgaySua(null);
                }

                ncc.setTrangThai(rs.getBoolean("trangThai"));
                list.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public ArrayList<NhaCungCap> getAllByTrangThai() {
        ArrayList<NhaCungCap> listNCC = new ArrayList<>();
        String sql = "select * from nhaCungCap where trangThai like 1";
        Connection conn = DBConnect.getConnectDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setId_NCC(rs.getInt("id_NhaCungCap"));
                ncc.setTenNCC(rs.getString("tenNhaCungCap"));
                ncc.setNgayTao(rs.getDate("ngayTao").toLocalDate());
                ncc.setNgaySua(rs.getDate("ngaySua").toLocalDate());
                ncc.setTrangThai(rs.getBoolean("trangThai"));
                listNCC.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNCC;
    }

    public void addNhaCungCap(NhaCungCap ncc) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "INSERT INTO nhaCungCap"
                + "(tenNhaCungCap,ngayTao, trangThai)"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setDate(2, Date.valueOf(ncc.getNgayTao()));
            ps.setBoolean(3, ncc.getTrangThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhaCungCap(int id, NhaCungCap ncc) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "UPDATE nhaCungCap SET tenNhaCungCap=?,ngaySua=?,trangThai=? WHERE id_NhaCungCap=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(4, ncc.getId_NCC());
            ps.setString(1, ncc.getTenNCC());
            ps.setDate(2, Date.valueOf(ncc.getNgaySua()));
            ps.setBoolean(3, ncc.getTrangThai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NhaCungCap> searchNhaCungcap(String tenNCC) {
        ArrayList<NhaCungCap> listNCC = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "SELECT * FROM nhaCungCap WHERE tenNhaCungCap LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenNCC + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setId_NCC(rs.getInt("id_NhaCungCap"));
                ncc.setTenNCC(rs.getString("tenNhaCungCap"));
                ncc.setNgayTao(rs.getDate("ngayTao").toLocalDate());
                ncc.setNgaySua(rs.getDate("ngaySua").toLocalDate());
                ncc.setTrangThai(rs.getBoolean("trangThai"));
                listNCC.add(ncc);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNCC;
    }
}
