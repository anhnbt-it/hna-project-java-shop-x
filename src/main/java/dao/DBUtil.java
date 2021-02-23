package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=shopx_db;user=anhnbt;password=KhoaiTay@2019";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("Connecting to SQL Server ...");
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Could not connect: " + e.getMessage());
        }
        return conn;
    }

}
