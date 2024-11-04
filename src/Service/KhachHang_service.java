/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class KhachHang_service {
      
      public ArrayList<KhachHang> getAllKhachHang(){
          ArrayList<KhachHang> list = new ArrayList<>();
            try {
            String sql = "select * from khachHang";
            Connection con = DBConnect.getConnectDAO();
            PreparedStatement ptsm = con.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId_KH(rs.getInt("id_KhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setNgayThem(rs.getString("ngayTao"));
               kh.setNgaySua(rs.getString("ngaySua"));
                kh.setTrangThai(rs.getBoolean("trangThai"));

                list.add(kh);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
      }
      
      public Integer addKhachHang(KhachHang kh) {
        Integer row = null;
        Connection conn = DBConnect.getConnectDAO();
        try {
            String sql = "insert into  khachHang (tenKhachHang, gioiTinh, sdt, ngayTao, trangThai) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, kh.getTenKH());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getNgayThem());
            ps.setBoolean(5, kh.isTrangThai());
            row = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
      public Integer updateKhachHang(KhachHang kh) {
        Integer row = null;
        try {
            String sql = "UPDATE khachHang SET sdt = ?, ngaySua = ?, trangThai = ? WHERE id_KhachHang = ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(4, kh.getId_KH());
            ps.setString(1, kh.getSdt());
            ps.setString(2, kh.getNgaySua());
            ps.setBoolean(3, kh.isTrangThai());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public ArrayList<KhachHang> findKhachHang(String sdt, String name) {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            String sql = "select * from khachHang\n"
                    + "where sdt like ? or tenKhachHang like ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, '%' + sdt + '%');
            ps.setString(2, '%' + name + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang nv = new KhachHang();
               nv.setId_KH(rs.getInt("id_KhachHang"));
                nv.setTenKH(rs.getString("tenKhachHang"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgayThem(rs.getString("ngayTao"));
               nv.setNgaySua(rs.getString("ngaySua"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
