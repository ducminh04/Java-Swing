/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;


import Model.HoaDon_Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import responsitory.DBConnect;
import view.HoaDonChiTiet;

/**
 *
 * @author HP
 */
public class HoaDon_service {

    public List<HoaDon_Model> getAllHoaDon() throws SQLException {
        {
            List<HoaDon_Model> list = new ArrayList<>();
            Connection conn = DBConnect.getConnectDAO();
            String sql = "select hoaDon.id_hoaDon,khachHang.tenKhachHang,nhanVien.tenNhanVien,hoaDon.tienHang,hoaDon.giamGia,hoaDon.tongTien,hoaDon.ngayTao,hoaDon.ngaySua,hoaDon.trangThai from hoaDon \n"
                    + "join nhanVien on nhanVien.id_NhanVien = hoaDon.id_NhanVien\n"
                    + "full outer join khachHang on khachHang.id_KhachHang = hoaDon.id_KhachHang\n"
                    + "full outer join KhuyenMai on KhuyenMai.id_KhuyenMai = hoaDon.id_KhuyenMai\n"
                    + "\n"
                    + "where hoaDon.trangThai like 1 ";
            try {
                PreparedStatement ptsm = conn.prepareStatement(sql);
                ResultSet rs = ptsm.executeQuery();
                while (rs.next()) {
                    HoaDon_Model hdm = new HoaDon_Model(rs.getInt("id_hoaDon"),
                            rs.getString("tenKhachHang"),
                            rs.getString("tenNhanVien"),
                            rs.getFloat("tienHang"),
                            rs.getFloat("giamGia"),
                            rs.getFloat("tongTien"),
                            rs.getDate("ngayTao"),
                            rs.getDate("ngaySua"),
                            rs.getBoolean("trangThai"));
                    list.add(hdm);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(int ma) throws SQLException {
        {
            List<HoaDonChiTiet> lists = new ArrayList<>();
            Connection conn = DBConnect.getConnectDAO();
            String sql = "select hoaDonChiTiet.id_HoaDonChiTiet,hoaDonChiTiet.id_HoaDon,sanPham.tenSanPham,hoaDonChiTiet.giaBan,hoaDonChiTiet.soLuong  from hoaDonChiTiet \n"
                    + "join SanPhamChiTiet  on SanPhamChiTiet.id_SanPhamChiTiet = hoaDonChiTiet.id_SanPhamChiTiet  \n"
                    + "join sanPham on sanPham.id_SanPham = SanPhamChiTiet.id_SanPham where hoaDonChiTiet.id_HoaDon like ?";
            try {
                PreparedStatement ptsm = conn.prepareStatement(sql);
                ptsm.setInt(1, ma);
                ResultSet rs = ptsm.executeQuery();
                while (rs.next()) {
                    HoaDonChiTiet qlhdct = new HoaDonChiTiet(rs.getInt("id_HoaDonChiTiet"),
                            rs.getInt("id_HoaDon"),
                            rs.getString("tenSanPham"),
                            rs.getDouble("giaBan"),
                            rs.getInt("soLuong")
                    );
                    lists.add(qlhdct);
                }
                return lists;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

//    public List<HoaDon_Model> timKiemHoaDon(String ma) throws SQLException {
//        {
//            List<HoaDon_Model> list = new ArrayList<>();
//            Connection conn = DBConnect.getConnectDAO();
//            String sql = "select hoaDon.id_hoaDon,khachHang.tenKhachHang,nhanVien.tenNhanVien,hoaDon.tienHang,hoaDon.giamGia,hoaDon.tongTien,hoaDon.ngayTao,hoaDon.ngaySua,hoaDon.trangThai from hoaDon\n"
//                    + "join nhanVien  on nhanVien.id_NhanVien = hoaDon.id_NhanVien\n"
//                    + "full outer join khachHang on khachHang.id_KhachHang = hoaDon.id_KhachHang\n"
//                    + "full outer join KhuyenMai on KhuyenMai.id_KhuyenMai = hoaDon.id_KhuyenMai\n"
//                    + "\n"
//                    + "where id_hoaDon like ?";
//            try {
//                PreparedStatement ptsm = conn.prepareStatement(sql);
//                ptsm.setString(1, "%" + ma + "%");
//                ResultSet rs = ptsm.executeQuery();
//                while (rs.next()) {
//                    HoaDon_Model hdm = new HoaDon_Model(rs.getInt("id_hoaDon"),
//                            rs.getString("tenKhachHang"),
//                            rs.getString("tenNhanVien"),
//                            rs.getFloat("tienHang"),
//                            rs.getFloat("giamGia"),
//                            rs.getFloat("tongTien"),
//                            rs.getDate("ngayTao"),
//                            rs.getDate("ngaySua"),
//                            rs.getBoolean("trangThai"));
//                    list.add(hdm);
//                }
//                return list;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//
//        }
//    }
}
