package System;


import HelperClasses.BankSession;
import HelperClasses.RoundedBorder;
import HelperClasses.RoundedWhite;
import HelperClasses.Session;

import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.*;

public class BankTransfer2 extends JFrame {

    // Variables declaration
    private JLabel AccountNameLabel;
    private JTextField AccountName_Field;
    private JLabel AccountNumberLabel;
    private JTextField AccountNumber_Field;
    private JLabel AmountLabel;
    private JTextField Amount_Field;
    private JLabel BalanceAmount;
    private JLabel BankName;
    private JButton Confirm_Button;
    private JLabel Label;
    private JLabel PhpLabel;
    private JLabel Title;
    private JPanel TitleBG;

    public BankTransfer2() {
        // Frame settings
        setTitle("Confirm Details");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

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

        // Title Background
        TitleBG = new JPanel();
        TitleBG.setBackground(new Color(52, 120, 242));
        TitleBG.setLayout(null);
        TitleBG.setBounds(0, 95, getWidth(), 100);
        getContentPane().add(TitleBG);

        // Title Label
        Title = new JLabel("Bank Transfer", SwingConstants.CENTER);
        Title.setFont(new Font("Gotham Rounded", Font.BOLD, 50));
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, getWidth(), 100); // Start at (0, 0) INSIDE the TitleBG
        TitleBG.add(Title);

        // Also handle resizing for TitleBG and Title
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setBounds(0, 0, getWidth(), 100);
                TitleBG.setBounds(0, 95, getWidth(), 100);
                Title.setBounds(0, 0, getWidth(), 100); // Update title size too
            }
        });

        // Bank Name
        String selectedBank = BankSession.getSelectedBank();
        BankName = new JLabel("(Add Bank Name Here!)"); 
        BankName.setText(selectedBank);
        BankName.setFont(new Font("Gotham Rounded", Font.BOLD, 40));
        BankName.setForeground(new Color(0, 43, 184));
        BankName.setBounds(143, 232, 1063, 53);
        getContentPane().add(BankName);

        // Account Name Label
        AccountNameLabel = new JLabel("Account Name");
        AccountNameLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        AccountNameLabel.setForeground(new Color(0, 28, 128));
        AccountNameLabel.setBounds(143, 329, 361, 40);
        getContentPane().add(AccountNameLabel);

        // Account Name Field
        AccountName_Field = new JTextField("Maximum of 50 characters");
        AccountName_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        AccountName_Field.setForeground(new Color(217, 217, 217));
        AccountName_Field.setBorder(new RoundedBorder(20));
        AccountName_Field.setBounds(143, 375, 544, 77);
        getContentPane().add(AccountName_Field);

        // Amount Label
        AmountLabel = new JLabel("Amount (Max of PHP 50,000.00)");
        AmountLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        AmountLabel.setForeground(new Color(0, 28, 128));
        AmountLabel.setBounds(826, 329, 766, 40);
        getContentPane().add(AmountLabel);

        // Php Label
        PhpLabel = new JLabel("Php");
        PhpLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 36));
        PhpLabel.setForeground(new Color(0, 28, 128));
        PhpLabel.setBounds(826, 380, 70, 77);
        getContentPane().add(PhpLabel);

        // Amount Field
        Amount_Field = new JTextField("0.00");
        Amount_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        Amount_Field.setForeground(new Color(217, 217, 217));
        Amount_Field.setBorder(new RoundedBorder(20));
        Amount_Field.setBounds(906, 380, 416, 77);
        getContentPane().add(Amount_Field);

        // Account Number Label
        AccountNumberLabel = new JLabel("Account Number");
        AccountNumberLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        AccountNumberLabel.setForeground(new Color(0, 28, 128));
        AccountNumberLabel.setBounds(143, 476, 361, 40);
        getContentPane().add(AccountNumberLabel);

        // Balance Amount
        BalanceAmount = new JLabel("Available Balance: PHP // + amount");
        
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;", "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD");

            int accountId = Session.getInstance().getAccountID();

            String sql = "SELECT Balance FROM Account WHERE AccountID = ?";
            stmt = conn.prepareStatement(sql); // ✅ should now work
            stmt.setInt(1, accountId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("Balance");
                BalanceAmount.setText("Balance: ₱" + String.format("%.2f", balance));
            } else {
                BalanceAmount.setText("Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            BalanceAmount.setText("Error retrieving balance.");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        
        
        BalanceAmount.setFont(new Font("Calibri Light", Font.PLAIN, 27));  //balance
        BalanceAmount.setForeground(new Color(0, 28, 128));
        BalanceAmount.setBounds(826, 476, 484, 40);
        getContentPane().add(BalanceAmount);

        // Account Number Field
        AccountNumber_Field = new JTextField("12 digits");
        AccountNumber_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        AccountNumber_Field.setForeground(new Color(217, 217, 217));
        AccountNumber_Field.setBorder(new RoundedBorder(20));
        AccountNumber_Field.setBounds(143, 522, 544, 77);
        getContentPane().add(AccountNumber_Field);

        // Confirm Button
        Confirm_Button = new JButton("Confirm");
        Confirm_Button.setFont(new Font("Gotham Rounded", Font.PLAIN, 33));
        Confirm_Button.setForeground(Color.WHITE);
        Confirm_Button.setBackground(new Color(0, 43, 184));
        Confirm_Button.setBorder(new RoundedWhite(50));
        Confirm_Button.setBorderPainted(false);
        Confirm_Button.setFocusPainted(false);
        Confirm_Button.setBounds(619, 649, 315, 67);
        Confirm_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(Confirm_Button);

        // Transfer Fee Label
        Label = new JLabel("This includes a transfer fee of PHP 50.00", SwingConstants.CENTER);
        Label.setFont(new Font("Calibri Light", Font.PLAIN, 27));
        Label.setForeground(new Color(0, 28, 128));
        Label.setBounds(528, 726, 484, 34);
        getContentPane().add(Label);

        // Add listeners
        addFieldListeners();
        addButtonListener();
    }

    private void addFieldListeners() {
        AccountName_Field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (AccountName_Field.getText().trim().equals("Maximum of 50 characters")) {
                    AccountName_Field.setText("");
                    AccountName_Field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent evt) {
                if (AccountName_Field.getText().trim().isEmpty()) {
                    AccountName_Field.setText("Maximum of 50 characters");
                    AccountName_Field.setForeground(new Color(217, 217, 217));
                }
            }
        });

        AccountNumber_Field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (AccountNumber_Field.getText().trim().equals("12 digits")) {
                    AccountNumber_Field.setText("");
                    AccountNumber_Field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent evt) {
                if (AccountNumber_Field.getText().trim().isEmpty()) {
                    AccountNumber_Field.setText("12 digits");
                    AccountNumber_Field.setForeground(new Color(217, 217, 217));
                }
            }
        });

        Amount_Field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (Amount_Field.getText().trim().equals("0.00")) {
                    Amount_Field.setText("");
                    Amount_Field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent evt) {
                if (Amount_Field.getText().trim().isEmpty()) {
                    Amount_Field.setText("0.00");
                    Amount_Field.setForeground(new Color(217, 217, 217));
                }
            }
        });
    }

