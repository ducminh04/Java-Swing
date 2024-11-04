/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Service.NhaCungCap_service;
import Service.NhaXuatBan_service;
import Service.SPCT_service;
import Service.TacGia_service;
import Service.TheLoai_service;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.NhaCungCap;
import model.NhaXuatBan;
import model.SanPhamChiTiet;
import model.SanPhamDetail_model;
import model.TacGia;
import model.TheLoai;
import model.Truyen_Data;

/**
 *
 * @author HP
 */
public class Form_XemSPCT extends javax.swing.JPanel {

    NhaCungCap_service NCCService = new NhaCungCap_service();
    DefaultTableModel molSPCT = new DefaultTableModel();
    SPCT_service SPCTService = new SPCT_service();
    TacGia_service tacGia_service = new TacGia_service();
    NhaXuatBan_service nxb_service = new NhaXuatBan_service();
    TheLoai_service serTheLoai = new TheLoai_service();
    int index = -1;
    Truyen_Data tdl = new Truyen_Data();
    DefaultComboBoxModel<NhaCungCap> molNcc = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<NhaXuatBan> molNxb = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<TacGia> molTacGia = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<TheLoai> molTheLoai = new DefaultComboBoxModel<>();

    /**
     * Creates new form Form_XemSPCT
     */
    public Form_XemSPCT() {
        initComponents();
        txt_idSP.setText(tdl.getMa());
        txt_tenSP.setText(tdl.getTen());
        setNCC();
        setTG();
        setTheLoai();
        setNXB();
        fillTable();
        txt_giaBan.setEditable(false);
        txt_idSP.setEditable(false);
        txt_id_spct.setEditable(false);
        txt_ngaySua.setEditable(false);
        txt_ngayTao.setEditable(false);
        txt_nguoiSua.setEditable(false);
        txt_nguoiTao.setEditable(false);
        txt_soLong.setEditable(false);
        txt_tenSP.setEditable(false);
        rdo_dangBan.setEnabled(false);
        rdo_ngungBan.setEnabled(false);
        cbo_Ncc.setEnabled(false);
        CBO_TacGia.setEnabled(false);
        cbo_theLoai.setEnabled(false);
        CBO_NXB.setEnabled(false);
    }

    public void fillTable() {
//        molSPCT = (DefaultTableModel) tbl_SPCT.getModel();
        DefaultTableModel model = (DefaultTableModel) tbl_sanPhamChiTiet.getModel();
        model.setRowCount(0);
        for (SanPhamDetail_model x : SPCTService.findAllSanPhamChiTiet()) {
            model.addRow(x.toDataRow());
        }
    }

    public void setNCC() {
        cbo_Ncc.setModel((DefaultComboBoxModel) molNcc);
        for (NhaCungCap x : NCCService.getAllNCC()) {
            molNcc.addElement(x);
        }
    }

    public void setTG() {
        CBO_TacGia.setModel((DefaultComboBoxModel) molTacGia);
        for (TacGia x : tacGia_service.getAllTacGia()) {
            molTacGia.addElement(x);
        }
    }

    public void setNXB() {
        CBO_NXB.setModel((DefaultComboBoxModel) molNxb);
        for (NhaXuatBan x : nxb_service.getAllNhaXuatban()) {
            molNxb.addElement(x);
        }
    }

