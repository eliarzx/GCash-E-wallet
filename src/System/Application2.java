package System;

import HelperClasses.*;
import static HelperClasses.Connector.connectToDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Application2 extends JFrame {

    private JButton CreateAccount_Button;
    private JLabel Heading;
    private JTextField MPIN_Field1;
    private JTextField MPIN_Field2;
    private JLabel MPIN_Label;
    private JLabel MPIN_Label2;
    private JTextField PhoneNumber_Field;
    private JLabel PhoneNumber_Label;
    private JLabel TermsText;
    private JLabel TermsText1;
    private JLabel jLabel1;

    // Database connection setup
    private Connection connection;
    private final int customerID;

public Application2(int customerID) {
    this.customerID = customerID;
    System.out.println("Application2 Constructor called with customerID: " + customerID);  // Debugging line
    setTitle("Finish Creating an Account");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setBackground(Color.WHITE);
    getContentPane().setBackground(Color.WHITE);
    getContentPane().setLayout(null);

        // Initialize DB connection
        connection = connectToDatabase();

        // Navigation Bar
        NavigationBar_Blue navbar = new NavigationBar_Blue();
        navbar.setBounds(0, 0, 1920, 100);
        navbar.setVisible(true);
        getContentPane().add(navbar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
            }
        });

        // Heading
        Heading = new JLabel("APPLICATION");
        Heading.setFont(new Font("Gotham Rounded", Font.PLAIN, 51));
        Heading.setForeground(new Color(0, 43, 184));
        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setBounds(613, 128, 391, 60);
        getContentPane().add(Heading);

        // Phone Number Label
        PhoneNumber_Label = new JLabel("Enter a valid phone number");
        PhoneNumber_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        PhoneNumber_Label.setForeground(new Color(128, 161, 219));
        PhoneNumber_Label.setBounds(397, 225, 455, 30);
        getContentPane().add(PhoneNumber_Label);

        // "+63" Label
        jLabel1 = new JLabel(" PH ");
        jLabel1.setFont(new Font("Gotham Rounded", Font.BOLD, 30));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setOpaque(true);
        jLabel1.setBackground(new Color(128, 161, 219));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setBorder(BorderFactory.createLineBorder(new Color(128, 161, 219), 10));
        jLabel1.setBounds(397, 264, 122, 75);
        getContentPane().add(jLabel1);

        // Phone Number Field
        PhoneNumber_Field = new JTextField();
        PhoneNumber_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        PhoneNumber_Field.setBorder(new RoundedBorder(20));
        PhoneNumber_Field.setBounds(525, 264, 632, 75);
        getContentPane().add(PhoneNumber_Field);

        // MPIN Label
        MPIN_Label = new JLabel("Enter MPIN");
        MPIN_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        MPIN_Label.setForeground(new Color(128, 161, 219));
        MPIN_Label.setBounds(397, 360, 455, 30);
        getContentPane().add(MPIN_Label);

        // MPIN Field 1
        MPIN_Field1 = new JTextField();
        MPIN_Field1.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        MPIN_Field1.setBorder(new RoundedBorder(20));
        MPIN_Field1.setBounds(397, 400, 760, 75);
        getContentPane().add(MPIN_Field1);

        // Confirm MPIN Label
        MPIN_Label2 = new JLabel("Confirm MPIN");
        MPIN_Label2.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        MPIN_Label2.setForeground(new Color(128, 161, 219));
        MPIN_Label2.setBounds(397, 495, 455, 30);
        getContentPane().add(MPIN_Label2);

        // MPIN Field 2
        MPIN_Field2 = new JTextField();
        MPIN_Field2.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        MPIN_Field2.setBorder(new RoundedBorder(20));
        MPIN_Field2.setBounds(397, 535, 760, 75);
        getContentPane().add(MPIN_Field2);

        // Create Account Button
        CreateAccount_Button = new JButton("Create Account");
        CreateAccount_Button.setFont(new Font("Gotham Rounded", Font.PLAIN, 33));
        CreateAccount_Button.setForeground(Color.WHITE);
        CreateAccount_Button.setBackground(new Color(52, 120, 242));
        CreateAccount_Button.setBorder(new RoundedWhite(50));
        CreateAccount_Button.setFocusPainted(false);
        CreateAccount_Button.setBounds(555, 635, 424, 83);
        CreateAccount_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(CreateAccount_Button);

        // Terms Text
        TermsText = new JLabel("By creating this account, I agree to");
        TermsText.setFont(new Font("Gotham Rounded", Font.BOLD, 20));
        TermsText.setForeground(new Color(128, 161, 219));
        TermsText.setHorizontalAlignment(SwingConstants.CENTER);
        TermsText.setBounds(555, 730, 424, 25);
        getContentPane().add(TermsText);

        TermsText1 = new JLabel("the terms and conditions.");
        TermsText1.setFont(new Font("Gotham Rounded", Font.BOLD, 20));
        TermsText1.setForeground(new Color(128, 161, 219));
        TermsText1.setHorizontalAlignment(SwingConstants.CENTER);
        TermsText1.setBounds(555, 755, 424, 25);
        getContentPane().add(TermsText1);

        // Create Account Button Action Listener
        CreateAccount_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String phoneNumber = PhoneNumber_Field.getText().trim();
                String mpin1 = MPIN_Field1.getText().trim();
                String mpin2 = MPIN_Field2.getText().trim();

                // Validate phone number
                if (!phoneNumber.matches("^09\\d{9}$")) {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "Phone number must be 11 digits and start with '09'.", 18, "Okay", null);
                    return;
                }

                // Validate MPIN
                if (mpin1.isEmpty() || mpin2.isEmpty()) {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "Both MPIN fields must be filled.", 20, "Okay", null);
                    return;
                }

                if (!mpin1.equals(mpin2)) {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "MPINs do not match.", 20, "Okay", null);
                    return;
                }

                if (!mpin1.matches("\\d{4}")) {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "MPIN must be exactly 4 numeric digits.", 18, "Okay", null);
                    return;
                }

                // Insert into Account table
                try {
                String query = "INSERT INTO Account (CustomerID, PhoneNumber, MPIN) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, customerID);
                statement.setString(2, phoneNumber);
                statement.setString(3, mpin1);


                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        GcashDialog dialog = new GcashDialog(null, null, 0, "Account created successfully!", 20, "Confirm", null);
                        LandingPage nextWindow = new LandingPage();
                        // Optionally close this window
                        // dispose();
                    } else {
                        GcashDialog dialog = new GcashDialog(null, null, 0, "Failed to create account.", 20, "Okay", null);
                    }
                } catch (SQLException e) {
                    GcashDialog dialog = new GcashDialog(null, null, 0, "Error: " + e.getMessage(), 18, "Okay", null);
                }
            }
        });
    }

    public static void main(String[] args) {

    }
}
