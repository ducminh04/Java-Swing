/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.DanhMuc_service;
import Service.NhaCungCap_service;
import Service.NhaXuatBan_service;
import Service.SPCT_service;
import Service.TacGia_service;
import Service.TheLoai_service;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhaCungCap;
import model.NhaXuatBan;
import model.SanPhamChiTiet;
import model.SanPhamDetail_model;
import model.TacGia;
import model.TheLoai;
import model.Truyen_Data;
import java.sql.Date;
import java.util.List;
import model.DanhMuc;
import model.NhanVien;
import model.SanPham;
import utils.Auth;

/**
 *
 * @author HP
 */
public class Form_SanPhamChiTiet extends javax.swing.JPanel {

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("Vi", "VN"));
    NhaCungCap_service NCCService = new NhaCungCap_service();
    DefaultTableModel molSPCT = new DefaultTableModel();
    SPCT_service SPCTService = new SPCT_service();
    TacGia_service tacGiaService = new TacGia_service();
    NhaXuatBan_service nhaXuatBanService = new NhaXuatBan_service();
    TheLoai_service tlService = new TheLoai_service();
    int index = -1;
    Truyen_Data tdl = new Truyen_Data();
    DefaultComboBoxModel<NhaCungCap> molNcc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<NhaXuatBan> molNxb = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<TacGia> molTacGia = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<TheLoai> molTheLoai = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<DanhMuc> molDanhMuc = new DefaultComboBoxModel<>();
    DanhMuc_service danhMucService = new DanhMuc_service();

    public Form_SanPhamChiTiet() {
        initComponents();
        setTG();
        setTheLoai();
        setNCC();
        setNXB();
        txt_IDsanPham.setText(tdl.getMa());
        txt_tenSanPham.setText(tdl.getTen());
        txt_nguoiTao.setText(tdl.getNguoiTao());
        txt_nguoiSua.setText(tdl.getNguoiSua());

        fillTable();
        txt_IDSPCT.setEditable(false);
        txt_IDsanPham.setEditable(false);
        txt_ngayTao.setEditable(false);
        txt_ngaySua.setEditable(false);
        txt_tenSanPham.setEditable(false);
        txt_nguoiTao.setEditable(false);
        txt_nguoiSua.setEditable(false);
    }

    public void setNCC() {
        CBO_NhaCungCap.setModel((DefaultComboBoxModel) molNcc);
        CBO_NhaCungCap.removeAllItems();
        for (NhaCungCap x : NCCService.getAllNCC()) {
            molNcc.addElement(x);
        }
    }

    public void setTG() {
        CBO_TacGia.setModel((DefaultComboBoxModel) molTacGia);
        CBO_TacGia.removeAllItems();
        for (TacGia x : tacGiaService.getAllTacGia()) {
            molTacGia.addElement(x);
        }
    }

    public void setNXB() {
        CBO_NXB.setModel((DefaultComboBoxModel) molNxb);
        CBO_NXB.removeAllItems();
        for (NhaXuatBan x : nhaXuatBanService.getAllNhaXuatban()) {
            molNxb.addElement(x);
        }
    }

    public void setTheLoai() {
        CBO_theLoai.setModel((DefaultComboBoxModel) molTheLoai);
        CBO_theLoai.removeAllItems();
        for (TheLoai x : tlService.getAllTheLoai()) {
            molTheLoai.addElement(x);
        }
    }

    public void fillTable() {
//        molSPCT = (DefaultTableModel) tbl_SPCT.getModel();
        DefaultTableModel model = (DefaultTableModel) tbl_TTSPCT.getModel();
        model.setRowCount(0);
        for (SanPhamDetail_model x : SPCTService.findAllSanPhamChiTiet()) {
            model.addRow(x.toDataRow());
        }
    }

    public void showForm(int index) {
        SanPhamChiTiet spct = SPCTService.getAllID().get(index);

        // Đặt giá trị cho combobox
        int selectedNCCIndex = -1;
        for (int i = 0; i < molNcc.getSize(); i++) {
            NhaCungCap ncc = (NhaCungCap) molNcc.getElementAt(i);
            if (ncc.getId_NCC() == spct.getId_NhaCungCap()) {
                selectedNCCIndex = i;
                break;
            }
        }
        int selectedTGIndex = -1;
        for (int i = 0; i < molTacGia.getSize(); i++) {
            TacGia tg = (TacGia) molTacGia.getElementAt(i);
            if (tg.getId_tacGia() == spct.getId_TacGia()) {
                selectedTGIndex = i;
                break;
            }
        }
        int selectedNXBIndex = -1;
        for (int i = 0; i < molNxb.getSize(); i++) {
            NhaXuatBan nxb = (NhaXuatBan) molNxb.getElementAt(i);
            if (nxb.getId_NXB() == spct.getId_NXB()) {
                selectedNXBIndex = i;
                break;
            }
        }
        int selectedTLIndex = -1;
        for (int i = 0; i < molTheLoai.getSize(); i++) {
            TheLoai tl = (TheLoai) molTheLoai.getElementAt(i);
            if (tl.getId_TheLoai() == spct.getId_theLoai()) {
                selectedTLIndex = i;
                break;
            }
        }

        CBO_NXB.setSelectedIndex(selectedNXBIndex);
        CBO_TacGia.setSelectedIndex(selectedTGIndex);
        CBO_NhaCungCap.setSelectedIndex(selectedNCCIndex);
        CBO_theLoai.setSelectedIndex(selectedTLIndex);

        SanPhamDetail_model sp = SPCTService.findAllSanPhamChiTiet().get(index);
        txt_IDSPCT.setText(String.valueOf(sp.getId_SanPhamChiTiet()));
        txt_IDsanPham.setText(String.valueOf(sp.getId_SanPham()));
        txt_tenSanPham.setText(sp.getTen_SanPham());

        // Định dạng và đặt giá trị cho các trường ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txt_ngayTao.setText(sp.getNgayTao() != null ? sdf.format(sp.getNgayTao()) : ""); // Đảm bảo không có giá trị null
        txt_ngaySua.setText(sp.getNgaySua() != null ? sdf.format(sp.getNgaySua()) : "");
        txt_soLuong.setText(String.valueOf(sp.getSoLuong()));
        txt_giaBan.setText(String.valueOf(sp.getGiaBan()));
        txt_nguoiTao.setText(sp.getNguoiTao() != null ? sp.getNguoiTao() : ""); // Thêm giá trị này 
        txt_nguoiSua.setText(sp.getNguoiSua() != null ? sp.getNguoiSua() : ""); // Thêm giá trị này
        if (sp.isTrangThai()) {
            rdo_DangBan.setSelected(true);
        } else {
            rdo_ngungBan.setSelected(true);
        }
    }

    private SanPhamChiTiet readFormAdd() {
        NhaCungCap ncc = (NhaCungCap) molNcc.getSelectedItem();
        NhaXuatBan nxb = (NhaXuatBan) molNxb.getSelectedItem();
        TacGia tg = (TacGia) molTacGia.getSelectedItem();
        TheLoai tl = (TheLoai) molTheLoai.getSelectedItem();
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setId_SanPham(Integer.parseInt(txt_IDsanPham.getText()));
        spct.setId_NXB(nxb.getId_NXB());
        spct.setId_TacGia(tg.getId_tacGia());
        spct.setId_NhaCungCap(ncc.getId_NCC());
        spct.setId_theLoai(tl.getId_TheLoai());
        spct.setSoLuong(Integer.parseInt(txt_soLuong.getText()));
        spct.setGiaBan(Float.parseFloat(txt_giaBan.getText()));
        if (rdo_DangBan.isSelected()) {
            spct.setTrangThai(true);
        } else {
            spct.setTrangThai(false);
        }
        NhanVien currentUser = Auth.user;
        if (currentUser != null) {
            spct.setNguoiTao(currentUser.getTenNhanVien());
        } else {
            spct.setNguoiTao("Không biết");
        }
        return spct;
    }

    private SanPhamChiTiet readFormUpdate() {
        NhaCungCap ncc = (NhaCungCap) molNcc.getSelectedItem();
        NhaXuatBan nxb = (NhaXuatBan) molNxb.getSelectedItem();
        TacGia tg = (TacGia) molTacGia.getSelectedItem();
        TheLoai tl = (TheLoai) molTheLoai.getSelectedItem();

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setId_SanPhamChiTiet(Integer.parseInt(txt_IDSPCT.getText()));
        spct.setId_SanPham(Integer.parseInt(txt_IDsanPham.getText()));
        spct.setId_NXB(nxb.getId_NXB());
        spct.setId_TacGia(tg.getId_tacGia());
        spct.setId_NhaCungCap(ncc.getId_NCC());
        spct.setId_theLoai(tl.getId_TheLoai());
        spct.setNgaySua(java.sql.Date.valueOf(LocalDate.now())); // Thiết lập ngày sửa
        spct.setSoLuong(Integer.parseInt(txt_soLuong.getText()));
        spct.setGiaBan(Float.parseFloat(txt_giaBan.getText()));

        if (rdo_DangBan.isSelected()) {
            spct.setTrangThai(true);
        } else {
            spct.setTrangThai(false);
        }
        NhanVien currentUser = Auth.user;
        if (currentUser != null) {
            spct.setNguoiSua(currentUser.getTenNhanVien());
        } else {
            spct.setNguoiSua("Không biết");
        }

        return spct;
    }

    private boolean validateForm() {
        String maSpText = txt_IDsanPham.getText();
        String soLuongText = txt_soLuong.getText();
        String giaBanText = txt_giaBan.getText();
        if (!rdo_DangBan.isSelected() && !rdo_ngungBan.isSelected()) {
            JOptionPane.showMessageDialog(this, "Trạng thái không được để trống");
            return false;
        }

        if (maSpText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống");
            return false;
        }
        try {
            int maSp = Integer.parseInt(maSpText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm phải là một số nguyên");
            return false;
        }

        if (soLuongText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            return false;
        }
        try {
            int soLuong = Integer.parseInt(soLuongText);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số lớn hơn không");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên");
            return false;
        }

        if (giaBanText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống");
            return false;
        }
        try {
            float giaBan = Float.parseFloat(giaBanText);
            if (giaBan < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán phải là một số lớn hơn không");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là một số thực");
            return false;
        }

        if (molNcc.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp");
            return false;
        }
        if (molNxb.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà xuất bản");
            return false;
        }
        if (molTacGia.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tác giả");
            return false;
        }

        return true;
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_TTSPCT = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_IDSPCT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_IDsanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tenSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CBO_NXB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        CBO_TacGia = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        CBO_NhaCungCap = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_ngaySua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_nguoiSua = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_nguoiTao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdo_DangBan = new javax.swing.JRadioButton();
        rdo_ngungBan = new javax.swing.JRadioButton();
        txt_giaBan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_nhaXuatBan = new javax.swing.JButton();
        btn_tacGia = new javax.swing.JButton();
        btn_nhaCungCap = new javax.swing.JButton();
        CBO_theLoai = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txt_timKiem = new javax.swing.JTextField();
        btn_theLoai = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        tbl_TTSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SPCT", "Ten SP", "Nha xuat ban", "Tac gia", "Nhà cung cấp", "Ngay them", "Ngay sua", "Nguoi tao", "Nguoi sua", "So luong", "Gia ban", "The loai", "Trang thai"
            }
        ));
        tbl_TTSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TTSPCTMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_TTSPCT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

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
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập sản phẩm chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 51, 0))); // NOI18N

        jLabel1.setText("Id SPCT");

        jLabel2.setText("Id SP");

        jLabel3.setText("Tên SP");

        jLabel4.setText("Nhà xuất bản");

        CBO_NXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_NXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBO_NXBMouseClicked(evt);
            }
        });
        CBO_NXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_NXBActionPerformed(evt);
            }
        });

        jLabel5.setText("Tác giả");

        CBO_TacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_TacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBO_TacGiaMouseClicked(evt);
            }
        });

        jLabel6.setText("Nhà cung cấp");

        CBO_NhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_NhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBO_NhaCungCapMouseClicked(evt);
            }
        });

        jLabel7.setText("Ngày tao");

        jLabel8.setText("Ngày sửa");

        jLabel9.setText("Người tạo");

        jLabel10.setText("Người sửa");

        txt_nguoiTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nguoiTaoActionPerformed(evt);
            }
        });

        jLabel11.setText("Số lượng");

        jLabel12.setText("Giá bán");

        jLabel13.setText("Trạng thái");

        buttonGroup1.add(rdo_DangBan);
        rdo_DangBan.setText("Dang ban");

        buttonGroup1.add(rdo_ngungBan);
        rdo_ngungBan.setText("Ngung ban");

        jLabel14.setText("Thể loại");

        btn_nhaXuatBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/badge-plus.png"))); // NOI18N
        btn_nhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nhaXuatBanActionPerformed(evt);
            }
        });

        btn_tacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/badge-plus.png"))); // NOI18N
        btn_tacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tacGiaActionPerformed(evt);
            }
        });

        btn_nhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/badge-plus.png"))); // NOI18N
        btn_nhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nhaCungCapActionPerformed(evt);
            }
        });

        CBO_theLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_theLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBO_theLoaiMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 51, 0))); // NOI18N

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
        btn_clear.setText("Làm mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_them)
                .addGap(18, 18, 18)
                .addComponent(btn_sua)
                .addGap(18, 18, 18)
                .addComponent(btn_clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_them, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        btn_theLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/badge-plus.png"))); // NOI18N
        btn_theLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_theLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tenSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txt_IDsanPham, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_IDSPCT, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBO_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBO_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_nhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(btn_tacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CBO_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(CBO_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdo_DangBan)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_ngungBan)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(285, 285, 285))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel8)
                                        .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CBO_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14))
                                    .addComponent(btn_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(rdo_DangBan)
                                    .addComponent(rdo_ngungBan)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txt_IDsanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)
                                        .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(txt_IDSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6)
                                                .addComponent(btn_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11)
                                                .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(CBO_NhaCungCap)))
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_nhaXuatBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CBO_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_tacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CBO_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CBO_NXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_NXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_NXBActionPerformed

    private void btn_nhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nhaCungCapActionPerformed
        // TODO add your handling code here:

        Frame_NCC ncc_form = new Frame_NCC();
        ncc_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ncc_form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                ncc_form.setVisible(false);
            }
        });
        ncc_form.setVisible(true);
    }//GEN-LAST:event_btn_nhaCungCapActionPerformed

    private void btn_nhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nhaXuatBanActionPerformed
        // TODO add your handling code here:
        Frame_NhaXuatBan nxbForm = new Frame_NhaXuatBan();
        nxbForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nxbForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                nxbForm.setVisible(false);
            }
        });
        nxbForm.setVisible(true);
    }//GEN-LAST:event_btn_nhaXuatBanActionPerformed

    private void CBO_NXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_NXBMouseClicked
        // TODO add your handling code here:
        setNXB();
    }//GEN-LAST:event_CBO_NXBMouseClicked

    private void CBO_TacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_TacGiaMouseClicked
        // TODO add your handling code here:
        setTG();
    }//GEN-LAST:event_CBO_TacGiaMouseClicked

    private void CBO_NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_NhaCungCapMouseClicked
        // TODO add your handling code here:
        setNCC();
    }//GEN-LAST:event_CBO_NhaCungCapMouseClicked

    private void CBO_theLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBO_theLoaiMouseClicked
        // TODO add your handling code here:
        setTheLoai();
    }//GEN-LAST:event_CBO_theLoaiMouseClicked

    private void tbl_TTSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TTSPCTMouseClicked
        // TODO add your handling code here:
        index = tbl_TTSPCT.getSelectedRow();
        this.showForm(index);
    }//GEN-LAST:event_tbl_TTSPCTMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        try {
            if (validateForm()) {
                SanPhamChiTiet sp = readFormAdd();
                if (SPCTService.addSanPhamChiTiet(sp) != null) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");

                    this.fillTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_tacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tacGiaActionPerformed
        // TODO add your handling code here:
        Frame_TacGia tacGia_form = new Frame_TacGia();
        tacGia_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tacGia_form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                tacGia_form.setVisible(false);
            }
        });
        tacGia_form.setVisible(true);
    }//GEN-LAST:event_btn_tacGiaActionPerformed

    private void btn_theLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_theLoaiActionPerformed
        // TODO add your handling code here:
        Frame_TheLoai tl_form = new Frame_TheLoai();
        tl_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tl_form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                tl_form.setVisible(false);
            }
        });
        tl_form.setVisible(true);
    }//GEN-LAST:event_btn_theLoaiActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        try {
            if (validateForm()) {
                SanPhamChiTiet sp = readFormUpdate();
                Integer result = SPCTService.updateSanPhamChiTiet(sp);
                if (result != null && result > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    this.fillTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại. Không có hàng nào được cập nhật.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        txt_IDSPCT.setText("");
        txt_IDsanPham.setText("");
        txt_giaBan.setText("");
        txt_ngaySua.setText("");
        txt_ngayTao.setText("");
        txt_nguoiSua.setText("");
        txt_nguoiTao.setText("");
        txt_soLuong.setText("");
        txt_tenSanPham.setText("");
        rdo_DangBan.setSelected(false);
        rdo_ngungBan.setSelected(false);


    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_nguoiTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nguoiTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nguoiTaoActionPerformed

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbl_TTSPCT.getModel();
        model.setRowCount(0);
        for (SanPhamDetail_model x : SPCTService.findByName(txt_timKiem.getText().trim())) {
            model.addRow(x.toDataRow());
        }
    }//GEN-LAST:event_txt_timKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBO_NXB;
    private javax.swing.JComboBox<String> CBO_NhaCungCap;
    private javax.swing.JComboBox<String> CBO_TacGia;
    private javax.swing.JComboBox<String> CBO_theLoai;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_nhaCungCap;
    private javax.swing.JButton btn_nhaXuatBan;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_tacGia;
    private javax.swing.JButton btn_theLoai;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdo_DangBan;
    private javax.swing.JRadioButton rdo_ngungBan;
    private javax.swing.JTable tbl_TTSPCT;
    private javax.swing.JTextField txt_IDSPCT;
    private javax.swing.JTextField txt_IDsanPham;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_nguoiSua;
    private javax.swing.JTextField txt_nguoiTao;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_tenSanPham;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
