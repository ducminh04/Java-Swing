/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Service.TacGia_service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhaXuatBan;
import model.TacGia;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class Frame_TacGia extends javax.swing.JFrame {

    TacGia_service ser_tacGia = new TacGia_service();

    /**
     * Creates new form Frame_TacGia
     */
    public Frame_TacGia() {
        initComponents();
        setLocationRelativeTo(null);
        fillTableTacGia();
        txt_idTacGia.setEditable(false);

        txt_ngayTao.setEditable(false);
        txt_ngaySua.setEditable(false);

    }

    public void fillTableTacGia() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_tacGia.getModel();
        dtm.setRowCount(0);
        for (TacGia tg : ser_tacGia.getAllTacGia()) {
            Object[] rowData = {
                tg.getId_tacGia(),
                tg.getTenTacGia(),
                tg.isGioiTinh() == true ? "Nam" : "Nu",
                tg.getQueQuan(),
                tg.getNamSinh(),
                tg.getNamMat(),
                tg.getNgayTao(),
                tg.getNgaySua(),
                tg.isTrangThai() == true ? "Dang hoat dong" : "Ngung hoat dong"
            };
            dtm.addRow(rowData);
        }
    }

    private TacGia checkAddTacGia() {
        String tenTG = txt_tenTacGia.getText();
        if (tenTG.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tác giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String que_quan = txt_queQuan.getText();
        if (que_quan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập quê quán của tác giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        String namSinhStr = txt_namSinh.getText();
        Date namSinh = null;
        try {
            java.util.Date utilNamSinh = sdf.parse(namSinhStr);
            namSinh = new Date(utilNamSinh.getTime());
            int currentYear = LocalDate.now().getYear();
            int namSinhYear = namSinh.toLocalDate().getYear();
            if (namSinhYear <= 0 || namSinhYear > currentYear) {
                JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh hợp lệ (yyyy-MM-dd)", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String namMatStr = txt_namMat.getText();
        Date namMat = null;
        if (!namMatStr.isEmpty()) {
            try {
                java.util.Date utilNamMat = sdf.parse(namMatStr);
                namMat = new Date(utilNamMat.getTime());
                int namMatYear = namMat.toLocalDate().getYear();
                int namSinhYear = namSinh.toLocalDate().getYear();
                if (namMatYear < namSinhYear) {
                    JOptionPane.showMessageDialog(this, "Năm mất không hợp lệ (trước năm sinh)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm mất hợp lệ (yyyy-MM-dd)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        LocalDate ngayTao = LocalDate.now();
        txt_ngayTao.setText(ngayTao.toString());

        TacGia tg = new TacGia();
        tg.setTenTacGia(tenTG);
        tg.setNamSinh(namSinh);
        tg.setNamMat(namMat);
        tg.setQueQuan(que_quan);
        tg.setNgayTao(Date.valueOf(ngayTao));
        tg.setGioiTinh(rdo_Nam.isSelected());
        tg.setTrangThai(rdo_dangHoatDong.isSelected());

        return tg;
    }

    private TacGia checkUpdateTacGia() {
        int Id_tg = Integer.parseInt(txt_idTacGia.getText());
        LocalDate ngaySua = LocalDate.now();
        txt_ngaySua.setText(ngaySua.toString());

        String tenTG = txt_tenTacGia.getText();
        if (tenTG.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tác giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String que_quan = txt_queQuan.getText();
        if (que_quan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập quê quán của tác giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        String namSinhStr = txt_namSinh.getText();
        Date namSinh = null;
        try {
            java.util.Date utilNamSinh = sdf.parse(namSinhStr);
            namSinh = new Date(utilNamSinh.getTime());
            int currentYear = LocalDate.now().getYear();
            int namSinhYear = namSinh.toLocalDate().getYear();
            if (namSinhYear <= 0 || namSinhYear > currentYear) {
                JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh hợp lệ (yyyy-MM-dd)", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String namMatStr = txt_namMat.getText();
        Date namMat = null;
        if (!namMatStr.isEmpty()) {
            try {
                java.util.Date utilNamMat = sdf.parse(namMatStr);
                namMat = new Date(utilNamMat.getTime());
                int namMatYear = namMat.toLocalDate().getYear();
                int namSinhYear = namSinh.toLocalDate().getYear();
                if (namMatYear < namSinhYear) {
                    JOptionPane.showMessageDialog(this, "Năm mất không hợp lệ (trước năm sinh)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm mất hợp lệ (yyyy-MM-dd)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        TacGia tg = new TacGia();
        tg.setId_tacGia(Id_tg);
        tg.setTenTacGia(tenTG);
        tg.setQueQuan(que_quan);
        tg.setNamSinh(namSinh);
        tg.setNamMat(namMat);
        tg.setNgaySua(Date.valueOf(ngaySua));
        tg.setGioiTinh(rdo_Nam.isSelected());
        tg.setTrangThai(rdo_dangHoatDong.isSelected());

        return tg;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        main_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_idTacGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tenTacGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txt_queQuan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namSinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_namMat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ngaySua = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdo_dangHoatDong = new javax.swing.JRadioButton();
        rdo_ngungHoatDong = new javax.swing.JRadioButton();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_timKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tacGia = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID Tác giả");

        jLabel3.setText("Tên tác giả");

        jLabel4.setText("Giới tính");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nu");

        jLabel5.setText("Quê quán");

        jLabel6.setText("Năm sinh");

        jLabel7.setText("Năm mất");

        jLabel8.setText("Ngày tạo");

        jLabel9.setText("Ngày sửa");

        jLabel10.setText("Trạng thái");

        buttonGroup3.add(rdo_dangHoatDong);
        rdo_dangHoatDong.setText("Dang hoat dong");

        buttonGroup3.add(rdo_ngungHoatDong);
        rdo_ngungHoatDong.setText("Ngung hoat dong");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 0)))); // NOI18N

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_tacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Tac gia", "Ten TG", "Gioi tinh", "Que quan", "Nam sinh", "Nam mat", "Ngay tao", "Ngay sua", "Trang thai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_tacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tacGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_tacGia);

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 2, 24)); // NOI18N
        jLabel11.setText("Quản lý tác giả");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(rdo_Nam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdo_Nu)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9))
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(txt_tenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(txt_namSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(main_panelLayout.createSequentialGroup()
                                                .addComponent(txt_queQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10))))
                                    .addGroup(main_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_idTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                        .addComponent(btn_them)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_sua)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_clear)
                                        .addGap(23, 23, 23)))))
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_namMat, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(rdo_dangHoatDong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdo_ngungHoatDong)))
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(17, 17, 17)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_namMat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_tenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdo_Nam)
                            .addComponent(rdo_Nu)))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_queQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(rdo_dangHoatDong)
                                .addComponent(rdo_ngungHoatDong)))
                        .addGap(12, 12, 12)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_namSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_tacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tacGiaMouseClicked
        // TODO add your handling code here:
        try {
            showDetailTacGia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbl_tacGiaMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        TacGia tg = checkAddTacGia();
        if (tg == null) {
            return;
        }
        ser_tacGia.addTacGia(tg);
        JOptionPane.showMessageDialog(this, "Thêm thành công");

        fillTableTacGia();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        int row = tbl_tacGia.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TacGia tg = checkUpdateTacGia();
        if (tg == null) {
            return;
        }
        ser_tacGia.updateTacGia(row, tg);
        JOptionPane.showMessageDialog(this, "Sửa thành công");
        fillTableTacGia();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        txt_idTacGia.setText("");
        txt_namMat.setText("");
        txt_namSinh.setText("");
        txt_ngaySua.setText("");
        txt_ngayTao.setText("");
        txt_queQuan.setText("");
        txt_tenTacGia.setText("");

    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_timKiemActionPerformed

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
        // TODO add your handling code here:
        ArrayList<TacGia> ds = ser_tacGia.searchTacGia(txt_timKiem.getText());
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_tacGia.getModel();
        dtm.setRowCount(0);
        for (TacGia tg : ds) {
            Object[] rowData = {
                tg.getId_tacGia(),
                tg.getTenTacGia(),
                tg.isGioiTinh() == true ? "Nam" : "Nu",
                tg.getQueQuan(),
                tg.getNamSinh(),
                tg.getNamMat(),
                tg.getNgayTao(),
                tg.getNgaySua(),
                tg.isTrangThai() == true ? "Dang hoat dong" : "Ngung hoat dong",};
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_txt_timKiemKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_TacGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_TacGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_TacGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_TacGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_TacGia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JRadioButton rdo_dangHoatDong;
    private javax.swing.JRadioButton rdo_ngungHoatDong;
    private javax.swing.JTable tbl_tacGia;
    private javax.swing.JTextField txt_idTacGia;
    private javax.swing.JTextField txt_namMat;
    private javax.swing.JTextField txt_namSinh;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_queQuan;
    private javax.swing.JTextField txt_tenTacGia;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables

    private void showDetailTacGia() {
        int index = tbl_tacGia.getSelectedRow();

        // Kiểm tra nếu không có dòng nào được chọn
        if (index == -1) {
            return; // Không làm gì nếu không có dòng nào được chọn
        }

        // Kiểm tra giá trị null trước khi gọi toString()
        Object idTG = tbl_tacGia.getValueAt(index, 0);
        if (idTG != null) {
            txt_idTacGia.setText(idTG.toString());
        } else {
            txt_idTacGia.setText(""); // Hoặc một giá trị mặc định khác
        }

        Object tenTG = tbl_tacGia.getValueAt(index, 1);
        if (tenTG != null) {
            txt_tenTacGia.setText(tenTG.toString());
        } else {
            txt_tenTacGia.setText("");
        }
        Object gioiTinh = tbl_tacGia.getValueAt(index, 2);
        if (gioiTinh != null && gioiTinh.equals("Nam")) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }
        Object queQuan = tbl_tacGia.getValueAt(index, 3);
        if (queQuan != null) {
            txt_queQuan.setText(queQuan.toString());
        } else {
            txt_queQuan.setText("");
        }
        Object namSinh = tbl_tacGia.getValueAt(index, 4);
        if (namSinh != null) {
            txt_namSinh.setText(namSinh.toString());
        } else {
            txt_namSinh.setText("");
        }
        Object namMat = tbl_tacGia.getValueAt(index, 5);
        if (namMat != null) {
            txt_namMat.setText(namMat.toString());
        } else {
            txt_namMat.setText("");
        }

        Object ngayTao = tbl_tacGia.getValueAt(index, 6);
        if (ngayTao != null) {
            txt_ngayTao.setText(ngayTao.toString());
        } else {
            txt_ngayTao.setText("");
        }

        Object ngaySua = tbl_tacGia.getValueAt(index, 7);
        if (ngaySua != null) {
            txt_ngaySua.setText(ngaySua.toString());
        } else {
            txt_ngaySua.setText("");
        }

        Object trangThai = tbl_tacGia.getValueAt(index, 8);
        if (trangThai != null) {
            String trangThaiStr = trangThai.toString().trim();
            if (trangThaiStr.equalsIgnoreCase("Dang hoat dong")) {
                rdo_dangHoatDong.setSelected(true);
            } else {
                rdo_ngungHoatDong.setSelected(true);
            }
        } else {
            rdo_ngungHoatDong.setSelected(true);
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
