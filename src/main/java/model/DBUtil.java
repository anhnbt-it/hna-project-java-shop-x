package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL      = "jdbc:mysql://localhost:3306/shopx_db";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "KhoaiTay@2019";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to database.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Could not connect: " + e.getMessage());
        }
        return conn;
    }

}
