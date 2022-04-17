//package com.textinsert.textToInsert;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class WorkWithText implements Runnable {
//
//    static final String JDBC_DRIVER = "org.h2.Driver";
//    static final String DB_URL = "jdbc:h2:~/test";
//    static final String USER = "sa";
//    static final String PASS = "";
//
//    public static Map<Float, Float> mapXandY = new HashMap<>();
//
//
//    private static String stringToCreated;
//
//    @Autowired
//    public WorkWithText(String stringToCreated) {
//        this.stringToCreated = stringToCreated;
//    }
//
////    WorkWithText() {
////
////    }
//
//
//
//    public void run() {
//        try {
////            for(int i=1; i <= 3; i++) {
////                System.out.println("WorkWithText from method RUN() ");
////                try {
////                    Thread.sleep(2000);
////                } catch(InterruptedException e) {
////                    System.out.println("An Exception occured in Thread: " + e);
////                }
////            }
//            Thread.sleep(5000);
//            WorkWithText.getDataFromDBAnd_InsertToDB();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void getDataFromDBAnd_InsertToDB() {
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            Class.forName(JDBC_DRIVER);
//
//            System.out.println("Connecting to database...");
//
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stmt = conn.createStatement();
//
//            String sql = "SELECT nameFile, strToFind, coorX, coorY, textForInsert, id FROM COORDINATES WHERE id IN (1, 3, 4)";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            System.out.println("Connected database from getDataFromDBAnd_InsertToDB successfully...");
//
//            while(rs.next()) {
//                String nameFile = rs.getString("nameFile");
//                String strToFind = rs.getString("strToFind");
//                Float coorX = rs.getFloat("coorX");
//                float coorY = rs.getFloat("coorY");
//                String textForInsert = rs.getString("textForInsert");
//                int id  = rs.getInt("id");
//
//                System.out.print("nameFile: " + nameFile);
//                System.out.print(", str: " + strToFind);
//                System.out.print(", coorX: " + coorX);
//                System.out.print(", coorY: " + coorY);
//                System.out.print(", textForInsert: " + textForInsert);
//                System.out.println(", id (place): " + id);
//
//                mapXandY.put(coorY, coorY);
//
//
//                Map<Integer, Map<Float, Float>> kvMap = new HashMap<>();
//                kvMap.put(id,mapXandY);
//
//
//
//                WorkWithText.insertTextToDocument(nameFile, coorX, coorY, textForInsert);
//
//            }
//            rs.close();
//        } catch(SQLException se) {
//            se.printStackTrace();
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            // finally block used to close resources
//            try {
//                if(stmt!=null) stmt.close();
//            } catch(SQLException se2) {
//            } // nothing we can do
//            try {
//                if(conn!=null) conn.close();
//            } catch(SQLException se) {
//                se.printStackTrace();
//            } // end finally try
//        } // end try
//        System.out.println("Finish!");
//    }
//
//    public static void insertTextToDocument(String nameFile, Float ttx, Float tty, String textInsert) throws IOException, SQLException {
//
//        //System.out.println("ttx from insertText " + ttx + "  tty " + tty);
//
//        //String stringCollect = CollectStrings.createStringFilename(stringToCreated);
//
//
//        File file = new File(stringToCreated);
//        PDDocument document = PDDocument.load(file);
//        PDPage page = document.getPage(0);
//        PDPageContentStream contentStream = new PDPageContentStream(document, page,
//                PDPageContentStream.AppendMode.APPEND, true, true);
//
//        contentStream.beginText();
//        PDType1Font font = PDType1Font.HELVETICA;
//        System.out.println("font : " + font.getEncoding().getName('\u0413'));
//
//        contentStream.setFont(font, 12);
//        contentStream.newLineAtOffset(ttx, tty);
//        String text = textInsert;
//
//
//
//        //text = text.replace("\n", "").replace("\r", "");
//        contentStream.showText(text);
//        contentStream.endText();
//
//        System.out.println("Content added");
//
//        contentStream.close();
//        document.save(stringToCreated);
//
//        document.close();
//    }
//
//
//
//}