    public void setTheLoai() {
        cbo_theLoai.setModel((DefaultComboBoxModel) molTheLoai);
        for (TheLoai x : serTheLoai.getAllTheLoai()) {
            molTheLoai.addElement(x);
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
        cbo_Ncc.setSelectedIndex(selectedNCCIndex);
        cbo_theLoai.setSelectedIndex(selectedTLIndex);

        SanPhamDetail_model sp = SPCTService.findAllSanPhamChiTiet().get(index);
        txt_id_spct.setText(String.valueOf(sp.getId_SanPhamChiTiet()));
        txt_idSP.setText(String.valueOf(sp.getId_SanPham()));
        txt_tenSP.setText(sp.getTen_SanPham());

        // Định dạng và đặt giá trị cho các trường ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txt_ngayTao.setText(sp.getNgayTao() != null ? sdf.format(sp.getNgayTao()) : ""); // Đảm bảo không có giá trị null
        txt_ngaySua.setText(sp.getNgaySua() != null ? sdf.format(sp.getNgaySua()) : "");
        txt_soLong.setText(String.valueOf(sp.getSoLuong()));
        txt_giaBan.setText(String.valueOf(sp.getGiaBan()));
        txt_nguoiTao.setText(sp.getNguoiTao() != null ? sp.getNguoiTao() : ""); // Thêm giá trị này 
        txt_nguoiSua.setText(sp.getNguoiSua() != null ? sp.getNguoiSua() : ""); // Thêm giá trị này
        if (sp.isTrangThai()) {
            rdo_dangBan.setSelected(true);
        } else {
            rdo_ngungBan.setSelected(true);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        main_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id_spct = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_idSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CBO_NXB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        CBO_TacGia = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbo_Ncc = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_ngaySua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_nguoiTao = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_nguoiSua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_soLong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdo_dangBan = new javax.swing.JRadioButton();
        rdo_ngungBan = new javax.swing.JRadioButton();
        txt_giaBan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbo_theLoai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanPhamChiTiet = new javax.swing.JTable();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

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
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Xem sản phẩm chi tiêt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 51, 0))); // NOI18N

        jLabel1.setText("Id SPCT");

        jLabel2.setText("Id SP");

        jLabel3.setText("Tên SP");

        jLabel4.setText("Nhà xuất bản");

        CBO_NXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_NXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_NXBActionPerformed(evt);
            }
        });

        jLabel5.setText("Tác giả");

        CBO_TacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Nhà cung cấp");

        cbo_Ncc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Ngày tạo");

        jLabel8.setText("Ngày sửa");

        jLabel9.setText("Người tạo");

        jLabel10.setText("Người sửa");

        jLabel11.setText("Số lượng");

        jLabel12.setText("Gía bán");

        jLabel13.setText("Trạng thái");

        buttonGroup1.add(rdo_dangBan);
        rdo_dangBan.setText("Dang ban");

        buttonGroup1.add(rdo_ngungBan);
        rdo_ngungBan.setText("Ngung ban");

        jLabel14.setText("Thể loại");

        cbo_theLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_spct, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idSP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_Ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_dangBan)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_ngungBan)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_soLong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(cbo_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(77, 77, 77))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbo_Ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11)
                                    .addComponent(txt_soLong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_id_spct, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_idSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)
                                        .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(cbo_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBO_NXB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(rdo_dangBan)
                            .addComponent(rdo_ngungBan)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CBO_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        tbl_sanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SPCT", "Ten SP", "Nha xuat ban", "Tac gia", "NCC", "Ngay them", "Ngay sua", "Nguoi tao", "Nguoi sua", "So luong", "Gia ban", "The loai", "Trang thai"
            }
        ));
        tbl_sanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_sanPhamChiTiet);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
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

    private void CBO_NXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_NXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_NXBActionPerformed

    private void tbl_sanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamChiTietMouseClicked
        // TODO add your handling code here:
        index = tbl_sanPhamChiTiet.getSelectedRow();
        this.showForm(index);
    }//GEN-LAST:event_tbl_sanPhamChiTietMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBO_NXB;
    private javax.swing.JComboBox<String> CBO_TacGia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_Ncc;
    private javax.swing.JComboBox<String> cbo_theLoai;
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton rdo_dangBan;
    private javax.swing.JRadioButton rdo_ngungBan;
    private javax.swing.JTable tbl_sanPhamChiTiet;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_idSP;
    private javax.swing.JTextField txt_id_spct;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_nguoiSua;
    private javax.swing.JTextField txt_nguoiTao;
    private javax.swing.JTextField txt_soLong;
    private javax.swing.JTextField txt_tenSP;
    // End of variables declaration//GEN-END:variables
}
