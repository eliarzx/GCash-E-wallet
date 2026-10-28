package HelperClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "YOUR_DB_USERNAME"; // Your SQL Server login username
    private static final String PASSWORD = "YOUR_DB_PASSWORD"; // Your SQL Server login password

    public static Connection connectToDatabase() {
    Connection connection = null;
    try {
        // Try connecting to the database
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        if (connection != null) {
            System.out.println("Connected to the database successfully.");
        } else {
            System.out.println("Connection object is null.");
        }
    } catch (SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        e.printStackTrace();
    }
    return connection;
}
}

