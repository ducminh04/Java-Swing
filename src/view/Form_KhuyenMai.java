/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.KhuyenMai;
import Service.KhuyenMai_service;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Auth;

/**
 *
 * @author HP
 */
public class Form_KhuyenMai extends javax.swing.JPanel {

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    private final KhuyenMai_service ser_khuyenMai = new KhuyenMai_service();
    private List<KhuyenMai> khuyenMaiList;
    int index;

    /**
     * Creates new form Form_KhuyenMai
     */
    public Form_KhuyenMai() {
        initComponents();
        loadAllTableKhuyenMai();
        rdo_dangDienRa.setEnabled(false);
        rdo_daKetThuc.setEnabled(false);
        txt_idKM.setEditable(false);
        txt_ngayTao.setEditable(false);
    }

    private void loadAllTableKhuyenMai() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_KhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : ser_khuyenMai.getAllKhuyenMai2()) {
            Object[] rowData = {
                km.getId_KhuyenMai(),
                km.getTenKhuyenMai(),
                km.getSoLuong(),
                km.getNgayTao(),
                km.getNguoiTao(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                currencyFormat.format(km.getGiaTriGiam()),
                currencyFormat.format(km.getHoaDonToiThieu()),
                km.getTrangThai() ? "DANG DIEN RA" : "CHUA DIEN RA",};
            dtm.addRow(rowData);
        }

    }

