/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.SanPhamChiTiet;
import model.SanPhamDetail_model;
import responsitory.DBConnect;

public class SPCT_service {

    public ArrayList<SanPhamDetail_model> findAllSanPhamChiTiet() {
        ArrayList<SanPhamDetail_model> listSPCT = new ArrayList<>();
        try {
            String sql = "SELECT SanPhamChiTiet.id_SanPhamChiTiet, sanPham.id_SanPham, sanPham.tenSanPham, nxb.tenNhaXuatBan, \n" +
"                     tacGia.tenTacGia, nhaCungCap.tenNhaCungCap, TheLoai.tenTheLoai, SanPhamChiTiet.ngayTao, SanPhamChiTiet.ngaySua, \n" +
"                     SanPhamChiTiet.nguoiTao, SanPhamChiTiet.nguoiSua, SanPhamChiTiet.soLuong, SanPhamChiTiet.giaBan,  SanPhamChiTiet.trangThai " +
"                     FROM SanPhamChiTiet \n" +
"                     JOIN sanPham ON SanPhamChiTiet.id_SanPham = sanPham.id_SanPham \n" +
"                     JOIN tacGia ON tacGia.id_TacGia = SanPhamChiTiet.id_TacGia\n" +
"                     JOIN TheLoai ON TheLoai.id_TheLoai = SanPhamChiTiet.id_TheLoai \n" +
"                     JOIN nhaCungCap ON nhaCungCap.id_NhaCungCap = SanPhamChiTiet.id_NhaCungCap \n" +
"                     JOIN nxb ON nxb.id_NXB = SanPhamChiTiet.id_NXB\n" +
"			";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamDetail_model sp = new SanPhamDetail_model();
                sp.setId_SanPhamChiTiet(rs.getInt("id_SanPhamChiTiet"));
                sp.setId_SanPham(rs.getInt("id_SanPham"));
                sp.setTen_SanPham(rs.getString("tenSanPham"));
                sp.setTen_NXB(rs.getString("tenNhaXuatBan"));
                sp.setTen_TacGia(rs.getString("tenTacGia"));
                sp.setTen_NhaCungCap(rs.getString("tenNhaCungCap"));
                sp.setNgayTao(rs.getDate("ngayTao"));
                sp.setNgaySua(rs.getDate("ngaySua"));
                sp.setNguoiTao(rs.getString("nguoiTao"));
                sp.setNguoiSua(rs.getString("nguoiSua"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setGiaBan(rs.getFloat("giaBan"));
                sp.setTheLoai(rs.getString("tenTheLoai"));
                sp.setTrangThai(rs.getBoolean("trangThai"));
              
                listSPCT.add(sp);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPCT;
    }

    public ArrayList<SanPhamChiTiet> getAllID() {
        ArrayList<SanPhamChiTiet> listSPCTfirst = new ArrayList<>();
        try {
            String sql = " select * from sanPhamChiTiet";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setId_SanPhamChiTiet(rs.getInt(1));
                sp.setId_NXB(rs.getInt(2));
                sp.setId_TacGia(rs.getInt(3));
                sp.setId_NhaCungCap(rs.getInt(4));
                sp.setId_SanPham(rs.getInt(5));
                sp.setId_theLoai(rs.getInt(6));
                sp.setGiaBan(rs.getFloat(7));
                sp.setSoLuong(rs.getInt(8));
                sp.setNgayTao(rs.getDate(9));
                sp.setNgaySua(rs.getDate(10));
                sp.setNguoiTao(rs.getString(11));
                sp.setNguoiSua(rs.getString(12));
                sp.setTrangThai(rs.getBoolean(13));

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

    public Integer addSanPhamChiTiet(SanPhamChiTiet sp) {
        Integer row = null;
        try {
            String sql = "insert into SanPhamChiTiet(id_sanPham,id_NXB,id_TacGia,id_NhaCungCap,ngayTao,nguoiTao, soLuong, giaBan,id_TheLoai, trangThai)\n"
                    + "			values(?,?,?,?,?,?,?,?,?,?)";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            SanPhamChiTiet spct = new SanPhamChiTiet();
            ps.setInt(1, sp.getId_SanPham());
            ps.setInt(2, sp.getId_NXB());
            ps.setInt(3, sp.getId_TacGia());
            ps.setInt(4, sp.getId_NhaCungCap());
            ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(6, sp.getNguoiTao());
            ps.setInt(7, sp.getSoLuong());
            ps.setFloat(8, sp.getGiaBan());
            ps.setInt(9, sp.getId_theLoai());
            ps.setBoolean(10, sp.isTrangThai());

            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer updateSanPhamChiTiet(SanPhamChiTiet spct) {
        Integer row = null;
        try {
            String sql = "UPDATE SanPhamChiTiet SET id_sanPham = ?,  id_NXB = ?, id_TacGia = ?, id_NhaCungCap = ?, ngaySua = ?, nguoiSua = ?, soLuong = ?, giaBan = ?, id_TheLoai = ?, trangThai = ? WHERE id_SanPhamChiTiet = ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, spct.getId_SanPham());
            ps.setInt(2, spct.getId_NXB());
            ps.setInt(3, spct.getId_TacGia());
            ps.setInt(4, spct.getId_NhaCungCap());
            ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(6, spct.getNguoiSua());
            ps.setInt(7, spct.getSoLuong());
            ps.setDouble(8, spct.getGiaBan());
            ps.setInt(9, spct.getId_theLoai());
            ps.setBoolean(10, spct.isTrangThai());
            ps.setInt(11, spct.getId_SanPhamChiTiet());
            row = ps.executeUpdate();

        } catch (Exception e) {
           
            e.printStackTrace();
        }
        return row;
    }

    public List<SanPhamDetail_model> findByName(String ten) {
        ArrayList<SanPhamDetail_model> list = new ArrayList<>();
        try {
            String sql = "select SanPhamChiTiet.id_SanPhamChiTiet,sanPham.id_SanPham, sanPham.tenSanPham, nxb.tenNhaXuatBan, nhaCungCap.tenNhaCungCap, tacGia.tenTacGia,TheLoai.tenTheLoai, giaBan, soLuong, SanPhamChiTiet.ngayTao,SanPhamChiTiet.ngaySua,SanPhamChiTiet.nguoiTao, SanPhamChiTiet.nguoiSua, SanPhamChiTiet.trangThai\n"
                    + "from tacGia inner join SanPhamChiTiet on tacGia.id_TacGia = SanPhamChiTiet.id_TacGia\n"
                    + "join nhaCungCap on nhaCungCap.id_NhaCungCap = SanPhamChiTiet.id_NhaCungCap\n"
                    + "join nxb on nxb.id_NXB = SanPhamChiTiet.id_NXB\n"
                     + "join TheLoai on TheLoai.id_theLoai = SanPhamChiTiet.id_theLoai\n"
                    + "join sanPham on sanPham.id_SanPham = SanPhamChiTiet.id_SanPham\n"
                    + "where sanPham.tenSanPham like ?";
            Connection cn = DBConnect.getConnectDAO();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamDetail_model sp = new SanPhamDetail_model();
                sp.setId_SanPhamChiTiet(rs.getInt("id_SanPhamChiTiet"));
                sp.setId_SanPham(rs.getInt("id_SanPham"));
                sp.setTen_SanPham(rs.getString("tenSanPham"));
                sp.setTen_NXB(rs.getString("tenNhaXuatBan"));
                sp.setTen_TacGia(rs.getString("tenTacGia"));
                sp.setTen_NhaCungCap(rs.getString("tenNhaCungCap"));
                sp.setNgayTao(rs.getDate("ngayTao"));
                sp.setNgaySua(rs.getDate("ngaySua"));
                sp.setNguoiTao(rs.getString("nguoiTao"));
                sp.setNguoiSua(rs.getString("nguoiSua"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setGiaBan(rs.getFloat("giaBan"));
                sp.setTheLoai(rs.getString("tenTheLoai"));
                sp.setTrangThai(rs.getBoolean("trangThai"));
                list.add(sp);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list;
    }
}
