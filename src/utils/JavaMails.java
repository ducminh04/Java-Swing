/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.mail.Authenticator;
/**

/**
 *
 * @author HP
 */
public class JavaMails {
     public static String generateRandomString(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void sendmail(String email, String mess, String subj) {
        // thông tin tài khoản người gửi (ender)
       
        String senderEmail = "hoangnxph36271@fpt.edu.vn";
        String senderPassword = "wpxt vklt yfwc epcf";

        // Người nhận (Receiver)
        String toEmail = email;
        //String ccEmail = "abc@fpt.edu.vn, xyz@fpt.edu.vn";
        String subject = subj;
        String message = mess;
        
        // Cấu hình các thuộc tính để kết nối với máy chủ email
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);

        
         // Xác thực tài khoản email người gửi
        Session session = Session.getInstance(p, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        // Tạo đối tượng MimeMessage để gửi email
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, true));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
        } catch (Exception ex) {
            System.out.println(ex);
        }
         // Gửi email
        
    }
}
