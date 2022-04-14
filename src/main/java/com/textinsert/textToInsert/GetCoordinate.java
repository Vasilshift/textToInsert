package com.textinsert.textToInsert;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.util.List;

@Component
public class GetCoordinate extends PDFTextStripper implements Runnable {

//    static final String JDBC_DRIVER = "org.h2.Driver";
//    static final String DB_URL = "jdbc:h2:~/test";
//
//    static final String USER = "sa";
//    static final String PASS = "";

    public static String textToFind = "Уникальный номер записи об аккредитации в реестре аккредитованных лиц ________";

    //private static Logger logger = LogManager.getLogger(GetCoordinate.class);

    public GetCoordinate() throws IOException {
    }

    @Override
    public void run() {
        try {
            File file = new File("C:/updatepdf/исходники/исходник1.pdf");
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new GetCoordinate();
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            document.save("C:/updatepdf/исходники/исходник1.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    @Override
    public void writeString(String string, List<TextPosition> textPositions) throws IOException {

        if (string.equalsIgnoreCase(textToFind)) {
            TextPosition textPosition = textPositions.get(string.length() - 7);
            float xDirAdj = textPosition.getXDirAdj();
            float yEndAdj = textPosition.getEndY();

            System.out.println(xDirAdj);
            System.out.println(yEndAdj);

            WorkWithDB.connectToDB(xDirAdj, yEndAdj, textToFind);
            //WorkWithDB.readFromDB();
            InsertText.insertText(xDirAdj, yEndAdj);

        }

    }

//    public void connectToDB(float xDirAdj, float yEndAdj) throws SQLException, ClassNotFoundException {
//        Class.forName(JDBC_DRIVER);
//        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//        Statement stmt1 = conn.createStatement();
//
//            String sql1 = "DROP TABLE COORDINATES; CREATE TABLE COORDINATES" +
//                    "(id INTEGER not NULL," +
//                    "strToFind VARCHAR(255), " +
//                    "coorX INT," +
//                    "coorY INT," +
//                    "PRIMARY KEY ( id ))";
//            stmt1.executeUpdate(sql1);
//
//        System.out.println("table is created ");
//
//        PreparedStatement stmt = conn.prepareStatement("insert into COORDINATES values(1,?,?,?)");
//            //stmt.setInt(1, Integer.parseInt("id"));
//            stmt.setString(1, textToFind);
//            stmt.setInt(2, 300);
//            stmt.setInt(3, 400);
//
//        System.out.println("insert into COORDINATES values(?,?,?,?) is did ");
//
//        PreparedStatement stmt2 = conn.prepareStatement("select coorX from COORDINATES");
//        ResultSet rs = stmt2.executeQuery();
//        //System.out.println(rs.getInt("coorX"));
////            while(rs.next()){
////                System.out.println(rs.getFloat("coorX"));
////            }
//
//
//        stmt.close();
//        conn.close();
//    }

//    public List<Float> getListCordinetes() throws IOException {
//        return listCordinetes;
//    }

//    public void fooq() throws IOException {
//        System.out.println("tListCordinetes() from ... " + getListCordinetes());
//    }



//    public void foo(Float xDirAdj, Float yEndAdj) throws IOException {
//        InsertText.insertText(xDirAdj, yEndAdj);
//    }



}

