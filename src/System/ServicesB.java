/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServicesB extends JFrame {

    public ServicesB() {
        setTitle("Our Services");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(204, 216, 237)); // Light Blue

        // Navigation Bar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, getWidth(), 100);

        // Dynamically resize navbar when window resizes
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setSize(getWidth(), 100);
            }
        });

        navbar.setBackground(Color.WHITE);

        // Highlight Services Button
        navbar.ServicesButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.ServicesButton.setBackground(Color.WHITE);
        navbar.ServicesButton.setOpaque(true);
        navbar.ServicesButton.setBorderPainted(false);

        getContentPane().add(navbar);

        // Heading
        JLabel heading1 = new JLabel("Send Money");
        heading1.setFont(new Font("Gotham Rounded", Font.BOLD, 53));
        heading1.setForeground(new Color(0, 43, 184));
        heading1.setBounds(173, 138, 500, 60);

        // Subheading
        JLabel subheading1 = new JLabel("<html>The fastest, secure-est &<br>reliable-est way to send<br>money</html>");
        subheading1.setFont(new Font("Gotham Rounded", Font.BOLD, 38));
        subheading1.setForeground(new Color(52, 120, 242));
        subheading1.setBounds(173, 210, 520, 170);  

        // Express Send Icon
        JLabel expressIcon = new JLabel();
        expressIcon.setIcon(new ImageIcon(getClass().getResource("/Resources/Express Send LOGO.png")));
        expressIcon.setBounds(321, 410, 261, 158);

        // Express Send Text
        JLabel expressTitle = new JLabel("Express Send");
        expressTitle.setFont(new Font("Gotham Rounded", Font.BOLD, 38));
        expressTitle.setForeground(new Color(52, 120, 242));
        expressTitle.setBounds(321, 580, 300, 50);

        JLabel expressDesc = new JLabel("<html>The best and fastest way <br> to send </html>");
        expressDesc.setFont(new Font("Gotham Rounded", Font.BOLD, 29));
        expressDesc.setForeground(new Color(0, 43, 184));
        expressDesc.setBounds(321, 640, 400, 80);

        // Bank Transfer Icon
        JLabel bankIcon = new JLabel();
        bankIcon.setIcon(new ImageIcon(getClass().getResource("/Resources/Bank Transfer LOGO.png")));
        bankIcon.setBounds(850, 410, 307, 158);

        // Bank Transfer Text
        JLabel bankTitle = new JLabel("Bank Transfer");
        bankTitle.setFont(new Font("Gotham Rounded", Font.BOLD, 38));
        bankTitle.setForeground(new Color(52, 120, 242));
        bankTitle.setBounds(850, 580, 350, 50);

        JLabel bankDesc = new JLabel("<html>Transfer to our partner <br> banks safely </html>");
        bankDesc.setFont(new Font("Gotham Rounded", Font.BOLD, 29));
        bankDesc.setForeground(new Color(0, 43, 184));
        bankDesc.setBounds(850, 640, 350, 80);

        // Add components to frame
        getContentPane().add(heading1);
        getContentPane().add(subheading1);
        getContentPane().add(expressIcon);
        getContentPane().add(expressTitle);
        getContentPane().add(expressDesc);
        getContentPane().add(bankIcon);
        getContentPane().add(bankTitle);
        getContentPane().add(bankDesc);

        // Navigation to ServicesC on click
        getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ServicesC().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServicesB().setVisible(true));
    }
}
