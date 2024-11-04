/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.NhanVien_Service;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;

/**
 *
 * @author HP
 */
public class Form_NhanVien extends javax.swing.JPanel {

    private int index = -1;
    private NhanVien_Service service = new NhanVien_Service();
    DefaultTableModel molTable = new DefaultTableModel();
    DefaultTableModel mol_search = new DefaultTableModel();
    boolean check = false;
    boolean check2 = false;

    public Form_NhanVien() {
        initComponents();
        fillTable();
        txt_IDNhanVien.setEditable(false);
        txt_ngayTao.setEditable(false);
        txt_ngaySua.setEditable(false);

    }

    private void fillTable() {
        molTable = (DefaultTableModel) tbl_NhanVien.getModel();
        molTable.setRowCount(0);
        List<NhanVien> list = null;
        String find = txt_timKiemMa.getText().trim();
        list = service.getAllNhanVien();
        for (NhanVien nv : list) {
            molTable.addRow(nv.toDataRow());
        }

    }

    private void fillTableSearch() {
        mol_search = (DefaultTableModel) tbl_NhanVien.getModel();
        mol_search.setRowCount(0);
        String find = txt_timKiemMa.getText().trim();
        ArrayList<NhanVien> list = service.findNhanVien(find, find);
        for (NhanVien nv : list) {
            molTable.addRow(nv.toDataRow());
        }
    }

    private void showNhanVien(int index) {
        NhanVien nv = service.getAllNhanVien().get(index);
        txt_IDNhanVien.setText(String.valueOf(nv.getId_NV()));
        txt_tenNhanVien.setText(nv.getTenNhanVien());
        txt_ngaySinh.setText(String.valueOf(nv.getNgaysinh()));
        if (nv.isGioiTinh() == true) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }
        txt_email.setText(nv.getEmail());
        txt_soDienThoai.setText(nv.getSdt());
        txt_ngayTao.setText(String.valueOf(nv.getNgayTao()));
        txt_ngaySua.setText(String.valueOf(nv.getNgaySua()));
        if (nv.isTrangThai() == true) {
            rdo_DanglamViec.setSelected(true);
        } else {
            rdo_DaNghi.setSelected(true);
        }
        txt_tenDangNhap.setText(nv.getUsername());
        txt_matKhau.setText(nv.getPasswork());

