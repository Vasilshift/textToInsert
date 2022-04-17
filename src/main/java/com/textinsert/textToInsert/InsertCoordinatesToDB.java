package com.textinsert.textToInsert;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;

//@Service
public class InsertCoordinatesToDB {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";


    public static void createTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sqlDropTable = "DROP table if exists COORDINATES";
            stmt.execute(sqlDropTable);

            String sqlCreateTable = "CREATE TABLE COORDINATES" +
                    " (" +
                    "nameFile VARCHAR(255)," +
                    "strToFind VARCHAR(255), " +
                    "coorX FLOAT," +
                    "coorY FLOAT," +
                    "textForInsert VARCHAR(255)," +
                    "id INT" +
                    ")";
            stmt.executeUpdate(sqlCreateTable);

            System.out.println("Table is created ");

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    public static void InsertValueToTable(String nameOfFile, String textAll, float xAll, float yAll,  String textToInsert) {
        Connection conn = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "insert into COORDINATES values (?, ?, ?, ?, ?, ?)";
            PreparedStatement myStmt = conn.prepareStatement(query);

            myStmt.setString(1, nameOfFile);
            myStmt.setString(2, textAll);
            myStmt.setFloat(3, xAll);
            myStmt.setFloat(4, yAll);
            myStmt.setString(5, textToInsert);
            myStmt.setInt(6, 1);

            myStmt.executeUpdate();
            myStmt.close();
            conn.close();

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt1!=null) stmt1.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void InsertValueToTableNext(String nameOfFile, String textAll, float xAll, float yAll,  String textToInsert) {
        Connection conn = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();

            String findId = "select id from COORDINATES";
            ResultSet resultSet = stmt1.executeQuery(findId);

            System.out.println("resultSet.next()) " + resultSet.next());  // to need call 1 time this method !!!!!!!

            String query = "insert into COORDINATES values (?, ?, ?, ?, ?, ?)";
            PreparedStatement myStmt = conn.prepareStatement(query);

            long idC = resultSet.getLong("id") + 1;
            while (resultSet.next()) {
                idC++;
            }

            myStmt.setString(1, nameOfFile);
            myStmt.setString(2, textAll);
            myStmt.setFloat(3, xAll);
            myStmt.setFloat(4, yAll);
            myStmt.setString(5, textToInsert);
            myStmt.setLong(6, idC);

            myStmt.executeUpdate();
            myStmt.close();
            conn.close();

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt1!=null) stmt1.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }

    }


}
