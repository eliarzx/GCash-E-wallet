package System;

import HelperClasses.RoundedBorder;
import HelperClasses.RoundedWhite;
import HelperClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class ExpressSend extends JFrame {

    private JPanel TitleBG;
    private JLabel Title;
    private JLabel PhoneNumber_Label;
    private JTextField PhoneNumber_Field;
    private JLabel jLabel1;
    private JLabel BalanceAmount;
    private JTextField Amount_Field;
    private JLabel AmountLabel;
    private JLabel PhpLabel;
    private JButton Confirm_Button2;

    private Connection conn;
    private String loggedInPhoneNumber; // Example phone number for logged-in user
    private String phoneNumber;
    private double currentBalance = 0.0;

    public ExpressSend() throws ClassNotFoundException {
        // Frame Settings
        setTitle("Easy Transfer, Fast Transaction!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Navigation Bar
        NavigationBar_Inside navbar = new NavigationBar_Inside();
        navbar.setBackground(Color.WHITE);
        navbar.setVisible(true);

        getContentPane().add(navbar);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

        // Set initial size
        navbar.setBounds(0, 0, 1920, 100);

        // Adjust navbar size when window resizes
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setBounds(0, 0, getWidth(), 100);
            }
        });
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        TitleBG = new JPanel();
        TitleBG.setBackground(new Color(52, 120, 242));
        TitleBG.setLayout(null);
        TitleBG.setBounds(0, 100, 1920, 110); // Adjusted because of Navbar above
        add(TitleBG);

        Title = new JLabel("Express Send", SwingConstants.CENTER);
        Title.setFont(new Font("Gotham Rounded", Font.BOLD, 50));
        Title.setForeground(Color.WHITE);
        Title.setBounds(560, 0, 400, 110);
        TitleBG.add(Title);

         // PhoneNumber_Label
        PhoneNumber_Label = new JLabel("Send to");
        PhoneNumber_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        PhoneNumber_Label.setForeground(new Color(0, 28, 128));
        PhoneNumber_Label.setBounds(367, 244, 634, 45);
        getContentPane().add(PhoneNumber_Label);

        // +63 Label
        jLabel1 = new JLabel("PH", SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setBackground(new Color(128, 161, 219));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        jLabel1.setBorder(BorderFactory.createLineBorder(new Color(128, 161, 219), 10));
        jLabel1.setBounds(367, 298, 122, 75);
        getContentPane().add(jLabel1);

        // Phone Number Field
        PhoneNumber_Field = new JTextField("Enter a valid phone number");
        PhoneNumber_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        PhoneNumber_Field.setForeground(new Color(217, 217, 217));
        PhoneNumber_Field.setBorder(new RoundedBorder(20));
        PhoneNumber_Field.setBounds(495, 298, 629, 75);
        getContentPane().add(PhoneNumber_Field);

        PhoneNumber_Field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (PhoneNumber_Field.getText().trim().equals("Enter a valid phone number")) {
                    PhoneNumber_Field.setText("");
                    PhoneNumber_Field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (PhoneNumber_Field.getText().trim().isEmpty()) {
                    PhoneNumber_Field.setText("Enter a valid phone number");
                    PhoneNumber_Field.setForeground(new Color(217, 217, 217));
                }
            }
        });

        // Amount Label
        AmountLabel = new JLabel("Amount ");
        AmountLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        AmountLabel.setForeground(new Color(0, 28, 128));
        AmountLabel.setBounds(367, 418, 738, 36);
        getContentPane().add(AmountLabel);

        // Php Label
        PhpLabel = new JLabel("Php");
        PhpLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 36));
        PhpLabel.setForeground(new Color(0, 28, 128));
        PhpLabel.setBounds(367, 460, 80, 77);
        getContentPane().add(PhpLabel);

        // Amount Field
        Amount_Field = new JTextField("0.00");
        Amount_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        Amount_Field.setForeground(new Color(217, 217, 217));
        Amount_Field.setBorder(new RoundedBorder(20));
        Amount_Field.setBounds(465, 460, 669, 77);
        getContentPane().add(Amount_Field);

        Amount_Field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Amount_Field.getText().trim().equals("0.00")) {
                    Amount_Field.setText("");
                    Amount_Field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Amount_Field.getText().trim().isEmpty()) {
                    Amount_Field.setText("0.00");
                    Amount_Field.setForeground(new Color(217, 217, 217));
                }
            }
        });

        // Balance Amount Label
        BalanceAmount = new JLabel("Available Balance: PHP // + amount");
        BalanceAmount.setFont(new Font("Calibri Light", Font.PLAIN, 27));
        BalanceAmount.setForeground(new Color(0, 28, 128));
        BalanceAmount.setBounds(367, 555, 484, 30);
        getContentPane().add(BalanceAmount);

        // Confirm Button
        Confirm_Button2 = new JButton("Confirm");
        Confirm_Button2.setFont(new Font("Gotham Rounded", Font.PLAIN, 33));
        Confirm_Button2.setForeground(Color.WHITE);
        Confirm_Button2.setBackground(new Color(0, 43, 184));
        Confirm_Button2.setBorder(new RoundedWhite(50));
        Confirm_Button2.setFocusPainted(false);
        Confirm_Button2.setBounds(621, 626, 315, 67);
        Confirm_Button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(Confirm_Button2);

        Confirm_Button2.addActionListener(e -> {
            String recipient = PhoneNumber_Field.getText().trim();
            String amountText = Amount_Field.getText().trim();

            if (recipient.equals("") || recipient.equals("Enter a valid phone number") || amountText.equals("") || amountText.equals("0.00")) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Please fill out all fields properly.", 18, "Okay", null);
                return;
            }

            // Check if trying to send to own number
            if (recipient.equals(loggedInPhoneNumber)) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "You input your own number. Please check and try again.", 18, "Okay", null);
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountText);
                if (amount <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Invalid amount entered.", 18, "Okay", null);
                return;
            }

            // Check min and max transaction limits
            if (amount < 10) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Minimum transaction amount is PHP 10.", 18, "Okay", null);
                return;
            }

            if (amount > 50000) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Maximum transaction amount is PHP 50,000.", 18, "Okay", null);
                return;
            }

            if (amount > currentBalance) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Insufficient balance. Reload now!", 18, "Okay", null);
                return;
            }

            try {
                // Check if recipient exists
                PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM Account WHERE PhoneNumber = ?");
                ps.setString(1, recipient);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    GcashDialog dialog = new GcashDialog(this, null, 0, "Recipient account not found.", 18, "Okay", null);
                    return;
                }

                double recipientBalance = rs.getDouble("Balance");

                // Update sender balance
                PreparedStatement updateSender = conn.prepareStatement("UPDATE ACCOUNT SET Balance = Balance - ? WHERE PhoneNumber = ?");
                updateSender.setDouble(1, amount);
                updateSender.setString(2, loggedInPhoneNumber);
                updateSender.executeUpdate();

                // Update recipient balance
                PreparedStatement updateRecipient = conn.prepareStatement("UPDATE ACCOUNT SET Balance = Balance + ? WHERE PhoneNumber = ?");
                updateRecipient.setDouble(1, amount);
                updateRecipient.setString(2, recipient);
                updateRecipient.executeUpdate();
                
                // Get logged in user's AccountID
    int accountID = Session.getInstance().getAccountID();

    try {
        // Insert into Express_Send table
        PreparedStatement insertExpressSend = conn.prepareStatement(
            "INSERT INTO Express_Send (AccountID, SendAmount, ReceiverPhoneNumber) VALUES (?, ?, ?)"
        );
        insertExpressSend.setInt(1, accountID);
        insertExpressSend.setDouble(2, amount);
        insertExpressSend.setString(3, recipient);
        insertExpressSend.executeUpdate();

        // Insert into Account_Transaction table
        PreparedStatement insertTransaction = conn.prepareStatement(
            "INSERT INTO Account_Transaction (AccountID, Transact_Type, TransactionAmount) VALUES (?, ?, ?)"
        );
        insertTransaction.setInt(1, accountID);
        insertTransaction.setString(2, "Express Send");
        insertTransaction.setDouble(3, amount);
        insertTransaction.executeUpdate();

        // Show success dialog
        GcashDialog dialog = new GcashDialog(this, null, 0,
            "<html><div style='text-align: center;'>Your funds have been transferred <br>successfully. Please wait while the <br>transaction is being reflected.</div></html>",
            18, "Confirm", null);

    } catch (SQLException ex) {
    GcashDialog dialog = new GcashDialog(this, null, 0,
        "Failed to record transaction: " + ex.getMessage(), 16, "Okay", null);
      }
                // Update UI
                currentBalance -= amount;
                BalanceAmount.setText("Available Balance: PHP " + currentBalance);

            } catch (Exception ex) {
                GcashDialog dialog = new GcashDialog(this, null, 0, "Transaction failed: " + ex.getMessage(), 16, "Okay", null);
            }
        });

        connectToDatabase();
        loadCurrentBalance();

        setVisible(true);
    }

    private void connectToDatabase() throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true"
            );

            int accountID = Session.getInstance().getAccountID();
            PreparedStatement ps = conn.prepareStatement("SELECT Balance, PhoneNumber FROM ACCOUNT WHERE AccountID = ?");
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                phoneNumber = rs.getString("PhoneNumber");
                currentBalance = rs.getDouble("Balance");
                loggedInPhoneNumber = phoneNumber;
                BalanceAmount.setText("Available Balance: PHP " + currentBalance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            GcashDialog dialog = new GcashDialog(this, null, 0, "Database connection failed: " + e.getMessage(), 16, "Okay", null);
        }
    }

    
    private void loadCurrentBalance() {
        try {
            // Use phone number instead of AccountID, if needed
            PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM ACCOUNT WHERE PhoneNumber = ?");
            ps.setString(1, loggedInPhoneNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                currentBalance = rs.getDouble("Balance");
                BalanceAmount.setText("Available Balance: PHP " + currentBalance);
            }
        } catch (Exception ex) {
            GcashDialog dialog = new GcashDialog(this, null, 0, "Failed to retrieve balance: " + ex.getMessage(), 16, "Okay", null);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ExpressSend();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                GcashDialog dialog = new GcashDialog(null, null, 0, "Failed to start Express Send: " + e.getMessage(), 16, "Okay", null);
            }
        });
    }
   
}

