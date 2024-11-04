/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.ThongKeSP;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class ThongKeSanPham_service {

    public ArrayList<ThongKeSP> getAll() {
        ArrayList<ThongKeSP> listTKSP = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "select sp.tenSanPham as 'tenSanPham', sum(hdct.soLuong) as 'soLuong', sum(hdct.soLuong*hdct.giaBan) as 'TongTien'"
                + "from hoaDonChiTiet hdct\n"
                + "join SanPhamChiTiet spct\n"
                + "on hdct.id_SanPhamChiTiet = spct.id_SanPhamChiTiet\n"
                + "join sanPham sp\n"
                + "on spct.id_SanPham = sp.id_SanPham\n"
                + "group by sp.tenSanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeSP tksp = new ThongKeSP();
                tksp.setTenSP(rs.getString("tenSanPham"));
                tksp.setSoLuong(rs.getInt("soLuong"));
                tksp.setTongTien(rs.getFloat("TongTien"));
                listTKSP.add(tksp);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTKSP;
    }

    public ArrayList<ThongKeSP> theoCBO(Date startDate, Date endDate) {
        ArrayList<ThongKeSP> listTK = new ArrayList<>();
        Connection conn = DBConnect.getConnectDAO();
        String sql = "select sp.tenSanPham as 'tenSanPham', sum(hdct.soLuong) as 'soLuong', sum(hdct.soLuong*hdct.giaBan) as 'TongTien'\n"
                + "from hoaDonChiTiet hdct\n"
                + "join SanPhamChiTiet spct\n"
                + "on hdct.id_SanPhamChiTiet = spct.id_SanPhamChiTiet\n"
                + "join sanPham sp\n"
                + "on spct.id_SanPham = sp.id_SanPham\n"
                + "join hoaDon hd\n"
                + "on hd.id_hoaDon = hdct.id_HoaDon\n"
                + "where hd.ngayTao between ? and ?\n"
                + "group by sp.tenSanPham";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
            ps.setDate(1, sqlStartDate);
            ps.setDate(2, sqlEndDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeSP tksp = new ThongKeSP();
                tksp.setTenSP(rs.getString("tenSanPham"));
                tksp.setSoLuong(rs.getInt("soLuong"));
                tksp.setTongTien(rs.getFloat("TongTien"));
                listTK.add(tksp);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;
    }
}
