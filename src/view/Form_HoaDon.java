/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.HoaDon_Model;
import Service.BanHang_service;
import Service.HoaDon_service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BanHangGioHang;

/**
 *
 * @author HP
 */
public class Form_HoaDon extends javax.swing.JPanel {

    DefaultTableModel mol_hoaDon = new DefaultTableModel();
    DefaultTableModel mol_HDCT = new DefaultTableModel();
    int index = -1;
    HoaDon_service ser_hoaDon = new HoaDon_service();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    BanHang_service service_BanHang = new BanHang_service();

    public Form_HoaDon() throws SQLException {
        initComponents();
        fillTableHoaDon(ser_hoaDon.getAllHoaDon());
    }

    private void fillTableHoaDon(List<HoaDon_Model> list) {
        DefaultTableModel mol_hoaDon = (DefaultTableModel) tbl_hoaDon.getModel();
        mol_hoaDon.setRowCount(0);
        for (HoaDon_Model hd : list) {

            mol_hoaDon.addRow(hd.addRow());
        }
    }

    private void fillTableHDCT(List<HoaDonChiTiet> lists) {
        mol_HDCT = (DefaultTableModel) tbl_hoaDonChitiet.getModel();
        mol_HDCT.setRowCount(0);
        for (HoaDonChiTiet hdct : lists) {
            mol_HDCT.addRow(hdct.addRowHDCT());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        main_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_tongTien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_hoaDonChitiet = new javax.swing.JTable();
        btn_xuatFile = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        main_panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý hóa đơn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tổng tiền hóa đơn : ");

        lbl_tongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        tbl_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Hóa đơn", "Tên khách hàng", "Tên nhân viên", "Tổng tiền ", "Giảm giá", "Thành tiền", "Ngày tạo", "Ngày thanh toán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        tbl_hoaDonChitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Hóa đơn chi tiết", "ID Hóa đơn", "Tên sản phẩm", "Giá bán", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_hoaDonChitiet);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btn_xuatFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xuatFile.setText("Xuất File PDF");
        btn_xuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_xuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_xuatFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_tongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonMouseClicked
        // TODO add your handling code here:
        index = tbl_hoaDon.getSelectedRow();
        if (index > -1) {
            try {
                fillTableHDCT(ser_hoaDon.getAllHoaDonChiTiet((int) tbl_hoaDon.getValueAt(index, 0)));
                lbl_tongTien.setText(tbl_hoaDon.getValueAt(index, 5).toString());
            } catch (SQLException ex) {
                Logger.getLogger(Form_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbl_hoaDonMouseClicked

    private void btn_xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatFileActionPerformed
        // TODO add your handling code here:
        index = tbl_hoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xuất hóa đơn");
            return;
        }
        try {
            // TODO add your handling code here:
            inHoaDon();
        } catch (SQLException ex) {
            Logger.getLogger(Form_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xuatFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xuatFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_tongTien;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTable tbl_hoaDon;
    private javax.swing.JTable tbl_hoaDonChitiet;
    // End of variables declaration//GEN-END:variables

    private void inHoaDon() throws SQLException {
        index = tbl_hoaDon.getSelectedRow();
        HoaDon_Model hoaDonModel = ser_hoaDon.getAllHoaDon().get(index);
        Date currentDate = new Date();
        int maHoaDon = hoaDonModel.getId_hoaDon();
        String ngayTao = dateFormat.format(hoaDonModel.getNgayTao());
        String ngayThanhToan = dateFormat.format(hoaDonModel.getNgaySua());
        ArrayList<BanHangGioHang> list = service_BanHang.getAllGioHang(maHoaDon);
        String dsSP[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            BanHangGioHang hdct = list.get(i);
            dsSP[i] = hdct.getTenSanPham() + "        SL: " + hdct.getSoLuong() + "      TT: " + currencyFormat.format(hdct.getThanhTien());
        }
        String tongTien = tbl_hoaDon.getValueAt(index, 3).toString();
        String giamGia = tbl_hoaDon.getValueAt(index, 4).toString();
        String thanhTien = tbl_hoaDon.getValueAt(index, 5).toString();
        String khachHang = hoaDonModel.getId_KhachHang();
        String tenNhanVien = hoaDonModel.getId_NhanVien();

        // Tạo hóa đơn PDF
        String pdfFileName = "ThongKeHoaDon_" + maHoaDon + ".pdf";
        taoHoaDonPDF(pdfFileName, maHoaDon, ngayTao, ngayThanhToan,
                dsSP, tongTien, giamGia, thanhTien, khachHang, tenNhanVien);

        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void taoHoaDonPDF(String fileName, int maHoaDon, String ngayTao, String ngayThanhToan, String[] dsSP, String tongTien, String giamGia, String thanhTien, String khachHang, String tenNhanVien) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            // Định dạng cho các đoạn văn bản căn giữa và in đậm
            Paragraph centerAlignedBold = new Paragraph();
            centerAlignedBold.setAlignment(Paragraph.ALIGN_CENTER);
            centerAlignedBold.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD));

            // Tên cửa hàng, địa chỉ, hotline
            centerAlignedBold.add("CUA HANG BAN SACH GIAO KHOA POLYBOOK\n");
            centerAlignedBold.add("Addess: To 1-TT.Kim Bai-Thanh Oai-Ha Tay\n");
            centerAlignedBold.add("Hotline: 0989789876\n");

            // Phần tiêu đề hóa đơn
            Paragraph title = new Paragraph("==================== HOA DON ====================");
            Paragraph light = new Paragraph("------------------------------------------------");
            Paragraph MaHoaDon = new Paragraph("MA HOA DON: " + maHoaDon);
            Paragraph Khachhang = new Paragraph("TEN NHAN VIEN: " + tenNhanVien);
            Paragraph TenNhanVien = new Paragraph("TEN KHACH HANG: " + khachHang);
            Paragraph Ngaytao = new Paragraph("NGAY TAO: " + ngayTao);
            Paragraph NgayThanhToan = new Paragraph("NGAY THANH TOAN: " + ngayThanhToan);
            Paragraph light1 = new Paragraph("-------------------------------------");
            Paragraph TitleSanPham = new Paragraph("==========SAN PHAM DA MUA =======");
            String TenSanPham = "";
            for (int i = 0; i < dsSP.length; i++) {
                TenSanPham = TenSanPham + dsSP[i] + "\n";
            }
            Paragraph SanPham = new Paragraph("SAN PHAM BAO GOM: \n" + TenSanPham);
            Paragraph tongTien3 = new Paragraph("TONG TIEN: " + tongTien + " VND");
            Paragraph giamgia = new Paragraph("GIAM GIA: " + giamGia + " VND");
            Paragraph ThanhTien = new Paragraph("THANH TOAN: " + thanhTien + " VND");

            document.add(centerAlignedBold);
            document.add(title);
            document.add(light);
            document.add(MaHoaDon);
            document.add(Khachhang);
            document.add(TenNhanVien);
            document.add(Ngaytao);
            document.add(NgayThanhToan);
            document.add(light1);
            document.add(TitleSanPham);
            document.add(SanPham);
            document.add(light1);
            document.add(tongTien3);
            document.add(giamgia);
            document.add(ThanhTien);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}