
package System;

import System.NavigationBar_Inside;
import HelperClasses.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CashIn extends JFrame {

    private JTextField AmountField;
    private JButton ConfirmButton;
    private JLabel Heading, Subheading, Text, Text1, Title;
    private JPanel TitleBG, jPanel1;

    public CashIn() {
        setTitle("Refill GCash Wallet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null); 
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

        Title = new JLabel("Cash In", SwingConstants.CENTER);
        Title.setFont(new Font("Gotham Rounded", Font.BOLD, 50));
        Title.setForeground(Color.WHITE);
        Title.setBounds(560, 0, 400, 110);
        TitleBG.add(Title);

        // Heading
        Heading = new JLabel("Refill your e-wallet easily!");
        Heading.setFont(new Font("Gotham Rounded", Font.BOLD, 38));
        Heading.setForeground(new Color(0, 43, 184));
        Heading.setBounds(150, 250, 800, 50);
        add(Heading);

        // Subheading
        Subheading = new JLabel("<html>Keep the good times rolling—<br>cash in anytime.</html>");
        Subheading.setFont(new Font("Gotham Rounded", Font.BOLD, 28));
        Subheading.setForeground(new Color(128, 161, 219));
        Subheading.setBounds(150, 310, 800, 100);
        add(Subheading);

        // Text1 - "Enter amount"
        Text1 = new JLabel("Enter amount", SwingConstants.CENTER);
        Text1.setFont(new Font("Gotham Rounded", Font.BOLD, 32));
        Text1.setBounds(540, 450, 400, 50);
        add(Text1);

        // PHP Label and AmountField
        Text = new JLabel("PHP", SwingConstants.CENTER);
        Text.setFont(new Font("Gotham Rounded", Font.BOLD, 32));
        Text.setBounds(560, 520, 100, 40);
        add(Text);

        AmountField = new JTextField("0.00");
        AmountField.setFont(new Font("Gotham Rounded", Font.PLAIN, 27));
        AmountField.setForeground(new Color(180, 181, 184));
        AmountField.setHorizontalAlignment(SwingConstants.RIGHT);
        AmountField.setBorder(null);
        AmountField.setCaretColor(new Color(180, 181, 184));
        AmountField.setBounds(705, 520, 200, 40);
        add(AmountField);

        // Underline panel
        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.BLACK);
        jPanel1.setBounds(550, 570, 420, 3);
        add(jPanel1);

        // Confirm Button
        ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(0, 43, 184));
        ConfirmButton.setForeground(Color.WHITE);
        ConfirmButton.setFont(new Font("Gotham Rounded", Font.PLAIN, 32));
        ConfirmButton.setBounds(580, 630, 339, 67);
        ConfirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(ConfirmButton);

        // Action Listeners
        AmountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (AmountField.getText().trim().equals("0.00")) {
                    AmountField.setText("");
                    AmountField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (AmountField.getText().trim().isEmpty()) {
                    AmountField.setText("0.00");
                    AmountField.setForeground(new Color(217, 217, 217));
                }
            }
        });

        ConfirmButton.addMouseListener(new MouseAdapter() {
            Color originalBackground = ConfirmButton.getBackground();
            Color originalForeground = ConfirmButton.getForeground();
            Color hoverBackground = new Color(0x80, 0xA1, 0xDB);
            Color hoverForeground = new Color(52, 120, 242);

            @Override
            public void mouseEntered(MouseEvent e) {
                ConfirmButton.setBackground(hoverBackground);
                ConfirmButton.setForeground(hoverForeground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ConfirmButton.setBackground(originalBackground);
                ConfirmButton.setForeground(originalForeground);
            }
        });
        
        ConfirmButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountText = AmountField.getText().trim();
            if (amountText.isEmpty() || !amountText.matches("\\d+(\\.\\d{1,2})?")) {
                GcashDialog dialog = new GcashDialog(null, null, 0, "Please enter a valid amount.", 18, "Okay", null);
                return;
            }

double amount = Double.parseDouble(amountText);

if (amount > 50000) {
    GcashDialog dialog = new GcashDialog(null, null, 0, "Transaction limit exceeded. Maximum cash-in is PHP 50,000.", 18, "Okay", null);
    return;
}

int accountId = Session.getInstance().getAccountID();

try (Connection conn = DriverManager.getConnection(
        "jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true", 
        "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD")) {

    conn.setAutoCommit(false); // Start transaction

    // Get current balance
    PreparedStatement getBalance = conn.prepareStatement(
        "SELECT Balance FROM Account WHERE AccountID = ?");
    getBalance.setInt(1, accountId);
    ResultSet rs = getBalance.executeQuery();

    double currentBalance = 0;
    if (rs.next()) {
        currentBalance = rs.getDouble("Balance");
    }

    if (currentBalance + amount > 999999) {
        GcashDialog dialog = new GcashDialog(null, null, 0, "Balance limit exceeded. Maximum allowed is PHP 999,999.", 18, "Okay", null);
        conn.rollback();
        return;
    }

    //Update Balance
    PreparedStatement updateBalance = conn.prepareStatement(
        "UPDATE Account SET Balance = Balance + ? WHERE AccountID = ?");
    updateBalance.setDouble(1, amount);
    updateBalance.setInt(2, accountId);
    updateBalance.executeUpdate();

    //Insert into Cash_In
    PreparedStatement insertCashIn = conn.prepareStatement(
        "INSERT INTO Cash_In (AccountID, CashInAmount) VALUES (?, ?)");
    insertCashIn.setInt(1, accountId);
    insertCashIn.setDouble(2, amount);
    insertCashIn.executeUpdate();

    //Insert into Account_Transaction
    PreparedStatement insertTransaction = conn.prepareStatement(
        "INSERT INTO Account_Transaction (AccountID, Transact_Type, TransactionAmount) VALUES (?, 'Cash In', ?)");
    insertTransaction.setInt(1, accountId);
    insertTransaction.setDouble(2, amount);
    insertTransaction.executeUpdate();

    conn.commit(); // Commit transaction

    GcashDialog dialog = new GcashDialog (null, "Cash In Successful!", 24, "<html><div style='text-align: center; width: 100%'>Your funds have been <br> added to your wallet.</div></html>", 20, "Confirm", null);	

    AmountField.setText("0.00");
    AmountField.setForeground(new Color(180, 181, 184));

} catch (SQLException ex) {
    ex.printStackTrace();
    GcashDialog dialog = new GcashDialog(null, null, 0, "Database error: " + ex.getMessage(), 17, "Okay", null);
}

        }
    });

    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CashIn cashIn = new CashIn();
            cashIn.setVisible(true);
        });
    }
}
