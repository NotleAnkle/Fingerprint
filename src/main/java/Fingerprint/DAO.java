package Fingerprint;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con;

    public DAO() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/finger?autoReconnect=true&useSSL=false";
//            String dbUrl = "jdbc:mysql://localhost:3306/finger";
            String dbClass = "com.mysql.cj.jdbc.Driver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "12345678");
                System.out.println("database connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
