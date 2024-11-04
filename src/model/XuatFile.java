/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author HP
 */
public class XuatFile {
     public static void main(String[] args) {
        
        printPdf("Hello, World!", "output.pdf");
    }

    public static void printPdf(String content, String outputPath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            document.add(new Paragraph(content));
            document.close();
            System.out.println("Tệp PDF đã được tạo thành công tại " + outputPath);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
