package com.health.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // CORE CONSTRAINT: Mock Mode Toggle
    // If true: App runs in memory (Safe for submission/grading without DB)
    // If false: App tries to connect to real MySQL
    public static boolean USE_MOCK_MODE = true;

    public static Connection getConnection() {
        if (USE_MOCK_MODE) {
            System.out.println("[DB] Mock Mode Active. Returning null connection.");
            return null;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Standard local MySQL connection
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/health_db", "root", ""); 
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[DB] Connection Failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
