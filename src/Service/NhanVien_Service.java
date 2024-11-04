/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class NhanVien_Service {

    String findByEmail = "select * from nhanVien\n"
            + "where email like ?";

    String findByUserName = "select * from nhanVien where username like ?";

    public List<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            String sql = "select * from nhanVien";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NV(rs.getInt("id_NhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                nv.setEmail(rs.getString("email"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                nv.setUsername(rs.getString("username"));
                nv.setPasswork(rs.getString("password"));
                nv.setVaiTro(rs.getBoolean("vaiTro"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public NhanVien findByUserName(String userName) {
        try {
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(findByUserName);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NV(rs.getInt("id_NhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                nv.setEmail(rs.getString("email"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                nv.setUsername(rs.getString("username"));
                nv.setPasswork(rs.getString("password"));
                nv.setVaiTro(rs.getBoolean("vaiTro"));
                return nv;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<NhanVien> findNhanVien(String sdt, String email) {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            String sql = "select * from nhanVien\n"
                    + "where sdt like ? or email like ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, '%' + sdt + '%');
            ps.setString(2, '%' + email + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NV(rs.getInt("id_NhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                nv.setEmail(rs.getString("email"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setVaiTro(rs.getBoolean("vaiTro"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Integer add(NhanVien o) {
        Integer row = null;
        try {
            String sql = "INSERT INTO nhanVien(tenNhanVien, ngaySinh, gioiTinh, email, sdt, ngayTao, trangThai, username, password, vaiTro) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, o.getTenNhanVien());
            java.util.Date ngaySinhUtil = o.getNgaysinh();
            java.sql.Date ngaySinhSql = new java.sql.Date(ngaySinhUtil.getTime());
            ps.setDate(2, ngaySinhSql);
            ps.setBoolean(3, o.isGioiTinh());
            ps.setString(4, o.getEmail());
            ps.setString(5, o.getSdt());
            ps.setDate(6, Date.valueOf(LocalDate.now())); // Ngày tạo hiện tại
            ps.setBoolean(7, o.isTrangThai());
            ps.setString(8, o.getUsername());
            ps.setString(9, o.getPasswork());
            ps.setBoolean(10, o.isVaiTro());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;

    }

    public Integer updateNhanVien(NhanVien o) {
        Integer row = null;
        try {
            String sql = "UPDATE nhanVien SET email=?, sdt=?,ngaySua = ?, trangThai=?, username=?, password=?, vaiTro=? WHERE id_NhanVien=?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, o.getEmail());
            ps.setString(2, o.getSdt());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, o.isTrangThai());
            ps.setString(5, o.getUsername());
            ps.setString(6, o.getPasswork());
            ps.setBoolean(7, o.isVaiTro());
            ps.setInt(8, o.getId_NV());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateNhanVienQMK(NhanVien nv) {
        Integer row = null;
        try {
            String sql = "UPDATE nhanVien SET password = ? WHERE id_NhanVien=?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nv.getPasswork());

            ps.setInt(2, nv.getId_NV());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public NhanVien findByEmail(String email) {
        try {
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(findByEmail);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_NV(rs.getInt("id_NhanVien"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                nv.setEmail(rs.getString("email"));
                nv.setSdt(rs.getString("sdt"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setNgaySua(rs.getDate("ngaySua"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                nv.setNgayTao(rs.getDate("ngayTao"));
                nv.setVaiTro(rs.getBoolean("vaiTro"));
                return nv;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
