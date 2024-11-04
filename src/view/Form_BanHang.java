/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.BanHang_service;
import Service.DanhMuc_service;
import Service.KhachHang_service;
import Service.KhuyenMai;
import Service.KhuyenMai_service;
import com.itextpdf.text.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import model.BanHangGioHang;
import model.BanHangHoaDoncho;
import model.BanHangSanPham;
import model.DanhMuc;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;
import utils.Auth;

/**
 *
 * @author HP
 */
public class Form_BanHang extends javax.swing.JPanel {

    DefaultTableModel model_TableGioHang = new DefaultTableModel();
    DefaultTableModel model_TableSp = new DefaultTableModel();
    DefaultTableModel model_HoaDon = new DefaultTableModel();

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    BanHang_service service_banHang = new BanHang_service();
    DefaultComboBoxModel<KhuyenMai> model_KhuyenMai = new DefaultComboBoxModel<>();

    private int rowHoaDon = -1;
    private int rowGioHang = -1;
    private int rowSanPham = -1;
    DefaultTableModel tblModel = new DefaultTableModel();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    KhuyenMai_service service_khuyenMai = new KhuyenMai_service();
    KhachHang_service ser_KH = new KhachHang_service();
    boolean check = false;

    DanhMuc_service ser_dm = new DanhMuc_service();
    private Map<String, DanhMuc> danhMucMap = new HashMap<>();

    public Form_BanHang() {
        initComponents();
        cbo_hinhThucThanhToan.removeAllItems();
        cbo_hinhThucThanhToan.addItem("TIEN MAT");
        cbo_hinhThucThanhToan.addItem("CHUYEN KHOAN");

        lbl_nhanVienID.setText("ID: " + Auth.user.getId_NV() + ". " + Auth.user.getTenNhanVien());
        fillTableHoaDon();
        fillTableSanPham();
        setComBoKM();
        txt_maHoaDon.setEditable(false);
        txt_tenKhachHang.setEditable(false);

        fillComboBoxDanhMuc();
        fillTableSanPham(service_banHang.getAllSanPham());
        setupComboBoxListener();

    }

    private void fillComboBoxDanhMuc() {
        List<DanhMuc> categories = ser_dm.getAllDM();
        cbo_danhMuc.removeAllItems();
        danhMucMap.clear();

        for (DanhMuc category : categories) {
            cbo_danhMuc.addItem(category.getTenDanhMuc());
            danhMucMap.put(category.getTenDanhMuc(), category);
        }
    }