private void addButtonListener() {
    Confirm_Button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            // Validate input fields
            String accountName = AccountName_Field.getText().trim();
            String accountNumber = AccountNumber_Field.getText().trim();
            String amountStr = Amount_Field.getText().trim();

            // Check if any of the fields are empty
            if (accountName.isEmpty() || accountName.equals("Maximum of 50 characters") ||
                accountNumber.isEmpty() || accountNumber.equals("13 digits") ||
                amountStr.isEmpty() || amountStr.equals("0.00")) {
                GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "Please fill in all fields.", 18, "Okay", null);
                return;
            }

            // Validate amount
            double amount = 0;
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0 || amount > 50000) {
                    GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "Amount must be between PHP 1.00 and PHP 50,000.00", 18, "Okay", null);
                    return;
                }
            } catch (NumberFormatException e) {
                GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "Invalid amount format.", 18, "Okay", null);
                return;
            }

            // Fetch account balance from the Account table
            double balance = 0;
            java.sql.Connection conn = null;
            java.sql.PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;", "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD");
                conn.setAutoCommit(false); // Enable transaction management

                int accountId = Session.getInstance().getAccountID();

                // Get account balance
                String sql = "SELECT Balance FROM Account WHERE AccountID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, accountId);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    balance = rs.getDouble("Balance");
                } else {
                    GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "Account not found.", 18, "Okay", null);
                    return;
                }

                // Check if balance is sufficient for the transfer
                if (balance < amount + 50) {  // Account balance must cover transfer amount + fee
                    GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "<html><div style='text-align: center;'>Insufficient balance for the transfer<br>(including transfer fee).</div><html>", 18, "Okay", null);
                    return;
                }

                // Deduct the transfer amount and the transfer fee from the balance
                double newBalance = balance - (amount + 50); // Deduct transfer amount + PHP 50 fee

                // Insert into Bank_Transfer table
                String bankName = BankSession.getSelectedBank();
                String receiverAccount = accountNumber; // Assuming receiver's account number is entered in the field
                String receiverName = accountName; // Assuming receiver's name is entered in the field

                String transferSql = "INSERT INTO Bank_Transfer (AccountID, BankName, ReceiverAccount, ReceiverAmount, ReceiverName, TransferAmount) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(transferSql);
                stmt.setInt(1, Session.getInstance().getAccountID());
                stmt.setString(2, bankName);
                stmt.setString(3, receiverAccount);
                stmt.setDouble(4, amount);
                stmt.setString(5, receiverName);
                stmt.setDouble(6, amount);
                stmt.executeUpdate();

                // Insert into Account_Transaction table
                String transactionSql = "INSERT INTO Account_Transaction (AccountID, Transact_Type, TransactionAmount) " +
                        "VALUES (?, 'Bank Transfer', ?)";
                stmt = conn.prepareStatement(transactionSql);
                stmt.setInt(1, Session.getInstance().getAccountID());
                stmt.setDouble(2, amount);
                stmt.executeUpdate();

                // Update the balance in Account table
                String updateBalanceSql = "UPDATE Account SET Balance = ? WHERE AccountID = ?";
                stmt = conn.prepareStatement(updateBalanceSql);
                stmt.setDouble(1, newBalance);
                stmt.setInt(2, Session.getInstance().getAccountID());
                stmt.executeUpdate();
                
                conn.commit(); // Commit the transaction if all queries are successful
                GcashDialog dialog = new GcashDialog(null, "Success!", 24, "Transfer successful.", 18, "Confirm", null);
                
                BalanceAmount.setText("Balance: ₱" + String.format("%.2f", newBalance));
            } catch (Exception e) {
                e.printStackTrace();
                try { if (conn != null) conn.rollback(); } catch (Exception rollbackEx) {}
                GcashDialog dialog = new GcashDialog(null, "ERROR!", 24, "Error during transfer.", 18, "Okay", null);
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) {}
                try { if (stmt != null) stmt.close(); } catch (Exception e) {}
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }
        }
    });
}



    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new BankTransfer2().setVisible(true);
        });
    }
}

