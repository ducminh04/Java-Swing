/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.BanHangGioHang;
import model.BanHangHoaDoncho;
import model.BanHangSanPham;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class BanHang_service {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<BanHangSanPham> getAllSanPham() {
        ArrayList<BanHangSanPham> bhsp = new ArrayList<>();
        String sql = "select SanPhamChiTiet.id_SanPhamChiTiet, sanPham.tenSanPham, giaBan, SanPhamChiTiet.soLuong \n"
                + "from SanPhamChiTiet join sanPham on sanPham.id_SanPham = SanPhamChiTiet.id_SanPham "
                + "where SanPhamChiTiet.soLuong > 0 and SanPhamChiTiet.trangThai like 1";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bhsp.add(new BanHangSanPham(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bhsp;
    }
        public ArrayList<KhuyenMai> getAllKhuyenMai() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "select * from KhuyenMai where trangThai like 1 and soLuong > 0";
        Connection conn = DBConnect.getConnectDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId_KhuyenMai(rs.getInt("id_KhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setNgayTao(rs.getDate("ngayTao").toLocalDate());
                km.setNguoiTao(rs.getString("nguoiTao"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                km.setGiaTriGiam(rs.getInt("giaTriGiam"));
                km.setHoaDonToiThieu(rs.getFloat("hoaDonToiThieu"));
                km.setTrangThai(rs.getBoolean("trangThai"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi get all khuyen mai");
        }
        return list;
    }

    public KhachHang timKiemsdt(String SDT) {
        String sql = "select * from khachHang where sdt like ?";

        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setString(1, SDT);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId_KH(rs.getInt("id_KhachHang"));
                kh.setTenKH(rs.getString("tenKhachHang"));
                kh.setGioiTinh(rs.getBoolean("gioiTinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setNgayThem(rs.getString("ngayTao"));
                kh.setNgaySua(rs.getString("ngaySua"));
                kh.setTrangThai(rs.getBoolean("trangThai"));
                return kh;
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi tim kiem khach hang");
        }
        return null;
    }

    public ArrayList<BanHangHoaDoncho> getAllHoaDonCho() {
        ArrayList<BanHangHoaDoncho> list = new ArrayList<>();
        String sql = "select id_hoaDon, nhanVien.tenNhanVien, khachHang.tenKhachHang , hoaDon.ngayTao, hoaDon.trangThai\n"
                + " from HoaDon left join NhanVien on HoaDon.id_NhanVien = NhanVien.id_NhanVien\n"
                + " left join khachHang on HoaDon.id_KhachHang = KhachHang.id_KhachHang where hoaDon.trangThai = 0";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BanHangHoaDoncho b = new BanHangHoaDoncho();
                b.setId(rs.getInt("id_hoaDon"));
                b.setTenNhanVien(rs.getString("tenNhanVien"));
                b.setTenKhachHang(rs.getString("tenKhachHang"));
                b.setNgayTao(rs.getDate("ngayTao"));
                b.setTrangThai(rs.getBoolean("trangThai"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer addHd_coKH(HoaDon hd) {
        Integer row = null;
        String sql = "insert into hoaDon(id_NhanVien, id_KhachHang, ngayTao, trangThai)\n"
                + "values(?,?,?,?)";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hd.getId_NhanVien());
            ps.setInt(2, hd.getId_KhachHang());
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, false);
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi add  hoa don");
        }
        return row;
    }

    public Integer addHd_khongKhachHang(HoaDon hd) {
        Integer row = null;
        String sql = "insert into hoaDon(id_NhanVien, ngayTao, trangThai)\n"
                + "values(?,?,?)";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hd.getId_NhanVien());
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setBoolean(3, false);
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi add hoa don1");
        }
        return row;
    }

    public ArrayList<BanHangGioHang> getAllGioHang(int maHoaDon) {
        ArrayList<BanHangGioHang> listGH = new ArrayList<>();
        
        
        String sql = "select hoaDonChiTiet.id_SanPhamChiTiet, sanPham.tenSanPham, hoaDonChiTiet.soLuong, hoaDonChiTiet.giaBan, hoaDonChiTiet.soLuong * hoaDonChiTiet.giaBan \n"
                + "from sanPham join SanPhamChiTiet on sanPham.id_SanPham = SanPhamChiTiet.id_sanPham\n"
                + "join hoaDonChiTiet on hoaDonChiTiet.id_SanPhamChiTiet = SanPhamChiTiet.id_SanPhamChiTiet\n"
                + "where hoaDonChiTiet.id_hoaDon = ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maHoaDon);
            rs = ps.executeQuery();
            while (rs.next()) {
                BanHangGioHang b = new BanHangGioHang();
                b.setId_sanPham(rs.getInt(1));
                b.setTenSanPham(rs.getString(2));
                b.setSoLuong(rs.getInt(3));
                b.setGiaBan(rs.getFloat(4));
                b.setThanhTien(rs.getFloat(5));
                listGH.add(b);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Lỗi getAll Giỏ Hàng");
        }
        return listGH;
    }

        public Integer updateSoLuongSanPhamVe(int soLuong, int idSanPhamChiTiet) {
            Integer row = null;
            try {
                String sql = "update SanPhamChiTiet\n"
                        + "set soLuong = soLuong + ? \n"
                        + "where id_SanPhamChiTiet like ?";
                conn = new DBConnect().getConnectDAO();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, soLuong);
                ps.setInt(2, idSanPhamChiTiet);
                row = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);

                System.out.println("Loi update so luong ve sp");
            }
            return row;
        }

    public Integer cancelHoaDon(int id) {
        Integer row = null;
        String sql = "delete hoaDonChiTiet\n"
                + "where id_hoaDon like ?\n"
                + "delete hoaDon\n"
                + "where id_hoaDon like ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi huy  hoa don");
        }
        return row;
    }

    public Integer deleteGioHang(int idHoaDon, int idSpCt) {
        Integer row = null;
        try {
            String sql = "delete hoaDonChiTiet\n"
                    + "where hoaDonChiTiet.id_HoaDon like ? and hoaDonChiTiet.id_SanPhamChiTiet like ?";
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idHoaDon);
            ps.setInt(2, idSpCt);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi xoa gio hang");
        }
        return row;
    }

    public ArrayList<BanHangSanPham> getAllTimKiemSanPham(String ten) {
        ArrayList<BanHangSanPham> dssp = new ArrayList<>();
        String sql = "select SanPhamChiTiet.id_SanPhamChiTiet, sanPham.tenSanPham, giaBan, soLuong \n"
                + "from SanPhamChiTiet join sanPham on sanPham.id_SanPham = SanPhamChiTiet.id_SanPham \n"
                + "where tenSanPham like ?  and SanPhamChiTiet.trangThai like 1 ";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                dssp.add(new BanHangSanPham(rs.getInt("id_SanPhamChiTiet"), rs.getString("tenSanPham"), rs.getFloat("giaBan"), rs.getInt("soLuong")));
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("loi tim kiem sp");
        }
        return dssp;
    }

    public Integer updateSoLuongSanPham(int soLuong, int idSanPhamChiTiet) {
        Integer row = null;
        try {
            String sql = "update SanPhamChiTiet\n"
                    + "set soLuong = soLuong - ? \n"
                    + "where id_SanPhamChiTiet like ?";
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, idSanPhamChiTiet);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update so luong di san pham");
        }
        return row;
    }

    public Integer addGiohang(int idHoaDon, int idSpCt, int soLuong) {
        Integer row = null;
        try {
            String sql = "insert into hoaDonChiTiet(id_HoaDon, id_SanPhamChiTiet, soLuong, giaBan)\n"
                    + "			values(?,?,?,(select giaBan from SanPhamChiTiet where id_SanPhamChiTiet like ? ))";
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idHoaDon);
            ps.setInt(2, idSpCt);
            ps.setInt(3, soLuong);
            ps.setInt(4, idSpCt);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi Add gio Hang");
        }
        return row;
    }

    public Integer UpdateGiohang_SoLuong(int idHoaDon, int idSpCt, int soLuong) {
        Integer row = null;
        try {
            String sql = "update hoaDonChiTiet\n"
                    + "set soLuong = soLuong + ?\n"
                    + "where hoaDonChiTiet.id_HoaDon like ? and hoaDonChiTiet.id_SanPhamChiTiet like ?";
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(2, idHoaDon);
            ps.setInt(3, idSpCt);
            ps.setInt(1, soLuong);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update so luong gio hang");
        }
        return row;
    }

    public Integer UpdateGiohang_SoLuongGiam(int idHoaDon, int idSpCt, int soLuong) {
        Integer row = null;
        try {
            String sql = "update hoaDonChiTiet\n"
                    + "set soLuong = soLuong - ?\n"
                    + "where hoaDonChiTiet.id_HoaDon like ? and hoaDonChiTiet.id_SanPhamChiTiet like ?";
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(2, idHoaDon);
            ps.setInt(3, idSpCt);
            ps.setInt(1, soLuong);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update so luong gio hang");
        }
        return row;
    }

    public BanHangSanPham getAllSanPhamByid(int id_spct) {

        String sql = "select SanPhamChiTiet.id_SanPhamChiTiet, sanPham.tenSanPham, giaBan, soLuong \n"
                + "from SanPhamChiTiet join sanPham on sanPham.id_SanPham = SanPhamChiTiet.id_SanPham "
                + "where SanPhamChiTiet.id_SanPhamChiTiet like ? and SanPhamChiTiet.soLuong > 0 and SanPhamChiTiet.trangThai like 1";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_spct);
            rs = ps.executeQuery();
            while (rs.next()) {
                BanHangSanPham sp = new BanHangSanPham(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
                return sp;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(" Loi get all san pham");
        }
        return null;
    }

    public Integer updateHoaDon_CoKhachHang_KhongKhuyenMai(boolean phuongThucTT, float tienHang, float giamGia, float tongTien, int idHoaDon, int idKhachHang) {
        Integer row = null;
        String sql = "update hoaDon\n"
                + "set phuongThucThanhToan = ?, tienHang=?, giamGia = ?, tongTien = ?, ngaySua = GETDATE(), id_khachHang = ?, trangThai =1\n"
                + "where id_hoaDon like ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, phuongThucTT);
            ps.setFloat(2, tienHang);
            ps.setFloat(3, giamGia);
            ps.setFloat(4, tongTien);
           
            ps.setInt(6, idHoaDon);
            ps.setInt(5, idKhachHang);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update  hoa don");
        }
        return row;
    }

    public Integer updateHoaDonCoKhCoKM(int idKM, boolean phuongThucTT, float tienHang, float giamGia, float tongTien, int idHoaDon, int idKhachHang) {
        Integer row = null;
        String sql = "update hoaDon\n"
                + "set phuongThucThanhToan = ?,tienHang=?, giamGia = ?, tongTien = ?, ngaySua = GETDATE(),id_khuyenMai = ?,id_khachHang = ?, trangThai =1\n"
                + "where id_hoaDon like ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, phuongThucTT);
            ps.setFloat(2, tienHang);
            ps.setFloat(3, giamGia);
            ps.setFloat(4, tongTien);
            ps.setInt(7, idHoaDon);
            ps.setInt(5, idKM);
            ps.setInt(6, idKhachHang);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update  hoa don");
        }
        return row;
    }

    public Integer updateKmSoLuong(int id) {
        Integer row = null;
        String sql = "update KhuyenMai\n"
                + "set soLuong = soLuong - 1\n"
                + "where id_KhuyenMai like  ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update so luong khuyen mai");
        }
        return row;
    }

    public Integer updateHoaDon_KhongKhachHang_KhongKhuyenMai(boolean phuongThucTT, float tienHang, float giamGia, float tongTien, int idHoaDon) {
        Integer row = null;
        String sql = "update hoaDon\n"
                + "set phuongThucThanhToan = ?,tienHang=?, giamGia = ?, tongTien = ?, ngaySua = GETDATE(), trangThai =1\n"
                + "where id_hoaDon like ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, phuongThucTT);
            ps.setFloat(2, tienHang);
            ps.setFloat(3, giamGia);
            ps.setFloat(4, tongTien);
            ps.setInt(5, idHoaDon);
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update  hoa don");
        }
        return row;
    }
    
    public Integer updateHoaDonKoKhCoKM(int idKM, boolean phuongThucTT, float tienHang, float giamGia, float tongTien, int idHoaDon) {
        Integer row = null;
        String sql = "update hoaDon\n"
                + "set phuongThucThanhToan = ?,tienHang=?, giamGia = ?, tongTien = ?, ngaySua = GETDATE(),id_KhuyenMai = ?, trangThai =1\n"
                + "where id_hoaDon like ?";
        try {
            conn = new DBConnect().getConnectDAO();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, phuongThucTT);
            ps.setFloat(2, tienHang);
            ps.setFloat(3, giamGia);
            ps.setFloat(4, tongTien);
            ps.setInt(6, idHoaDon);
            ps.setInt(5, idKM);
           
            row = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi update  hoa don");
        }
        return row;
    }
    public int coutnHoaDon() {
        int count = 0;
        String sql = "select COUNT(*) as total  from  hoaDon where trangThai like 0";
        try (Connection con = DBConnect.getConnectDAO(); PreparedStatement pstm = con.prepareStatement(sql); ResultSet rs = pstm.executeQuery();) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
public List<BanHangSanPham> getSanPhamByDM(int categoryId) {
    List<BanHangSanPham> products = new ArrayList<>();
    String sql = "SELECT sp.id_SanPham, sp.tenSanPham, spt.giaBan, spt.soLuong " +
                 "FROM sanPham sp " +
                 "JOIN SanPhamChiTiet spt ON sp.id_SanPham = spt.id_SanPham " +
                 "JOIN DanhMuc dm ON sp.id_danhMuc = dm.id_danhMuc " +
                 "WHERE dm.id_danhMuc = ? AND spt.trangThai = 1 AND spt.soLuong > 0";

    try (Connection conn = DBConnect.getConnectDAO();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, categoryId); 
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_SanPham");
                String ten = rs.getString("tenSanPham");
                float gia = rs.getFloat("giaBan");
                int soLuong = rs.getInt("soLuong");

                BanHangSanPham sp = new BanHangSanPham(id, ten, gia, soLuong);
                products.add(sp);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return products;
}


}
