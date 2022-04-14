package com.textinsert.textToInsert;

import java.sql.*;

public class WorkWithDB {

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
                    "id INTEGER not NULL," +
                    "strToFind VARCHAR(255), " +
                    "coorX FLOAT," +
                    "coorY FLOAT," +
                    "textForInsert VARCHAR(255)" +
                    //"PRIMARY KEY ( id )" +
                    ")";
            stmt.executeUpdate(sqlCreateTable);

            System.out.println("table is created ");

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


    public static void InsertValueToTable(String textAll, float xAll, float yAll,  String textToInsert) {
        Connection conn = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();

            String query = "insert into COORDINATES values (?, ?, ?, ?, ?)";
            PreparedStatement myStmt = conn.prepareStatement(query);
            // todo: resolve id of place
            //myStmt.setInt(1, );
            myStmt.setString(1, textAll);
            myStmt.setFloat(2, xAll);
            myStmt.setFloat(3, yAll);
            myStmt.setString(4, textToInsert);
            myStmt.executeUpdate();
            myStmt.close();
            conn.close();

            System.out.println("coordinates are inserted!");

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