    private void setupComboBoxListener() {
        cbo_danhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategoryName = (String) cbo_danhMuc.getSelectedItem();
                DanhMuc selectedCategory = danhMucMap.get(selectedCategoryName);

                if (selectedCategory != null) {
                    int categoryId = selectedCategory.getId_danhMuc();
                    List<BanHangSanPham> products = service_banHang.getSanPhamByDM(categoryId);
                    fillTableSanPham(products);
                }
            }
        });
    }

    private void fillTableSanPham(List<BanHangSanPham> products) {
        DefaultTableModel model = (DefaultTableModel) tbl_danhSachSP.getModel();
        model.setRowCount(0);
        for (BanHangSanPham sp : products) {
            Object[] row = {sp.getId_SP(), sp.getTenSP(), sp.getGiaSP(), sp.getSoluong()};
            model.addRow(row);
        }
    }

    public void fillTableHoaDon(){
        model_HoaDon = (DefaultTableModel) tbl_hoaDoncho.getModel();
        model_HoaDon.setRowCount(0);
        for (BanHangHoaDoncho kh : service_banHang.getAllHoaDonCho()) {
            model_HoaDon.addRow(new Object[]{kh.getId(), kh.getTenNhanVien(), kh.getTenKhachHang() == null ? "Khách vãng lai" : kh.getTenKhachHang(), kh.getNgayTao(), kh.isTrangThai() == true ? "Da Thanh Toan" : "Cho Thanh Toan"});
        }
    }

    public void fillTableSanPham() {
        model_TableSp = (DefaultTableModel) tbl_danhSachSP.getModel();
        model_TableSp.setRowCount(0);
        for (BanHangSanPham kh : service_banHang.getAllSanPham()) {
            String gia = currencyFormat.format(kh.getGiaSP());
            model_TableSp.addRow(new Object[]{kh.getId_SP(), kh.getTenSP(), gia, kh.getSoluong()});
        }
    }

    public HoaDon readFormAddHoaDon() {
        HoaDon hd = new HoaDon();
        KhachHang kh = service_banHang.timKiemsdt(txt_soDienThoatKH.getText());
        NhanVien nv = Auth.user;
        hd.setId_NhanVien(nv.getId_NV());
        if (!txt_soDienThoatKH.getText().isEmpty()) {
            hd.setId_KhachHang(kh.getId_KH());
        }
        return hd;
    }

    private void fillTableGiohang() {
        model_TableGioHang = (DefaultTableModel) tbl_gioHang.getModel();
        model_TableGioHang.setRowCount(0);
        int maHoaDon = Integer.parseInt(txt_maHoaDon.getText());
        float tongTien = 0;
        ArrayList<BanHangGioHang> list = service_banHang.getAllGioHang(Integer.parseInt(txt_maHoaDon.getText()));
        for (BanHangGioHang x : list) {
            String giaBan = currencyFormat.format(x.getGiaBan());
            String thanhTien = currencyFormat.format(x.getGiaBan() * x.getSoLuong());
            model_TableGioHang.addRow(new Object[]{x.getId_sanPham(), x.getTenSanPham(), x.getSoLuong(), giaBan, thanhTien});
            tongTien = tongTien + x.getThanhTien();
        }

        lbl_tongTien.setText((currencyFormat.format(tongTien)));
        lbl_tienGiam.setText("0");
        float tienGiam = 0;
        float thanhTien = tongTien - tienGiam;
        lbl_thanhTien.setText(currencyFormat.format(thanhTien));
    }

    private void showIdHoaDon(int index) {
        BanHangHoaDoncho hd = service_banHang.getAllHoaDonCho().get(index);
        txt_maHoaDon.setText(String.valueOf(hd.getId()));
    }
    // LayID cua 1 hoa don cho
   

    public void clearFr() {
        txt_maHoaDon.setText("");
        txt_soDienThoatKH.setText("");
        txt_tenKhachHang.setText("");
        txt_tienKhachDua.setText("");
        lbl_tienGiam.setText("0");
        lbl_tienThuaTraKhach.setText("0");
        lbl_tongTien.setText("0");
        lbl_thanhTien.setText("0");

    }

    public void setComBoKM() {
        KhuyenMai km1 = new KhuyenMai();
        km1.setTenKhuyenMai("Không dùng");
        cbo_khuyenMai.setModel((DefaultComboBoxModel) model_KhuyenMai);
        model_KhuyenMai.addElement(km1);
        for (KhuyenMai x : service_banHang.getAllKhuyenMai()) {
            model_KhuyenMai.addElement(x);
        }
    }

    public void fillTableSanPhamTimKiem() {
        model_TableSp = (DefaultTableModel) tbl_danhSachSP.getModel();
        model_TableSp.setRowCount(0);
        for (BanHangSanPham kh : service_banHang.getAllTimKiemSanPham(txt_timKiem.getText())) {
            model_TableSp.addRow(new Object[]{kh.getId_SP(), kh.getTenSP(), kh.getGiaSP(), kh.getSoluong()});
        }
    }

    public boolean validateFR() {
        if (txt_maHoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần thanh toán ");
            return false;
        }

        if (txt_tienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa để thanh toán", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validateHD() {
        if (txt_maHoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần thanh toán ");
            return false;
        }

        if (txt_tienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa để thanh toán", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void clearFR() {
        txt_maHoaDon.setText("");
        txt_soDienThoatKH.setText("");
        txt_tenKhachHang.setText("");
        txt_tienKhachDua.setText("");
        lbl_tienGiam.setText("0");
        lbl_tienThuaTraKhach.setText("0");
        lbl_tongTien.setText("0");
        lbl_thanhTien.setText("0");

    }

    private void taoHoaDonPDF(String fileName, String maHoaDon, String ngayTao, String ngayThanhToan,
            String dsSP[], String tongTien, String giamGia, String thanhTien, String khachHang,
            String sdtKhachHang, String httt) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            Paragraph storeDetails = new Paragraph("CUA HANG BAN SACH GIAO KHOA POLYBOOK\nHotline: 0987654321\n Address: To 1 - TT.Kim Bai - Thanh Oai - Ha  Tay", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            storeDetails.setAlignment(Element.ALIGN_CENTER);
            document.add(storeDetails);

            document.add(new Paragraph(" "));

            // Add invoice title
            Paragraph title = new Paragraph("====================HOA DON THANH TOAN================", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("----------------------------------------------------------"));

            // Add invoice details
            Paragraph Ngaytao = new Paragraph("NGAY TAO:               " + ngayTao);
            Paragraph NgayThanhToan = new Paragraph("NGAY THANH TOAN:  " + ngayThanhToan);
            Paragraph MaHoaDon = new Paragraph("MA HOA DON:           " + maHoaDon);
            Paragraph Khachhang = new Paragraph("KHACH HANG:          " + khachHang);
            Paragraph SDTKhachHang = new Paragraph("SĐT KHACH HANG:   " + sdtKhachHang);
            Paragraph HTTT = new Paragraph("HINH THUC THANH TOAN:   " + httt);

            document.add(Ngaytao);
            document.add(NgayThanhToan);
            document.add(new Paragraph("----------------------------------------------------------"));
            document.add(MaHoaDon);
            document.add(Khachhang);
            document.add(SDTKhachHang);
            document.add(HTTT);
            document.add(new Paragraph("----------------------------------------------------------"));

            // Add purchased products
            Paragraph TitleSanPham = new Paragraph("SAN PHAM DA MUA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            document.add(TitleSanPham);

            String TenSanPham = "";
            for (String sp : dsSP) {
                TenSanPham = TenSanPham + sp + "\n";
            }
            Paragraph SanPham = new Paragraph("SAN PHAM BAO GOM: \n" + TenSanPham);
            document.add(SanPham);

            document.add(new Paragraph("----------------------------------------------------------"));

            // Add total, discount, and final amount
            Paragraph tongTien3 = new Paragraph("TONG TIEN:   " + tongTien + " VND");
            Paragraph giamgia = new Paragraph("GIAM GIA:      " + giamGia + " VND");
            Paragraph ThanhTien = new Paragraph("THANH TOAN:  " + thanhTien + " VND");
            Paragraph footer = new Paragraph("===============CAM ON QUY KHACH DA MUA HANG===========", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            document.add(tongTien3);
            document.add(giamgia);
            document.add(ThanhTien);
            document.add(footer);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void inHoaDon() {
        rowHoaDon = tbl_hoaDoncho.getSelectedRow();
        Date currentDate = new Date();
        String maHoaDon = txt_maHoaDon.getText();
        BanHangHoaDoncho hoaDon = service_banHang.getAllHoaDonCho().get(rowHoaDon);
        String ngayTao = dateFormat.format(hoaDon.getNgayTao());
        String ngayThanhToan = dateFormat.format(currentDate);
        ArrayList<BanHangGioHang> list = service_banHang.getAllGioHang(Integer.parseInt(maHoaDon));
        String dsSP[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            BanHangGioHang hdct = list.get(i);
            dsSP[i] = hdct.getTenSanPham() + "        SL: " + hdct.getSoLuong() + "      TT: " + currencyFormat.format(hdct.getThanhTien());
        }
        String tongTien = lbl_tongTien.getText();
        String giamGia = lbl_tienGiam.getText();
        String thanhTien = lbl_thanhTien.getText();
        String sdtKhachHang;
        String khachHang;
        if (txt_tenKhachHang.getText().isEmpty()) {
            khachHang = "Khách Vãng Lai";
        } else {
            khachHang = txt_tenKhachHang.getText();
        }
        if (txt_soDienThoatKH.getText().isEmpty()) {
            sdtKhachHang = "TRONG";
        } else {
            sdtKhachHang = txt_soDienThoatKH.getText();
        }
        String httt = cbo_hinhThucThanhToan.getSelectedItem().toString();

        // Tạo hóa đơn PDF
        String pdfFileName = "HoaDon_" + maHoaDon + ".pdf";
        taoHoaDonPDF(pdfFileName, maHoaDon, ngayTao, ngayThanhToan,
                dsSP, tongTien, giamGia, thanhTien, khachHang, sdtKhachHang, httt);

        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    //Duyệt qua từng ký tự: Kiểm tra từng ký tự trong chuỗi.
    //Kiểm tra ký tự số: Nếu ký tự không phải là số, trả về false.
    //Hoàn tất: Nếu tất cả ký tự đều là số, trả về true.

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachSP = new javax.swing.JTable();
        txt_timKiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbo_danhMuc = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_gioHang = new javax.swing.JTable();
        btn_SuaSPGioHang = new javax.swing.JButton();
        btn_xoaSPGioHang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_hoaDoncho = new javax.swing.JTable();
        btn_taoHoaDon = new javax.swing.JButton();
        btn_huyHoaDon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_maHoaDon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_soDienThoatKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tenKhachHang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbo_khuyenMai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lbl_tienGiam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_thanhTien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbo_hinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txt_tienKhachDua = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lbl_tienThuaTraKhach = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_thanhToan = new javax.swing.JButton();
        lbl_nhanVienID = new javax.swing.JLabel();
        btn_themKhachHangNhanh = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_tongTien = new javax.swing.JLabel();

        main_panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_danhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID SP", "Tên SP", "Giá SP", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_danhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhSachSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachSP);

        txt_timKiem.setText("Tìm kiếm tên san phẩm......");
        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyReleased(evt);
            }
        });

        jLabel8.setText("Lọc");

        cbo_danhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_danhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_danhMucItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_danhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbo_danhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_gioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID SP", "Tên SP", "Giá SP", "Số lượng ", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tbl_gioHang);

        btn_SuaSPGioHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_SuaSPGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rss.png"))); // NOI18N
        btn_SuaSPGioHang.setText("Sửa");
        btn_SuaSPGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaSPGioHangActionPerformed(evt);
            }
        });

        btn_xoaSPGioHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_xoaSPGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trash-2.png"))); // NOI18N
        btn_xoaSPGioHang.setText("Xóa");
        btn_xoaSPGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaSPGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_SuaSPGioHang)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoaSPGioHang)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_SuaSPGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaSPGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_hoaDoncho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID HD", "Nhân viên", "Khách hàng", "Ngày tạo", "Trạng thái"
            }
        ));
        tbl_hoaDoncho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonchoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_hoaDoncho);

        btn_taoHoaDon.setBackground(new java.awt.Color(255, 102, 0));
        btn_taoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_taoHoaDon.setText("Tạo hóa đơn");
        btn_taoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDonActionPerformed(evt);
            }
        });

        btn_huyHoaDon.setBackground(new java.awt.Color(255, 102, 0));
        btn_huyHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_huyHoaDon.setText("Hủy");
        btn_huyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_taoHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_huyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_taoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã hóa đơn");

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("SDT KH");

        txt_soDienThoatKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soDienThoatKHActionPerformed(evt);
            }
        });
        txt_soDienThoatKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_soDienThoatKHKeyReleased(evt);
            }
        });

        jLabel4.setText("Tên khách hàng");

        jLabel5.setText("Mã khuyến mãi");

        cbo_khuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_khuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_khuyenMaiMouseClicked(evt);
            }
        });
        cbo_khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_khuyenMaiActionPerformed(evt);
            }
        });

        lbl_tienGiam.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_tienGiam.setForeground(new java.awt.Color(51, 51, 255));
        lbl_tienGiam.setText("0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("VND");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("VND");

        jLabel13.setText("Thành tiền");

        lbl_thanhTien.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_thanhTien.setForeground(new java.awt.Color(51, 51, 255));
        lbl_thanhTien.setText("0");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("VND");

        jLabel16.setText("Hình thức thanh toán");

        cbo_hinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_hinhThucThanhToan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_hinhThucThanhToanItemStateChanged(evt);
            }
        });

        jLabel17.setText("Tiền khách đưa");

        txt_tienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tienKhachDua.setForeground(new java.awt.Color(102, 0, 255));
        txt_tienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tienKhachDuaMouseClicked(evt);
            }
        });
        txt_tienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienKhachDuaActionPerformed(evt);
            }
        });
        txt_tienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tienKhachDuaKeyReleased(evt);
            }
        });

        jLabel18.setText("Tiền thừa trả khách");

        lbl_tienThuaTraKhach.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_tienThuaTraKhach.setForeground(new java.awt.Color(51, 51, 255));
        lbl_tienThuaTraKhach.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("VND");

        btn_thanhToan.setBackground(new java.awt.Color(255, 102, 0));
        btn_thanhToan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });

        btn_themKhachHangNhanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/badge-plus.png"))); // NOI18N
        btn_themKhachHangNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themKhachHangNhanhActionPerformed(evt);
            }
        });

        jLabel10.setText("Tổng tiền");

        jLabel6.setText("Tiền giảm");

        lbl_tongTien.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_tongTien.setForeground(new java.awt.Color(51, 51, 255));
        lbl_tongTien.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17))
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(22, 22, 22)))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lbl_tienThuaTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel20)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(txt_soDienThoatKH, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_themKhachHangNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lbl_nhanVienID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_maHoaDon, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbo_khuyenMai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_tienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(lbl_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbl_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_nhanVienID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_soDienThoatKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(btn_themKhachHangNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(lbl_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbo_hinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tienThuaTraKhach)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaActionPerformed
        String tienKhachDuaStr = txt_tienKhachDua.getText().trim().replace("₫", "").replaceAll("\\s+", "").replace(".", "").replace(",", ".");
        float thanhTien;
        float tienKhachDua;
        try {
            tienKhachDua = Float.parseFloat(tienKhachDuaStr);
            thanhTien = currencyFormat.parse(lbl_thanhTien.getText()).floatValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tienKhachDua < thanhTien) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn hoặc bằng thành tiền", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        float tienThua = tienKhachDua - thanhTien;
        lbl_tienThuaTraKhach.setText(currencyFormat.format(tienThua));

    }//GEN-LAST:event_txt_tienKhachDuaActionPerformed

    private void btn_taoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDonActionPerformed
        // TODO add your handling code here:
        HoaDon hd = readFormAddHoaDon();
        try {

            int currentHoaDonCount = service_banHang.coutnHoaDon();
            if (currentHoaDonCount >= 20) {
                JOptionPane.showMessageDialog(this, "Bạn đã đạt tới giới hạn hóa đơn. Vui lòng thanh toán hoặc xóa bớt hóa đơn trước khi tạo mới.");
                return;
            }

            if (txt_soDienThoatKH.getText().trim().isEmpty()) {
                if (service_banHang.addHd_khongKhachHang(hd) != null) {
                    JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
                    fillTableHoaDon();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bSại");
                }
            } else {
                if (service_banHang.addHd_coKH(hd) != null) {
                    JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
                    fillTableHoaDon();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btn_taoHoaDonActionPerformed

    private void tbl_hoaDonchoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonchoMouseClicked
        // TODO add your handling code here:
        rowHoaDon = tbl_hoaDoncho.getSelectedRow();
        this.showIdHoaDon(rowHoaDon);
        this.fillTableGiohang();
    }//GEN-LAST:event_tbl_hoaDonchoMouseClicked

    private void btn_huyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyHoaDonActionPerformed
        // TODO add your handling code here:
        rowHoaDon = tbl_hoaDoncho.getSelectedRow();
        if (rowHoaDon < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để hủy", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Chắc Muốn Hủy Hóa Đơn Này Chứ", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            int idHoaDon = Integer.parseInt(txt_maHoaDon.getText());
            //Lặp dữ liệu giỏ hàng để trả về sl về 
            for (BanHangGioHang x : service_banHang.getAllGioHang(idHoaDon)) {
                int soLuongTra = x.getSoLuong();
                int idSPCT = x.getId_sanPham();
                service_banHang.updateSoLuongSanPhamVe(soLuongTra, idSPCT);
            }
            //Xóa giỏ hàng theo idhoaDOn rồi đến hóa đơn
            if (service_banHang.cancelHoaDon(idHoaDon) != null) {
                JOptionPane.showMessageDialog(this, "Hủy thành công");
                this.fillTableHoaDon();
                this.fillTableGiohang();
                this.fillTableSanPham();
                clearFr();
            } else {
                JOptionPane.showMessageDialog(this, "Hủy thất bại");
            }
        }
    }//GEN-LAST:event_btn_huyHoaDonActionPerformed

    private void btn_xoaSPGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaSPGioHangActionPerformed
        // TODO add your handling code here:
        rowGioHang = tbl_gioHang.getSelectedRow();
        if (rowGioHang < 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn sản phẩm muốn xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this, "Bạn xác nhận xóa sản phẩm này chứ", "Xác nhận yêu cầu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            BanHangGioHang giohang = service_banHang.getAllGioHang(Integer.parseInt(txt_maHoaDon.getText())).get(rowGioHang);
            int idHoaDonCT = giohang.getId_SPCT();
            int soLuongGioHang = giohang.getSoLuong();
            int idSanPhamChiTiet = giohang.getId_sanPham();
            int idHoaDon = Integer.valueOf(txt_maHoaDon.getText());
            service_banHang.deleteGioHang(idHoaDon, idSanPhamChiTiet);
            service_banHang.updateSoLuongSanPhamVe(soLuongGioHang, idSanPhamChiTiet);
            fillTableGiohang();
            fillTableSanPham();
        }
    }//GEN-LAST:event_btn_xoaSPGioHangActionPerformed

    private void tbl_danhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachSPMouseClicked
        // TODO add your handling code here:

        rowGioHang = tbl_gioHang.getSelectedRow();
        rowSanPham = tbl_danhSachSP.getSelectedRow();
        rowHoaDon = tbl_hoaDoncho.getSelectedRow();
        if (rowHoaDon < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thêm sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nhapSoLuong = JOptionPane.showInputDialog("Nhập số lượng");
        if (nhapSoLuong == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nhapSoLuong.contains("-") || nhapSoLuong.equals("0")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!nhapSoLuong.isEmpty()) {
            try {
                int soLuong = Integer.parseInt(nhapSoLuong);
                BanHangSanPham sanPham = service_banHang.getAllSanPham().get(rowSanPham);
//                Lấy thông tin sản phẩm từ danh sách sản phẩm dựa vào chỉ số hàng rowSanPham.
                int maSPCT = sanPham.getId_SP();
                int soLuongThuc = sanPham.getSoluong();
                if (soLuong > soLuongThuc) {
                    JOptionPane.showMessageDialog(this, "Số lượng mua lớn hơn số lượng còn lại của sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                service_banHang.updateSoLuongSanPham(soLuong, maSPCT);
                BanHangHoaDoncho hd = service_banHang.getAllHoaDonCho().get(rowHoaDon);
                int dem = 0;
                for (BanHangGioHang gh : service_banHang.getAllGioHang(hd.getId())) {
                    if (gh.getId_sanPham() == maSPCT) {
                        dem++;
                    }
                }
                if (dem == 0) {
                    service_banHang.addGiohang(hd.getId(), maSPCT, soLuong);
                } else {
                    service_banHang.UpdateGiohang_SoLuong(hd.getId(), maSPCT, soLuong);
                }
                fillTableGiohang();
                fillTableSanPham();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tbl_danhSachSPMouseClicked

    private void btn_SuaSPGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaSPGioHangActionPerformed
        // TODO add your handling code here:
        rowGioHang = tbl_gioHang.getSelectedRow();
        if (rowGioHang < 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn sản phẩm muốn Sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nhapSoLuong = JOptionPane.showInputDialog("Nhập số lượng muốn sửa");
        if (nhapSoLuong == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nhapSoLuong.contains("-") || nhapSoLuong.equals("0")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Số Lớn Hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!nhapSoLuong.isEmpty()) {
            try {
                int soLuongSua = Integer.parseInt(nhapSoLuong);
                BanHangGioHang giohang = service_banHang.getAllGioHang(Integer.parseInt(txt_maHoaDon.getText())).get(rowGioHang);
                int idHoaDonCT = giohang.getId_SPCT();
                int soLuongGioHang = giohang.getSoLuong();
                int idSanPhamChiTiet = giohang.getId_sanPham();
                int idHoaDon = Integer.parseInt(txt_maHoaDon.getText());
                BanHangSanPham sanpham = service_banHang.getAllSanPhamByid(idSanPhamChiTiet);
                int soLuongSanPham = sanpham.getSoluong();
                if (soLuongSua > soLuongSanPham) {
                    JOptionPane.showMessageDialog(this, "Số lượng mua lớn hơn số lượng còn lại của sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (soLuongSua > soLuongGioHang) {
                    int soLuongTang = soLuongSua - soLuongGioHang;
                    service_banHang.UpdateGiohang_SoLuong(idHoaDon, idSanPhamChiTiet, soLuongTang);
                    service_banHang.updateSoLuongSanPham(soLuongTang, idSanPhamChiTiet);
                } else {
                    int soLuongGiam = soLuongGioHang - soLuongSua;
                    service_banHang.UpdateGiohang_SoLuongGiam(idHoaDon, idSanPhamChiTiet, soLuongGiam);
                    service_banHang.updateSoLuongSanPhamVe(soLuongGiam, idSanPhamChiTiet);
                }
                this.fillTableGiohang();
                this.fillTableSanPham();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Số lượng phải là số", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

    }//GEN-LAST:event_btn_SuaSPGioHangActionPerformed

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        // TODO add your handling code here:
        try {
            if (validateHD()) {
                int chon = JOptionPane.showConfirmDialog(null, "Bạn chắc muốn thanh toán chứ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (chon == JOptionPane.YES_OPTION) {
                    KhachHang kh = service_banHang.timKiemsdt(txt_soDienThoatKH.getText());
                    KhuyenMai km = (KhuyenMai) model_KhuyenMai.getSelectedItem();

                    // Sử dụng NumberFormat để phân tích số
                    NumberFormat currencyFormat = NumberFormat.getInstance(Locale.getDefault());
                    float tongTien = currencyFormat.parse(lbl_tongTien.getText()).floatValue();
                    float tienGiam = currencyFormat.parse(lbl_tienGiam.getText()).floatValue();
                    float thanhTien = tongTien - tienGiam;

                    Integer idKh;
                    if (txt_soDienThoatKH.getText().isEmpty()) {
                        idKh = 1;
                    } else {
                        idKh = kh.getId_KH();
                    }
                    Integer idKm = km.getId_KhuyenMai();

                    int vtHTTT = cbo_hinhThucThanhToan.getSelectedIndex();

                    Boolean hinhThucThanhToan;
                    if (vtHTTT == 0) {
                        hinhThucThanhToan = false;
                    } else if (vtHTTT == 1) {
                        hinhThucThanhToan = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Bạn phải chọn hình thức thanh toán", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    System.out.println("Tien Giam: " + tienGiam);
                    System.out.println("Tong Tien: " + tongTien);
                    System.out.println("Thanh Tien: " + thanhTien);
                    if (thanhTien <= 0) {
                        JOptionPane.showMessageDialog(this, "Hóa Đơn Phải Có Giá Trị Lơn Hơn 1", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    inHoaDon();
                    int idHoaDon = Integer.parseInt(txt_maHoaDon.getText());
                    // Co khach hàng - khong khuyen mai
                    if (cbo_khuyenMai.getSelectedIndex() == 0 && !txt_soDienThoatKH.getText().trim().isEmpty()) {
                        if (service_banHang.updateHoaDon_CoKhachHang_KhongKhuyenMai(hinhThucThanhToan, tongTien, tienGiam, thanhTien, idHoaDon, idKh) != null) {
                            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                            fillTableSanPham();
                            fillTableHoaDon();
                            fillTableGiohang();
                            clearFR();
                        } else {
                            JOptionPane.showConfirmDialog(this, "Thanh toán thất bại");
                        }
                        // Co khach hang - Co khuyen mai
                    } else if (cbo_khuyenMai.getSelectedIndex() > 0 && !txt_soDienThoatKH.getText().trim().isEmpty()) {
                        if (service_banHang.updateHoaDonCoKhCoKM(idKm, hinhThucThanhToan, tongTien, tienGiam, thanhTien, idHoaDon, idKh) != null) {
                            service_banHang.updateKmSoLuong(idKm);
                            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                            fillTableSanPham();
                            fillTableHoaDon();
                            fillTableGiohang();
                            clearFR();
                        } else {
                            JOptionPane.showConfirmDialog(this, "Thanh toán thất bại");
                        }
                        // khong khach hang-  khong khuyen mai
                    } else if (cbo_khuyenMai.getSelectedIndex() == 0 && txt_soDienThoatKH.getText().trim().isEmpty()) {
                        if (service_banHang.updateHoaDon_KhongKhachHang_KhongKhuyenMai(hinhThucThanhToan, tongTien, tienGiam, thanhTien, idHoaDon) != null) {
                            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                            fillTableSanPham();
                            fillTableHoaDon();
                            fillTableGiohang();
                            clearFR();
                        } else {
                            JOptionPane.showConfirmDialog(this, "Thanh toán thất bại");
                        }
                        // khong khach hang - co khuyen main
                    } else if (cbo_khuyenMai.getSelectedIndex() > 0 && txt_soDienThoatKH.getText().trim().isEmpty()) {
                        boolean phuongThucTT = false;
                        if (service_banHang.updateHoaDonKoKhCoKM(idKm, phuongThucTT, tienGiam, tienGiam, thanhTien, idHoaDon) != null) {
                            service_banHang.updateKmSoLuong(idKm);
                            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                            fillTableSanPham();
                            fillTableHoaDon();
                            fillTableGiohang();
                            clearFR();
                        } else {
                            JOptionPane.showConfirmDialog(this, "Thanh toán thất bại");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void cbo_khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_khuyenMaiActionPerformed
        // TODO add your handling code here:
        try {
            KhuyenMai km = (KhuyenMai) model_KhuyenMai.getSelectedItem();
            if (cbo_khuyenMai.getSelectedIndex() == 0) {
                lbl_tienGiam.setText(currencyFormat.format(km.getGiaTriGiam()));
                lbl_thanhTien.setText(lbl_tongTien.getText());
                return;
            }
            float tongTien = currencyFormat.parse(lbl_tongTien.getText()).floatValue();
            float giamGia = 0;
            if (tongTien >= km.getHoaDonToiThieu()) {
                giamGia = km.getGiaTriGiam();
            } else {
                JOptionPane.showMessageDialog(this, "Bạn không thể áp dụng được mã này");
                cbo_khuyenMai.setSelectedIndex(0);
            }

            lbl_tienGiam.setText(currencyFormat.format(giamGia));
            lbl_thanhTien.setText(currencyFormat.format(tongTien - giamGia));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbo_khuyenMaiActionPerformed

    private void cbo_khuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_khuyenMaiMouseClicked
        // TODO add your handling code here:
        rowHoaDon = tbl_hoaDoncho.getSelectedRow();
        if (rowHoaDon < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước");
            return;
        }
    }//GEN-LAST:event_cbo_khuyenMaiMouseClicked

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:
        fillTableSanPhamTimKiem();
    }//GEN-LAST:event_txt_timKiemActionPerformed

    private void txt_tienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaKeyReleased

    private void txt_soDienThoatKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soDienThoatKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soDienThoatKHActionPerformed

    private void txt_soDienThoatKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_soDienThoatKHKeyReleased
        // TODO add your handling code here:
        String sdt = txt_soDienThoatKH.getText().trim();

        if (sdt.length() == 10) {
            KhachHang kh = service_banHang.timKiemsdt(sdt);

            if (kh != null) {
                txt_tenKhachHang.setText(kh.getTenKH());
            } else {
                JOptionPane.showMessageDialog(this, "Khách hàng không có trên hệ thống");
                txt_tenKhachHang.setText("");
            }
        }
    }//GEN-LAST:event_txt_soDienThoatKHKeyReleased

    private void txt_tienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_tienKhachDuaMouseClicked

    private void cbo_hinhThucThanhToanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_hinhThucThanhToanItemStateChanged
        // TODO add your handling code here:
        Object selectedItem = cbo_hinhThucThanhToan.getSelectedItem();
        if (selectedItem != null) {
            if (!check) {
                if (selectedItem.toString().equals("CHUYEN KHOAN")) {
                    txt_tienKhachDua.setText(lbl_thanhTien.getText());
                }
                check = !check;
            } else {
                check = !check;
            }
        }
    }//GEN-LAST:event_cbo_hinhThucThanhToanItemStateChanged

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
        // TODO add your handling code here:
        fillTableSanPhamTimKiem();
    }//GEN-LAST:event_txt_timKiemKeyReleased

    private void btn_themKhachHangNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themKhachHangNhanhActionPerformed

        ThemKhachHang themKhachHangForm = new ThemKhachHang();
        themKhachHangForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        themKhachHangForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                themKhachHangForm.setVisible(false);
            }
        });
        themKhachHangForm.setVisible(true);

    }//GEN-LAST:event_btn_themKhachHangNhanhActionPerformed

    private void cbo_danhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_danhMucItemStateChanged


    }//GEN-LAST:event_cbo_danhMucItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SuaSPGioHang;
    private javax.swing.JButton btn_huyHoaDon;
    private javax.swing.JButton btn_taoHoaDon;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_themKhachHangNhanh;
    private javax.swing.JButton btn_xoaSPGioHang;
    private javax.swing.JComboBox<String> cbo_danhMuc;
    private javax.swing.JComboBox<String> cbo_hinhThucThanhToan;
    private javax.swing.JComboBox<String> cbo_khuyenMai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_nhanVienID;
    private javax.swing.JLabel lbl_thanhTien;
    private javax.swing.JLabel lbl_tienGiam;
    private javax.swing.JLabel lbl_tienThuaTraKhach;
    private javax.swing.JLabel lbl_tongTien;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTable tbl_danhSachSP;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_hoaDoncho;
    private javax.swing.JTextField txt_maHoaDon;
    private javax.swing.JTextField txt_soDienThoatKH;
    private javax.swing.JTextField txt_tenKhachHang;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
