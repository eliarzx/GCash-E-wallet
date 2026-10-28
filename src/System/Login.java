
package System;

import HelperClasses.*;
import HelperClasses.Connector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {

    private JLabel Heading;
    private JPanel Panel;
    private JLabel jLabel1;
    private JTextField PhoneNumber_Field1;
    private JPasswordField MPIN_PasswordField;
    private JLabel MPIN_Label;
    private JLabel MPIN_Label1;
    private JButton Proceed_Button2;
    private NavigationBar navbar;

    public Login() {
        super("Welcome back!");

        // Frame settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(52, 120, 242));
        getContentPane().setBackground(new java.awt.Color(52, 120, 242));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);

        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, 1920, 100);
        navbar.setVisible(true);
        navbar.LoginButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.LoginButton.setBackground(Color.WHITE);
        navbar.LoginButton.setOpaque(true);
        navbar.LoginButton.setBorderPainted(false);
        getContentPane().add(navbar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
            }
        });


        Heading = new JLabel("LOG IN", SwingConstants.CENTER);
        Heading.setFont(new java.awt.Font("Gotham Rounded", 0, 51));
        Heading.setForeground(new java.awt.Color(255, 255, 255));
        Heading.setBounds(545, 147, 391, 60); // Centered heading
        getContentPane().add(Heading);

        Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBackground(new java.awt.Color(255, 255, 255));
        Panel.setBounds(318, 225, 874, 500); // Centered white panel
        getContentPane().add(Panel);

        MPIN_Label1 = new JLabel("Enter your Phone Number");
        MPIN_Label1.setFont(new java.awt.Font("Gotham Rounded", 1, 26));
        MPIN_Label1.setForeground(new java.awt.Color(128, 161, 219));
        MPIN_Label1.setBounds(112, 62, 503, 30);
        Panel.add(MPIN_Label1);

        jLabel1 = new JLabel(" PH ", SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Gotham Rounded", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setBackground(new java.awt.Color(52, 120, 242));
        jLabel1.setOpaque(true);
        jLabel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(52, 120, 242), 10));
        jLabel1.setBounds(112, 100, 93, 60);
        Panel.add(jLabel1);

        PhoneNumber_Field1 = new JTextField();
        PhoneNumber_Field1.setFont(new java.awt.Font("Gotham Rounded", 1, 27));
        PhoneNumber_Field1.setBorder(new RoundedBorder(20));
        PhoneNumber_Field1.setBounds(215, 100, 563, 70);
        Panel.add(PhoneNumber_Field1);

        MPIN_Label = new JLabel("Enter your MPIN");
        MPIN_Label.setFont(new java.awt.Font("Gotham Rounded", 1, 26));
        MPIN_Label.setForeground(new java.awt.Color(128, 161, 219));
        MPIN_Label.setBounds(112, 188, 325, 30);
        Panel.add(MPIN_Label);

        MPIN_PasswordField = new JPasswordField("");
        MPIN_PasswordField.setFont(new java.awt.Font("Gotham Rounded", 1, 27));
        MPIN_PasswordField.setForeground(new java.awt.Color(128, 161, 219));
        MPIN_PasswordField.setBorder(new RoundedBorder(20));
        MPIN_PasswordField.setBounds(112, 226, 674, 77);
        Panel.add(MPIN_PasswordField);

        Proceed_Button2 = new JButton("Log in");
        Proceed_Button2.setFont(new java.awt.Font("Gotham Rounded", 0, 33));
        Proceed_Button2.setBackground(new java.awt.Color(0, 43, 184));
        Proceed_Button2.setForeground(new java.awt.Color(255, 255, 255));
        Proceed_Button2.setBorder(new RoundedWhite(50));
        Proceed_Button2.setBorderPainted(false);
        Proceed_Button2.setFocusPainted(false);
        Proceed_Button2.setBounds(247, 348, 394, 66);
        Proceed_Button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Proceed_Button2.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    Proceed_Button2.setBackground(new java.awt.Color(6, 54, 212));
                }
                public void mouseExited(MouseEvent e) {
                    Proceed_Button2.setBackground(new java.awt.Color(0, 43, 184));
                }
        });
        Panel.add(Proceed_Button2);
        
        Proceed_Button2.addActionListener(new ActionListener() {
            
       public void actionPerformed(ActionEvent evt) {
        String phoneNumber = PhoneNumber_Field1.getText();
        String mpin = String.valueOf(MPIN_PasswordField.getPassword());

        try {
            java.sql.Connection con = Connector.connectToDatabase();
            String query = "SELECT * FROM Account WHERE PhoneNumber = ? AND MPIN = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, phoneNumber);
            pst.setString(2, mpin);
            
              java.sql.ResultSet rs = pst.executeQuery();
             
            if (rs.next()) {
                // Login successful
                int accountID = rs.getInt("AccountID");
                System.out.print(accountID);
                Session.getInstance().setAccountID(accountID);
                System.out.println("Logged in Account ID: " + accountID);
                GcashDialog dialog = new GcashDialog(null, "Login Successful!", 18, null, 0, "Confirm", null);
                Home home = new Home();
                home.setVisible(true);
                java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor((Component) evt.getSource());
                if (window instanceof javax.swing.JFrame) {
                    ((javax.swing.JFrame) window).dispose();
                }
            } else {
                GcashDialog dialog = new GcashDialog(null, "LOGIN FAILED", 20, "Invalid Phone Number or MPIN!", 16, "Confirm", null);
            }
            con.close();
        } catch (Exception e) {
            GcashDialog dialog = new GcashDialog(null, "ERROR", 20, "Database Error: " + e.getMessage(), 16, "Confirm", null);
        }
 
    }
});
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
