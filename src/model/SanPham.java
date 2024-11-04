    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package model;
    import java.util.Date;
    
    public class SanPham {
        private int id_SP;
        private int id_danhMuc;
        private String tenSP;
        private String ngayTao;
        private String ngaySua;
        private String nguoiTao;
        private String nguoiSua;
        private String tenDM;
        
    public SanPham() {
        
    }

    public SanPham(int id_SP, int id_danhMuc, String tenSP, String ngayTao, String ngaySua, String nguoiTao, String nguoiSua, String tenDM) {
        this.id_SP = id_SP;
        this.id_danhMuc = id_danhMuc;
        this.tenSP = tenSP;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.tenDM = tenDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public int getId_SP() {
        return id_SP;
    }

    public void setId_SP(int id_SP) {
        this.id_SP = id_SP;
    }

    public int getId_danhMuc() {
        return id_danhMuc;
    }

    public void setId_danhMuc(int id_danhMuc) {
        this.id_danhMuc = id_danhMuc;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    @Override
    public String toString() {
        return "SanPham{" + "id_SP=" + id_SP + ", id_danhMuc=" + id_danhMuc + ", tenSP=" + tenSP + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", nguoiTao=" + nguoiTao + ", nguoiSua=" + nguoiSua + ", tenDM=" + tenDM + '}';
    }

  

        

     






    }