private KhuyenMai ReadFormAdd() {
    String ten = txt_tenKM.getText();
    int soLuong = Integer.parseInt(txt_soLuong.getText());
    LocalDate ngayTao = LocalDate.now();
    String nguoiTao = Auth.user != null ? Auth.user.getTenNhanVien() : "Trống";
    Date ngayBatDau = txt_ngayBatDau.getDate();
    Date ngayKetThuc = txt_ngayKetThuc.getDate();
    int giaTriGiam = Integer.parseInt(txt_giaTriGiam.getText());
    float hoaDonToiThieu = Float.parseFloat(txt_hoaDonToiThieu.getText());
    boolean trangThai = rdo_dangDienRa.isSelected();

    KhuyenMai km = new KhuyenMai();
    km.setTenKhuyenMai(ten);
    km.setSoLuong(soLuong);
    km.setNgayTao(ngayTao);
    km.setNguoiTao(nguoiTao); // Gán tên người tạo từ Auth.use
    km.setNgayBatDau(ngayBatDau);
    km.setNgayKetThuc(ngayKetThuc);
    km.setGiaTriGiam(giaTriGiam);
    km.setHoaDonToiThieu(hoaDonToiThieu);
    km.setTrangThai(trangThai);
    return km;
}

    private KhuyenMai ReadFormUpdate() {

        int ma = Integer.parseInt(txt_idKM.getText());
        String ten = txt_tenKM.getText();
        int giaTriGiam = Integer.parseInt(txt_giaTriGiam.getText());
        int soLuong = Integer.parseInt(txt_soLuong.getText());
        Date ngayBatDau = txt_ngayBatDau.getDate();
        Date ngayKetThuc = txt_ngayKetThuc.getDate();
        float hoaDonToiThieu = Float.parseFloat(txt_hoaDonToiThieu.getText());
        boolean status = rdo_dangDienRa.isSelected();
        KhuyenMai km = new KhuyenMai();
        km.setSoLuong(soLuong);
        km.setId_KhuyenMai(ma);
        km.setTenKhuyenMai(ten);
        km.setGiaTriGiam(giaTriGiam);
        km.setNgayBatDau(ngayBatDau);
        km.setNgayKetThuc(ngayKetThuc);
        km.setHoaDonToiThieu(hoaDonToiThieu);
        km.setTrangThai(status);

        return km;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_idKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_tenKM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_nguoiTao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_giaTriGiam = new javax.swing.JTextField();
        txt_hoaDonToiThieu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdo_dangDienRa = new javax.swing.JRadioButton();
        rdo_daKetThuc = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txt_timKiem = new javax.swing.JTextField();
        txt_ngayBatDau = new com.toedter.calendar.JDateChooser();
        txt_ngayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_KhuyenMai = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("ID Khuyến mãi");

        txt_idKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idKMActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Tên khuyến mãi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Số lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Ngày tạo");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Người tạo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Ngày bắt đầu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Ngày kết thúc");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setText("Giá trị giảm");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setText("Hóa đơn tối thiểu");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel10.setText("Trạng thái");

        buttonGroup1.add(rdo_dangDienRa);
        rdo_dangDienRa.setText("DANG DIEN RA");

        buttonGroup1.add(rdo_daKetThuc);
        rdo_daKetThuc.setText("CHUA DIEN RA");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/circle-plus.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/panel-bottom-open.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/file-x.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_clear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel5.setToolTipText("");

        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiem)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_idKM, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_hoaDonToiThieu, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_giaTriGiam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdo_dangDienRa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_daKetThuc))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txt_idKM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_tenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7))
                            .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_giaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_hoaDonToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(rdo_dangDienRa)
                            .addComponent(rdo_daKetThuc)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(204, 0, 0))); // NOI18N

        tbl_KhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Khuyến mãi", "Tên khuyến mãi", "Số lượng", "Ngày tạo", "Người tạo", "Ngày bắt đầu", "Ngày kết thúc", "Gía trị giảm", "Hóa đơn tối thiểu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_KhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_KhuyenMai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        String name = txt_tenKM.getText().strip();
        String soLuong = txt_soLuong.getText().trim();
        String ngayTao = txt_ngayTao.getText().trim();
        String nguoiTao = txt_nguoiTao.getText().trim();
        Date ngayBatDau = txt_ngayBatDau.getDate();
        Date ngayKetThuc = txt_ngayKetThuc.getDate();
        String giaTriGiam = txt_giaTriGiam.getText().trim();
        String hoaDonToiThieu = txt_hoaDonToiThieu.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống tên của chương trình khuyến mãi");
            return;
        }
        if (ngayBatDau == null || ngayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không được để trống");
            return;
        }
        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }

        if (giaTriGiam.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống giá trị giảm");
            return;
        }
        if (hoaDonToiThieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống hóa đơn tối thiểu");
            return;
        }
        if (soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống số lượng");
            return;
        }

        try {
            int checkSL = Integer.parseInt(soLuong);
            if (checkSL < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là kiểu số");
            return;
        }

        try {
            int checkGiaTri = Integer.parseInt(giaTriGiam);
            if (checkGiaTri < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm phải là kiểu số");
            return;
        }

        try {
            int checkHoaDonToiThieu = Integer.parseInt(hoaDonToiThieu);
            if (checkHoaDonToiThieu < 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn tối thiểu phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hóa đơn tối thiểu phải là kiểu số");
            return;
        }

        if (Integer.parseInt(hoaDonToiThieu) != 0 && Integer.parseInt(giaTriGiam) != 0) {
            if (Integer.parseInt(hoaDonToiThieu) <= Integer.parseInt(giaTriGiam)) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm phải bé hơn hóa đơn tối thiểu");
                return;
            }
        }

        KhuyenMai km = ReadFormAdd();
        if (km == null) {
            return;
        }
        ser_khuyenMai.addKhuyenMai(km);
        loadAllTableKhuyenMai();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        txt_idKM.setText("");
        txt_tenKM.setText("");
        txt_ngayTao.setText("");
        txt_nguoiTao.setText("");
        txt_ngayBatDau.setDate(new Date());
        txt_ngayKetThuc.setDate(new Date());
        txt_soLuong.setText("");
        txt_giaTriGiam.setText("");
        txt_hoaDonToiThieu.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_idKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKMActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        int row = tbl_KhuyenMai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        String name = txt_tenKM.getText().strip();
        String soLuong = txt_soLuong.getText().trim();
        String nguoiTao = txt_nguoiTao.getText().trim();
        String giaTriGiam = txt_giaTriGiam.getText().trim();
        String hoaDonToiThieu = txt_hoaDonToiThieu.getText().trim();
        Date ngayBatDau = txt_ngayBatDau.getDate();
        Date ngayKetThuc = txt_ngayKetThuc.getDate();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống tên chương trình khuyến mãi");
            return;
        }
        if (soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống số lượng");
            return;
        }
        try {
            int checkSL = Integer.parseInt(soLuong);
            if (checkSL < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng là kiểu số");
            return;
        }

        if (ngayBatDau == null || ngayKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không được để trống ");
            return;
        }
        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc ");
            return;
        }
        if (giaTriGiam.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống giá trị giảm ");
            return;
        }
        if (hoaDonToiThieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống hóa đơn tối thiểu");
            return;
        }
        try {
            int checkGiaTri = Integer.parseInt(giaTriGiam);
            if (checkGiaTri < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm là kiểu số");
            return;
        }

        try {
            double checkHoaDonToiThieu = Double.parseDouble(hoaDonToiThieu);
            if (checkHoaDonToiThieu < 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn tối thiểu phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hóa đơn tối thiểu  là kiểu số");
            return;
        }

        if (Double.parseDouble(hoaDonToiThieu) <= Integer.parseInt(giaTriGiam)) {
            JOptionPane.showMessageDialog(this, "Giá Trị Giảm Phải Bé Hơn Hóa Đơn Tối Thiểu");
            return;
        }

        KhuyenMai km = ReadFormUpdate();
        if (km == null) {
            return;
        }
        ser_khuyenMai.updateKhuyenMai(row, km);
        loadAllTableKhuyenMai();
        btn_clearActionPerformed(evt);

    }//GEN-LAST:event_btn_suaActionPerformed

    private void tbl_KhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhuyenMaiMouseClicked
        // TODO add your handling code here:
        int row = tbl_KhuyenMai.getSelectedRow();
        String giaTriGiam = tbl_KhuyenMai.getValueAt(row, 7).toString().replaceAll("[^0-9]", "");
        String hoaDonToiThieu = tbl_KhuyenMai.getValueAt(row, 8).toString().replaceAll("[^0-9]", "");
        txt_idKM.setText(tbl_KhuyenMai.getValueAt(row, 0).toString());
        txt_tenKM.setText(tbl_KhuyenMai.getValueAt(row, 1).toString());
        txt_giaTriGiam.setText(giaTriGiam);
        txt_soLuong.setText(tbl_KhuyenMai.getValueAt(row, 2).toString());
        txt_ngayTao.setText(tbl_KhuyenMai.getValueAt(row, 3).toString());
        txt_nguoiTao.setText(tbl_KhuyenMai.getValueAt(row, 4).toString());
        txt_hoaDonToiThieu.setText(hoaDonToiThieu);
        Date ngayBatDau = (Date) tbl_KhuyenMai.getValueAt(row, 5);
        Date ngayKetThuc = (Date) tbl_KhuyenMai.getValueAt(row, 6);
        txt_ngayBatDau.setDate(ngayBatDau);
        txt_ngayKetThuc.setDate(ngayKetThuc);
        if (tbl_KhuyenMai.getValueAt(row, 9).equals("DANG DIEN RA")) {
            rdo_dangDienRa.setSelected(true);
        } else {
            rdo_daKetThuc.setSelected(true);
        }
    }//GEN-LAST:event_tbl_KhuyenMaiMouseClicked

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
        // TODO add your handling code here:
        ArrayList<KhuyenMai> listKM = ser_khuyenMai.timKiemsanPham(txt_timKiem.getText());
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_KhuyenMai.getModel();
        dtm.setRowCount(0);
        for (KhuyenMai km : listKM) {
            Object[] rowData = {
                km.getId_KhuyenMai(),
                km.getTenKhuyenMai(),
                km.getSoLuong(),
                km.getNgayTao(),
                km.getNguoiTao(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getGiaTriGiam(),
                km.getHoaDonToiThieu(),
                km.getTrangThai() ? "DANG DIEN RA" : "CHUA DIEN RA",};
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_txt_timKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdo_daKetThuc;
    private javax.swing.JRadioButton rdo_dangDienRa;
    private javax.swing.JTable tbl_KhuyenMai;
    private javax.swing.JTextField txt_giaTriGiam;
    private javax.swing.JTextField txt_hoaDonToiThieu;
    private javax.swing.JTextField txt_idKM;
    private com.toedter.calendar.JDateChooser txt_ngayBatDau;
    private com.toedter.calendar.JDateChooser txt_ngayKetThuc;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_nguoiTao;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_tenKM;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
