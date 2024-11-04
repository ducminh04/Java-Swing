package Model;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class HoaDon_Model {
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    private int id_hoaDon;
    private String id_KhachHang;
    private String id_NhanVien;
    private double tienHang;
    private double chietKhau;
    private double tongTien;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public Object[] addRow() {
        return new Object[]{id_hoaDon, id_KhachHang == null ? "Khách vãng lai" : this.id_KhachHang  , id_NhanVien, tienHang, chietKhau, tongTien, ngayTao, ngaySua, trangThai == true ? "Đã Thanh Toán" : "Chưa Thanh Toán"};
    }

    public HoaDon_Model() {
    }

    public HoaDon_Model(int id_hoaDon, String id_KhachHang, String id_NhanVien, double tienHang, double chietKhau, double tongTien, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id_hoaDon = id_hoaDon;
        this.id_KhachHang = id_KhachHang;
        this.id_NhanVien = id_NhanVien;
        this.tienHang = tienHang;
        this.chietKhau = chietKhau;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(int id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public String getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(String id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public String getId_NhanVien() {
        return id_NhanVien;
    }

    public void setId_NhanVien(String id_NhanVien) {
        this.id_NhanVien = id_NhanVien;
    }

    public double getTienHang() {
        return tienHang;
    }

    public void setTienHang(double tienHang) {
        this.tienHang = tienHang;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDonModel{" + "id_hoaDon=" + id_hoaDon + ", id_KhachHang=" + id_KhachHang + ", id_NhanVien=" + id_NhanVien + ", tienHang=" + tienHang + ", chietKhau=" + chietKhau + ", tongTien=" + tongTien + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + '}';
    }
}
