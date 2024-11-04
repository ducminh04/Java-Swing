/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import java.sql.SQLException;
import responsitory.DBConnect;

/**
 *
 * @author HP
 */
public class DanhMuc_service {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<DanhMuc> getAllDM() {
        sql = "select* from DanhMuc";
        List<DanhMuc> dm = new ArrayList<>();
        try {
            con = DBConnect.getConnectDAO();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc(rs.getInt(1), rs.getString(2));
                dm.add(danhMuc);
            }
            return dm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int AddDM(DanhMuc danhMuc) {
        String sql = "insert into DanhMuc(tenDanhmuc) values(?)";
        try (Connection connection = DBConnect.getConnectDAO();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, danhMuc.getTenDanhMuc());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
