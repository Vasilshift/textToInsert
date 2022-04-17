package testing;

import com.textinsert.textToInsert.Application;
//import com.textinsert.textToInsert.WorkWithText;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestDownloadFromDB {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Application.class, args);

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT nameFile, strToFind, coorX, coorY, textForInsert, id FROM COORDINATES WHERE id IN (1, 3, 4)";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Connected database successfully...");

            while(rs.next()) {
                String nameFile = rs.getString("nameFile");
                String strToFind = rs.getString("strToFind");
                Float coorX = rs.getFloat("coorX");
                float coorY = rs.getFloat("coorY");
                String textForInsert = rs.getString("textForInsert");
                int id  = rs.getInt("id");

                System.out.print("nameFile: " + nameFile);
                System.out.print(", str: " + strToFind);
                System.out.print(", coorX: " + coorX);
                System.out.print(", coorY: " + coorY);
                System.out.print(", textForInsert: " + textForInsert);
                System.out.println(", id (place): " + id);

//                mapXandY.put(coorY, coorY);
//
//
//                Map<Integer, Map<Float, Float>> kvMap = new HashMap<>();
//                kvMap.put(id,mapXandY);



                //WorkWithText.insertTextToDocument(nameFile, coorX, coorY, textForInsert);

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
}
