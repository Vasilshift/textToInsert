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

//    public static Float ttx;
//    public static Float tty;
//
//    public static void setTtx(Float ttx) {
//        InsertText.ttx = ttx;
//    }
//
//    public static void setTty(Float tty) {
//        InsertText.tty = tty;
//    }

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";

    public void run() {

        try {
            InsertText.getDataFromDBAnd_InsertToDB();
            //InsertText.insertText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDBAnd_InsertToDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Connected database from readFromDB successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT id, strToFind, coorX, coorY FROM COORDINATES";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                int id  = rs.getInt("id");
                String str = rs.getString("strToFind");
                float coorX = rs.getFloat("coorX");
                float coorY = rs.getFloat("coorY");

                System.out.print("ID: " + id);
                System.out.print(", str: " + str);
                System.out.print(", coorX: " + coorX);
                System.out.println(", coorY: " + coorX);

                InsertText.insertText(coorX, coorY);

            }
            rs.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Finish!");
    }

    public static void insertText(Float ttx, Float tty) throws IOException, SQLException {

        System.out.println("ttx from insertText " + ttx + "  tty " + tty);

        File file = new File("C:/updatepdf/исходники/исходник1.pdf");
        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //WorkWithDB.readFromDB();

        contentStream.newLineAtOffset(ttx, tty);

        String text = "RA.RU.312849";

        contentStream.showText(text);
        contentStream.endText();

        System.out.println("Content added");

        contentStream.close();
        document.save("C:/updatepdf/исходники/исходник1.pdf");

        document.close();
    }



}
