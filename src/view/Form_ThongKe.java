/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.ThongKeSanPham_service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ThongKeSP;

/**
 *
 * @author HP
 */
public class Form_ThongKe extends javax.swing.JPanel {

    DecimalFormat df = new DecimalFormat("###,###,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
    ThongKeSanPham_service ser_thongKe = new ThongKeSanPham_service();
 NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    /**
     * Creates new form Form_ThongKe
     */
    public Form_ThongKe() {
        initComponents();
        fillTable();
    }

    void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_doanhThuSP.getModel();
        dtm.setRowCount(0);

        for (ThongKeSP tksp : ser_thongKe.getAll()) {
            Object[] rowData = {
                tksp.getTenSP(),
                tksp.getSoLuong(),
                currencyFormat.format(tksp.getTongTien())
            };
            dtm.addRow(rowData);
        }
    }

    private boolean validateInput(Date startDate, Date endDate) {
        // Kiểm tra rỗng
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Date currentDate = new Date();

        // Kiểm tra startDate phải lớn hơn ngày hiện tại
        if (startDate.after(currentDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải bé hơn ngày hiện tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra endDate phải lớn hơn ngày hiện tại
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (startDate.equals(endDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void inHoaDon() {
        Date startDate = txt_startDate.getDate();
        Date endDate = txt_endDate.getDate();
        String ngayBD = dateFormat.format(startDate);
        String ngayKT = dateFormat.format(endDate);
        ArrayList<ThongKeSP> list = ser_thongKe.theoCBO(startDate, endDate);
        String dsSP[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ThongKeSP tksp = list.get(i);
            dsSP[i] = tksp.getTenSP() + "        SL: " + tksp.getSoLuong() + "      TT: " + df.format(tksp.getTongTien());
        }
        String tongTien = lbl_doanhThu.getText();
        String sl = lbl_tongSPDaBan.getText();
        String ngayBD1 = dateFormat1.format(startDate);
        String ngayKT1 = dateFormat1.format(endDate);
        // Tạo hóa đơn PDF
        String pdfFileName = "ThongKeSPTheoNgay_" + ngayBD1 + "to" + ngayKT1 + ".pdf";
        taoHoaDonPDF(pdfFileName, ngayBD, ngayKT, dsSP, tongTien, sl);
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void taoHoaDonPDF(String fileName, String ngayBD, String ngayKT,
            String dsSP[], String tongTien, String slSP) {
        Document document = new Document();
        document.addLanguage(document.toString());
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph title = new Paragraph("====================THONG KE SAN PHAM THEO NGAY====================");
            Paragraph light = new Paragraph("-------------------------------------------------------------------");
            Paragraph Ngaytao = new Paragraph("NGAY BAT DAU:              " + ngayBD);
            Paragraph NgayThanhToan = new Paragraph("NGAY KET THUC: " + ngayKT);
            Paragraph light1 = new Paragraph("-------------------------------------");
            Paragraph TitleSanPham = new Paragraph("========SAN PHAM DA BAN=======");
            String TenSanPham = "";
            for (int i = 0; i < dsSP.length; i++) {
                TenSanPham = TenSanPham + dsSP[i] + "\n";
            }
            Paragraph SanPham = new Paragraph("SAN PHAM BAO GOM: \n" + TenSanPham);
            Paragraph sp = new Paragraph("SO LUONG DA BAN:   " + slSP);
            Paragraph tongTien3 = new Paragraph("TONG DOANH THU:   " + tongTien);
            document.add(title);
            document.add(light);
            document.add(Ngaytao);
            document.add(NgayThanhToan);
            document.add(light1);
            document.add(TitleSanPham);
            document.add(SanPham);
            document.add(light1);
            document.add(sp);
            document.add(tongTien3);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
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

        main_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_startDate = new com.toedter.calendar.JDateChooser();
        txt_endDate = new com.toedter.calendar.JDateChooser();
        btn_tim = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_doanhThu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_tongSPDaBan = new javax.swing.JLabel();
        btn_xuatFile = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_doanhThuSP = new javax.swing.JTable();

        main_panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Từ ngày");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Đến ngày");

        btn_tim.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search-slash.png"))); // NOI18N
        btn_tim.setText("Tim");
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(btn_tim)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addComponent(txt_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(204, 51, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Doanh thu");

        lbl_doanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_doanhThu.setForeground(new java.awt.Color(51, 0, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Số lượng sản phẩm đã bán");

        lbl_tongSPDaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_tongSPDaBan.setForeground(new java.awt.Color(51, 0, 255));

        btn_xuatFile.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_xuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/upload.png"))); // NOI18N
        btn_xuatFile.setText("Xuất File PDF");
        btn_xuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_tongSPDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xuatFile))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tongSPDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh thu san pham", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        tbl_doanhThuSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng đã bán", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(tbl_doanhThuSP);
        if (tbl_doanhThuSP.getColumnModel().getColumnCount() > 0) {
            tbl_doanhThuSP.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
        // TODO add your handling code here:
        Date startDate = txt_startDate.getDate();
        Date endDate = txt_endDate.getDate();
        if (validateInput(startDate, endDate)) {
            ArrayList<ThongKeSP> ds = ser_thongKe.theoCBO(startDate, endDate);
            DefaultTableModel dtm = (DefaultTableModel) this.tbl_doanhThuSP.getModel();
            dtm.setRowCount(0);
            for (ThongKeSP tksp : ds) {
                Object[] rowData = {
                    tksp.getTenSP(),
                    tksp.getSoLuong(),
                    tksp.getTongTien()
                };
                dtm.addRow(rowData);
            }

            int tongSoLuong = 0;
            for (int i = 0; i < tbl_doanhThuSP.getRowCount(); i++) {
                int soLuong = Integer.parseInt(tbl_doanhThuSP.getValueAt(i, 1).toString());
                tongSoLuong += soLuong;
            }
            lbl_tongSPDaBan.setText("" + tongSoLuong);

            int tongDoanhThu = 0;
            for (int i = 0; i < tbl_doanhThuSP.getRowCount(); i++) {
                double doanhThu = Double.parseDouble(tbl_doanhThuSP.getValueAt(i, 2).toString());
                tongDoanhThu += doanhThu;
            }
            String formattedNumber = df.format(tongDoanhThu);
            lbl_doanhThu.setText("" + formattedNumber + " VND");
        }
    }//GEN-LAST:event_btn_timActionPerformed

    private void btn_xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatFileActionPerformed
        // TODO add your handling code here:
        Date startDate = txt_startDate.getDate();
        Date endDate = txt_endDate.getDate();
        if (validateInput(startDate, endDate)) {
            inHoaDon();
        }
    }//GEN-LAST:event_btn_xuatFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_xuatFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_doanhThu;
    private javax.swing.JLabel lbl_tongSPDaBan;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTable tbl_doanhThuSP;
    private com.toedter.calendar.JDateChooser txt_endDate;
    private com.toedter.calendar.JDateChooser txt_startDate;
    // End of variables declaration//GEN-END:variables
}
