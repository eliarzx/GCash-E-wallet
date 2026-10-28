package HelperClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        // Connection details
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;";


        // Try establishing the connection
        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                System.out.println("Connected to the database successfully.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace
        }
    }
}
