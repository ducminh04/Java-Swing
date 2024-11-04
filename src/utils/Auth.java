/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;
import model.NhanVien;

/**
 *
 * @author HP
 */
public class Auth {
    
    public static NhanVien user = null ;
    public static String otp = null;

    public static void clear() {
        Auth.user = null;
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void RandomOtp() {
        otp = getRandomNumber(6);
    }

    public static void clearOtp() {
        otp = null;
    }

    public static String getotp() {
        return otp;
    }

    public static boolean isCheck() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return Auth.isCheck() && user.isVaiTro();
    }
    
     public static boolean isStatus() {
        return  user.isTrangThai();
    }
}
