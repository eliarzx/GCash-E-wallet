
package AdminSide;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = " ";
    
    // Method to establish a connection to the database
    private static Connection connect() throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return connection;
    }

    // Method to retrieve the total number of accounts
    public static int getTotalAccounts() {
        int totalAccounts = 0;
        String query = "SELECT COUNT(*) FROM AccountID"; 
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                totalAccounts = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAccounts;
    }

    // Method to retrieve the total number of loans
    public static int getTotalLoans() {
        int totalLoans = 0;
        String query = "SELECT COUNT(*) FROM LoanID"; 
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                totalLoans = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalLoans;
    }

}
