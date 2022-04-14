package com.textinsert.textToInsert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@Component
public class InsertText implements Runnable {

    public static Float ttx;
    public static Float tty;

    public static void setTtx(Float ttx) {
        InsertText.ttx = ttx;
    }

    public static void setTty(Float tty) {
        InsertText.tty = tty;
    }


    public void run() {

        try {

            InsertText.insertText(ttx, tty);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertText(Float ttx, Float tty) throws IOException, SQLException {

//        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
//        Statement stmt = conn.createStatement();
//        String sql = "select coorX from COORDINATES";
//        ResultSet rs = stmt.executeQuery(sql);
        //System.out.println("rs =  " + rs);
//        while(rs.next()) {
//            float ttx = rs.getFloat("coorX");
//            float tty = rs.getFloat("coorY");
//            System.out.println("x and y from while " + ttx + " " + tty);
//        }
//        rs.close();

        File file = new File("C:/updatepdf/исходники/исходник1.pdf");
        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        //System.out.println("x and y from insertText method " + InsertText.ttx + " " + InsertText.tty);

        WorkWithDB.readFromDB();

        contentStream.newLineAtOffset(ttx, tty);

        String text = "blablabla---===";

        contentStream.showText(text);
        contentStream.endText();

        System.out.println("Content added");

        contentStream.close();
        document.save("C:/updatepdf/исходники/исходник1.pdf");

        document.close();
    }



}
