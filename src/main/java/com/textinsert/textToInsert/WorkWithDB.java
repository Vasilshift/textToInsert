package com.textinsert.textToInsert;

import java.sql.*;

public class WorkWithDB {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";

    public static void connectToDB(float xDirAdj, float yEndAdj, String textToFind) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();

            String sqlCreateTable = "DROP TABLE COORDINATES; CREATE TABLE COORDINATES" +
                    "(id INTEGER not NULL," +
                    "strToFind VARCHAR(255), " +
                    "coorX FLOAT," +
                    "coorY FLOAT," +
                    "PRIMARY KEY ( id ))";
            stmt1.executeUpdate(sqlCreateTable);

            System.out.println("table is created ");

            String query = "insert into COORDINATES values (1, ?, ?, ?)";
            PreparedStatement myStmt = conn.prepareStatement(query);
            myStmt.setString(1, textToFind);
            myStmt.setFloat(2, xDirAdj);
            myStmt.setFloat(3, yEndAdj);

            myStmt.executeUpdate();
            stmt1.close();
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

    public static void readFromDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT id, strToFind, coorX, coorY FROM COORDINATES";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                int id  = rs.getInt("id");
                String age = rs.getString("strToFind");
                float first = rs.getFloat("coorX");
                float last = rs.getFloat("coorY");

                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);

                InsertText.insertText(first, last);

            }
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
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
        System.out.println("Goodbye!");
    }

}
