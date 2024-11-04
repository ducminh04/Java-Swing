/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import model.SanPham;
import responsitory.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class SanPham_Service {
    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            String sql = "select id_sanPham ,tenSanPham, ngayTao, ngaySua, nguoiTao, nguoiSua, DanhMuc.tenDanhmuc from sanPham join DanhMuc on sanPham.id_danhMuc = DanhMuc.id_danhMuc";
            Connection con = DBConnect.getConnectDAO();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId_SP(rs.getInt("id_SanPham"));
                sp.setTenSP(rs.getString("tenSanPham"));
                sp.setNgayTao(rs.getString("ngayTao"));
                sp.setNgaySua(rs.getString("ngaySua"));
                sp.setNguoiTao(rs.getString("nguoiTao"));
                sp.setNguoiSua(rs.getString("nguoiSua"));
                sp.setTenDM(rs.getString("tenDanhMuc"));

                list.add(sp);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   public void addSanPham(SanPham sp) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DBConnect.getConnectDAO();
        String sql = "INSERT INTO SanPham (id_danhMuc, tenSanPham, ngayTao, nguoiTao) VALUES (?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, sp.getId_danhMuc());
        ps.setString(2, sp.getTenSP());
        ps.setString(3, sp.getNgayTao());
        ps.setString(4, sp.getNguoiTao());
        ps.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public void update(int id_SP, SanPham sp) {
        Connection conn = DBConnect.getConnectDAO();
        String sql = "UPDATE SanPham SET tenSanPham=?, ngaySua=? ,nguoiSua = ? WHERE id_SanPham=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(4, sp.getId_SP());
            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getNgaySua());
            ps.setString(3, sp.getNguoiSua());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SanPham> searchSanPham(String tenSP) {
        ArrayList<SanPham> listSP = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "SELECT * FROM sanPham WHERE tenSanPham LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenSP + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId_SP(rs.getInt("id_SanPham"));
                sp.setTenSP(rs.getString("tenSanPham"));
                sp.setNgayTao(rs.getString("ngayTao"));
                sp.setNguoiTao(rs.getString("nguoiTao"));
                sp.setNguoiTao(rs.getString("nguoiTao"));
                sp.setNguoiSua(rs.getString("nguoiSua"));
                listSP.add(sp);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }
    public ArrayList<SanPham> getAllIDSP() {
        ArrayList<SanPham> listSPCTfirst = new ArrayList<>();
        try {
            String sql = " select * from sanPham";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId_SP(rs.getInt(1));
                sp.setId_danhMuc(rs.getInt(2));
                sp.setTenSP(rs.getString(3));
                sp.setNgayTao(rs.getString(4));
                sp.setNgaySua(rs.getString(5));
                sp.setNguoiTao(rs.getString(6));
                sp.setNguoiSua(rs.getString(7));
                listSPCTfirst.add(sp);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return listSPCTfirst;
    }
}
