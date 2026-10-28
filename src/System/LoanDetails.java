package System;

import HelperClasses.RoundedWhite;
import HelperClasses.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.*;

public class LoanDetails extends JFrame {

    private JLabel AmountofSlider_Label;
    private JLabel Heading, Heading1, Heading2, Heading3, Heading4, Heading5, Heading6, Heading7;
    private JSlider LoanAmountSlider;
    private JCheckBox NineMonths_CheckBox, TwelveMonths_CheckBox;
    private JPanel PanelBG, TitleBG;
    private JButton Proceed_Button;
    private JLabel Title;
    private ButtonGroup buttonGroup1;

    public LoanDetails() {
        super("Complete your GLoan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        SwingUtilities.invokeLater(() -> {
            getContentPane().setBackground(new Color(0xCCD8ED));
        });

        // Navigation Bar
        NavigationBar_Inside navbar = new NavigationBar_Inside();
        navbar.setBackground(Color.WHITE);
        navbar.setVisible(true);
        getContentPane().add(navbar);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

        navbar.setBounds(0, 0, 1920, 100);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setBounds(0, 0, getWidth(), 100);
            }
        });

        // Title Panel
        TitleBG = new JPanel();
        TitleBG.setBackground(new Color(52, 120, 242));
        TitleBG.setLayout(null);
        TitleBG.setBounds(0, 88, 1610, 100);

        Title = new JLabel("GLoan", SwingConstants.CENTER);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Gotham Rounded", Font.BOLD, 50));
        Title.setBounds(588, 0, 394, 98);
        TitleBG.add(Title);
        getContentPane().add(TitleBG);

        // Heading
        Heading = new JLabel("Loan Amount Details");
        Heading.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        Heading.setForeground(new Color(0, 43, 184));
        Heading.setBounds(76, 206, 727, 32);
        getContentPane().add(Heading);

        Heading1 = new JLabel("Select your loan amount and tenor to see the breakdown of your transaction.");
        Heading1.setFont(new Font("Gotham Rounded", Font.BOLD, 23));
        Heading1.setForeground(new Color(128, 161, 219));
        Heading1.setBounds(76, 244, 919, 30);
        getContentPane().add(Heading1);

        // Panel Background
        PanelBG = new JPanel();
        PanelBG.setBackground(Color.WHITE);
        PanelBG.setLayout(null);
        PanelBG.setBounds(205, 306, 1085, 460);
        getContentPane().add(PanelBG);

        // Loan Amount
        Heading2 = new JLabel("Loan Amount");
        Heading2.setFont(new Font("Gotham Rounded", Font.BOLD, 28));
        Heading2.setBounds(31, 25, 250, 30);
        PanelBG.add(Heading2);

        AmountofSlider_Label = new JLabel("PHP 5000");
        AmountofSlider_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        AmountofSlider_Label.setForeground(new Color(0, 43, 184));
        AmountofSlider_Label.setBounds(250, 25, 400, 30);
        PanelBG.add(AmountofSlider_Label);

        Heading3 = new JLabel("PHP 5,000.00");
        Heading3.setFont(new Font("Gotham Rounded", Font.BOLD, 21));
        Heading3.setBounds(120, 85, 165, 30);
        PanelBG.add(Heading3);

        Heading4 = new JLabel("min");
        Heading4.setFont(new Font("Gotham Rounded", Font.BOLD, 19));
        Heading4.setForeground(new Color(204, 204, 204));
        Heading4.setBounds(285, 85, 55, 30);
        PanelBG.add(Heading4);

        Heading5 = new JLabel("PHP 25,000.00");
        Heading5.setFont(new Font("Gotham Rounded", Font.BOLD, 21));
        Heading5.setBounds(700, 85, 190, 30);
        PanelBG.add(Heading5);

        Heading6 = new JLabel("max");
        Heading6.setFont(new Font("Gotham Rounded", Font.BOLD, 19));
        Heading6.setForeground(new Color(204, 204, 204));
        Heading6.setBounds(885, 85, 55, 30);
        PanelBG.add(Heading6);

        LoanAmountSlider = new JSlider(JSlider.HORIZONTAL, 5000, 25000, 5000);
        LoanAmountSlider.setForeground(new Color(0, 43, 184));
        LoanAmountSlider.setMajorTickSpacing(250);
        LoanAmountSlider.setOpaque(true);
        LoanAmountSlider.setBackground(Color.WHITE);
        LoanAmountSlider.setBounds(97, 120, 872, 50);
        LoanAmountSlider.setPaintTicks(true);
        LoanAmountSlider.setPaintTrack(true);
        LoanAmountSlider.setPaintLabels(false);
        PanelBG.add(LoanAmountSlider);

        LoanAmountSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int loanAmount = LoanAmountSlider.getValue();
                AmountofSlider_Label.setText("PHP " + loanAmount);
            }
        });

        Heading7 = new JLabel("Months to Pay");
        Heading7.setFont(new Font("Gotham Rounded", Font.BOLD, 28));
        Heading7.setBounds(31, 200, 340, 30);
        PanelBG.add(Heading7);

        NineMonths_CheckBox = new JCheckBox("9 Months");
        NineMonths_CheckBox.setFont(new Font("Gotham Rounded", Font.PLAIN, 25));
        NineMonths_CheckBox.setForeground(new Color(0, 28, 128));
        NineMonths_CheckBox.setBounds(353, 250, 155, 55);
        NineMonths_CheckBox.setOpaque(false);
        PanelBG.add(NineMonths_CheckBox);

        TwelveMonths_CheckBox = new JCheckBox("12 Months");
        TwelveMonths_CheckBox.setFont(new Font("Gotham Rounded", Font.PLAIN, 25));
        TwelveMonths_CheckBox.setForeground(new Color(0, 28, 128));
        TwelveMonths_CheckBox.setBounds(595, 250, 186, 55);
        TwelveMonths_CheckBox.setOpaque(false);
        PanelBG.add(TwelveMonths_CheckBox);

        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(NineMonths_CheckBox);
        buttonGroup1.add(TwelveMonths_CheckBox);

        Proceed_Button = new JButton("Proceed");
        Proceed_Button.setFont(new Font("Gotham Rounded", Font.PLAIN, 33));
        Proceed_Button.setForeground(Color.WHITE);
        Proceed_Button.setBackground(new Color(0, 28, 128));
        Proceed_Button.setFocusPainted(false);
        Proceed_Button.setBorder(new RoundedWhite(50));
        Proceed_Button.setBounds(400, 340, 282, 55);
        Proceed_Button.setBorderPainted(false);
        Proceed_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        PanelBG.add(Proceed_Button);

        Proceed_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amountInt = LoanAmountSlider.getValue();
                int LoanTerm;

                if (NineMonths_CheckBox.isSelected()) {
                    LoanTerm = 9;
                } else if (TwelveMonths_CheckBox.isSelected()) {
                    LoanTerm = 12;
                } else {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "Please choose a plan.", 22, "Okay", null);
                    return;
                }

                double amount = amountInt;
                double feeValue = amount * 0.03;
                double amountReceive = amount - feeValue;
                double monthlyDue = ((amount * 0.05) + amount)/LoanTerm;
                
                GLoanDialog loanDialog = new GLoanDialog(null, 3, 5, amount, feeValue, amountReceive, LoanTerm, String.format("%.2f", monthlyDue));
                
                if(loanDialog.getLoan) {
                    int accountId = Session.getInstance().getAccountID();
                    System.out.println("Logged in Account ID: " + accountId);

                    Connection conn = null;
                    PreparedStatement updateBalance = null;
                    PreparedStatement insertLoan = null;
                    PreparedStatement insertTransaction = null;

                    try {
                        conn = DriverManager.getConnection(
                                "jdbc:sqlserver://localhost:1433;databaseName=BankDB;encrypt=true;trustServerCertificate=true;",
                                "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD");
                        conn.setAutoCommit(false);

                        updateBalance = conn.prepareStatement("UPDATE Account SET Balance = Balance + ? WHERE AccountID = ?");
                        updateBalance.setDouble(1, amount);
                        updateBalance.setInt(2, accountId);
                        updateBalance.executeUpdate();

                        insertLoan = conn.prepareStatement("INSERT INTO Loan (AccountID, LoanAmount, LoanBalance, LoanTerms) VALUES (?, ?, ?, ?)");
                        insertLoan.setInt(1, accountId);
                        insertLoan.setDouble(2, amount);
                        insertLoan.setDouble(3, amount);
                        insertLoan.setInt(4, LoanTerm);
                        insertLoan.executeUpdate();

                        insertTransaction = conn.prepareStatement("INSERT INTO Account_Transaction (AccountID, Transact_Type, TransactionAmount) VALUES (?, 'Loan', ?)");
                        insertTransaction.setInt(1, accountId);
                        insertTransaction.setDouble(2, amount);
                        insertTransaction.executeUpdate();

                        conn.commit();
                        GcashDialog dialog = new GcashDialog(null, "Loan successful!", 22, "", 0, "Confirm", null);

                    } catch (SQLException ex) {
                        try {
                            if (conn != null) conn.rollback();
                        } catch (SQLException rollbackEx) {
                            rollbackEx.printStackTrace();
                        }
                        ex.printStackTrace();
                        GcashDialog dialog = new GcashDialog(null, null, 0, "Database error: " + ex.getMessage(), 17, "Okay", null);
                    } finally {
                        try {
                            if (updateBalance != null) updateBalance.close();
                            if (insertLoan != null) insertLoan.close();
                            if (insertTransaction != null) insertTransaction.close();
                            if (conn != null) conn.close();
                        } catch (SQLException closeEx) {
                            closeEx.printStackTrace();
                        }
                    }
                }
                
                loanDialog.getLoan = false;
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoanDetails::new);
    }
}