        if (nv.isVaiTro() == true) {
            rdo_quanLy.setSelected(true);
        } else {
            rdo_nhanVien.setSelected(true);
        }
    }

    private void showFindNhanVien(int index) {
        String find = txt_timKiemMa.getText().trim();
        NhanVien nv = service.findNhanVien(find, find).get(index);
        txt_IDNhanVien.setText(String.valueOf(nv.getId_NV()));
        txt_tenNhanVien.setText(nv.getTenNhanVien());
        txt_ngaySinh.setText(String.valueOf(nv.getNgaysinh()));
        if (nv.isGioiTinh() == true) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }
        txt_email.setText(nv.getEmail());
        txt_soDienThoai.setText(nv.getSdt());
        txt_ngayTao.setText(String.valueOf(nv.getNgayTao()));
        txt_ngaySua.setText(String.valueOf(nv.getNgaySua()));
        if (nv.isTrangThai() == true) {
            rdo_DanglamViec.setSelected(true);
        } else {
            rdo_DaNghi.setSelected(true);
        }
        txt_tenDangNhap.setText(nv.getUsername());
        txt_matKhau.setText(nv.getPasswork());
        if (nv.isVaiTro() == true) {
            rdo_quanLy.setSelected(true);
        } else {
            rdo_nhanVien.setSelected(true);
        }
    }

    private NhanVien add() {
        NhanVien nv = new NhanVien();
        nv.setTenNhanVien(txt_tenNhanVien.getText().trim());
        nv.setEmail(txt_email.getText().trim());
        nv.setSdt(txt_soDienThoai.getText().trim());
        nv.setUsername(txt_tenDangNhap.getText().trim());
        nv.setPasswork(new String(txt_matKhau.getPassword()));

        // Xử lý ngày sinh
        String ngaySinhStr = txt_ngaySinh.getText().trim();
        if (!ngaySinhStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date ngaySinh = dateFormat.parse(ngaySinhStr);
                nv.setNgaysinh(ngaySinh);
            } catch (ParseException e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình chuyển đổi
            }
        }

        // Xử lý giới tính
        if (rdo_Nam.isSelected()) {
            nv.setGioiTinh(true);
        } else {
            nv.setGioiTinh(false);
        }

        // Xử lý trạng thái làm việc
        if (rdo_DanglamViec.isSelected()) {
            nv.setTrangThai(true);
        } else {
            nv.setTrangThai(false);
        }

        // Xử lý vai trò
        if (rdo_quanLy.isSelected()) {
            nv.setVaiTro(true);
        } else {
            nv.setVaiTro(false);
        }

        return nv;
    }

    private NhanVien update() {
        NhanVien nv = new NhanVien();
        nv.setId_NV(Integer.valueOf(txt_IDNhanVien.getText()));
        nv.setTenNhanVien(txt_tenNhanVien.getText().trim());
        nv.setEmail(txt_email.getText().trim());

        if (rdo_Nam.isSelected()) {
            nv.setGioiTinh(true);
        } else {
            nv.setGioiTinh(false);
        }

        nv.setSdt(txt_soDienThoai.getText().trim());
        nv.setUsername(txt_tenDangNhap.getText().trim());
        nv.setPasswork(new String(txt_matKhau.getPassword()));

        if (rdo_DanglamViec.isSelected()) {
            nv.setTrangThai(true);
        } else {
            nv.setTrangThai(false);
        }

        if (rdo_quanLy.isSelected()) {
            nv.setVaiTro(true);
        } else {
            nv.setVaiTro(false);
        }

        return nv;
    }

    private boolean validateForm() {
        try {
            if (txt_tenNhanVien.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Họ và tên của nhân viên");
                txt_tenNhanVien.requestFocus();
                return false;
            }
            if (!txt_tenNhanVien.getText().matches("^[\\p{L}\\s'-]+$")) {
                JOptionPane.showMessageDialog(this, "Tên nhân viên không được chứa kí tự đặc biệt");
                txt_tenNhanVien.requestFocus();
                return false;
            }

            String ngaySinhStr = txt_ngaySinh.getText().trim();
            if (ngaySinhStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Ngày sinh");
                txt_ngaySinh.requestFocus();
                return false;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Không cho phép các giá trị không hợp lệ
            try {
                dateFormat.parse(ngaySinhStr); // Kiểm tra xem có parse được không
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày sinh (yyyy-MM-dd)");
                txt_ngaySinh.requestFocus();
                return false;
            }

            if (!rdo_Nam.isSelected() && !rdo_Nu.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
                return false;
            }

            if (txt_email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Email");
                txt_email.requestFocus();
                return false;
            }

            if (txt_soDienThoai.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Số điện thoại");
                txt_soDienThoai.requestFocus();
                return false;
            }

            if (txt_tenDangNhap.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập UserName");
                txt_tenDangNhap.requestFocus();
                return false;
            }

            if (txt_matKhau.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Password");
                txt_matKhau.requestFocus();
                return false;
            }

            if (!rdo_DanglamViec.isSelected() && !rdo_DaNghi.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái");
                return false;
            }

            if (!rdo_nhanVien.isSelected() && !rdo_quanLy.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vai trò");
                return false;
            }

            String chkemail = "\\w+@\\w{3,8}+\\.+\\w{2,4}";
            String chksdt = "\\d{10}";

            if (!txt_email.getText().matches(chkemail)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email");
                txt_email.requestFocus();
                return false;
            }

            if (!txt_soDienThoai.getText().matches(chksdt)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng Số điện thoại (10 số)");
                txt_soDienThoai.requestFocus();
                return false;
            }

        } catch (Exception e) {
            System.out.println("Lỗi validate");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void fillTableTK() {
        mol_search = (DefaultTableModel) tbl_NhanVien.getModel();
        mol_search.setRowCount(0);
        String find = txt_timKiemMa.getText().trim();
        ArrayList<NhanVien> list = service.findNhanVien(find, find);
        for (NhanVien nv : list) {
            molTable.addRow(nv.toDataRow());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_tenNhanVien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_IDNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ngaySinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        txt_ngaySua = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdo_DanglamViec = new javax.swing.JRadioButton();
        rdo_DaNghi = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txt_tenDangNhap = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdo_quanLy = new javax.swing.JRadioButton();
        rdo_nhanVien = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_matKhau = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        btn_tthem = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txt_timKiemMa = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1017, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel1.setText("ID Nhân viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel2.setText("Tên nhân viên ");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel3.setText("Ngày sinh");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel4.setText("Gioi tính");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel7.setText("So dien thoai");

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel8.setText("Ngày tao");

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel9.setText("Ngày sua");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setSelected(true);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nu");

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel10.setText("Trang thai");

        buttonGroup3.add(rdo_DanglamViec);
        rdo_DanglamViec.setSelected(true);
        rdo_DanglamViec.setText("Đang làm việc ");

        buttonGroup3.add(rdo_DaNghi);
        rdo_DaNghi.setText("Đã nghỉ");

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel11.setText("Tên dang nhap");

        jLabel12.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel12.setText("Mat khau");

        jLabel13.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jLabel13.setText("Vai trò");

        buttonGroup5.add(rdo_quanLy);
        rdo_quanLy.setSelected(true);
        rdo_quanLy.setText("Quản lý");

        buttonGroup5.add(rdo_nhanVien);
        rdo_nhanVien.setText("Nhân viên");

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_IDNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                        .addComponent(txt_tenNhanVien)
                                        .addComponent(txt_ngaySinh)
                                        .addComponent(txt_email))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdo_Nam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdo_Nu))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(rdo_quanLy)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdo_nhanVien))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_matKhau))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel9))
                                            .addComponent(jLabel10))
                                        .addGap(24, 24, 24))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdo_DanglamViec)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdo_DaNghi))
                                    .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_IDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_tenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_DanglamViec)
                    .addComponent(rdo_DaNghi)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(rdo_Nam)
                        .addComponent(rdo_Nu))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_tenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(rdo_quanLy)
                            .addComponent(rdo_nhanVien))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13), new java.awt.Color(255, 51, 0))); // NOI18N

        btn_tthem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_tthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/circle-plus.png"))); // NOI18N
        btn_tthem.setText("Thêm");
        btn_tthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tthemActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/panel-bottom-open.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
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
                .addContainerGap()
                .addComponent(btn_tthem)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua)
                .addGap(12, 12, 12)
                .addComponent(btn_clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tthem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_timKiemMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemMaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiemMa)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txt_timKiemMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13), new java.awt.Color(255, 0, 0))); // NOI18N

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên NV", "Ngày sinh", "Giới tính", "Email", "Số điện thoại", "Ngày tạo", "Ngày sửa", "Trạng thái", "Tên đăng nhập", "Mật khẩu", "Vai trò"
            }
        ));
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_NhanVien);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 36, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        txt_IDNhanVien.setText("");
        txt_tenNhanVien.setText("");
        txt_ngaySinh.setText("");
        txt_email.setText("");
        txt_soDienThoai.setText("");
        txt_tenDangNhap.setText("");
        txt_matKhau.setText("");
        txt_ngayTao.setText("");
        txt_ngaySua.setText("");

        // Đặt lại các nút radio về trạng thái mặc định
        rdo_Nam.setSelected(false);
        rdo_Nu.setSelected(false);
        rdo_quanLy.setSelected(false);
        rdo_nhanVien.setSelected(false);
        rdo_DanglamViec.setSelected(false);
        rdo_DaNghi.setSelected(false);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        // TODO add your handling code here:
        try {
            index = tbl_NhanVien.getSelectedRow();
            if (txt_timKiemMa.getText().trim().isEmpty()) {
                this.showNhanVien(index);
            } else {
                this.showFindNhanVien(index);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void btn_tthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tthemActionPerformed
        // TODO add your handling code here:
        for (NhanVien nv : service.getAllNhanVien()) {
            if (nv.getEmail().equals(txt_email.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại, vui lòng nhập email khác");
                return;
            }
            if (nv.getSdt().equals(txt_soDienThoai.getText().trim())) {
                JOptionPane.showMessageDialog(this, "SĐT đã tồn tại, vui lòng nhập SĐT khác");
                return;
            }
            if (nv.getUsername().equals(txt_tenDangNhap.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Tên đang nhập đã tồn tại, vui lòng nhập UserName khác");
                return;
            }
        }
        try {
            if (validateForm()) {
                NhanVien nv = add();
                if (service.add(nv) != null) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                    this.fillTable();
                    btn_clearActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("Thêm lỗi ở hệ thống");
        }

    }//GEN-LAST:event_btn_tthemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        index = tbl_NhanVien.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        }
        try {
            NhanVien nv = this.update();
            if (validateForm()) {
                if (service.updateNhanVien(nv) != null) {
                    JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công");
                    this.fillTable();
                    btn_clearActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btn_SuaActionPerformed

    private void txt_timKiemMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemMaKeyReleased
        // TODO add your handling code here:
fillTableSearch();
    }//GEN-LAST:event_txt_timKiemMaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_tthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdo_DaNghi;
    private javax.swing.JRadioButton rdo_DanglamViec;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JRadioButton rdo_nhanVien;
    private javax.swing.JRadioButton rdo_quanLy;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextField txt_IDNhanVien;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_matKhau;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_tenDangNhap;
    private javax.swing.JTextField txt_tenNhanVien;
    private javax.swing.JTextField txt_timKiemMa;
    // End of variables declaration//GEN-END:variables
}
