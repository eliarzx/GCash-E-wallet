package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ServicesA extends JFrame {

    public ServicesA() {
        setTitle("Our Services");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(0, 28, 128)); 

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

        // Headings
        JLabel heading1 = new JLabel("Send, pay");
        heading1.setFont(new Font("Gotham Rounded", Font.BOLD, 55));
        heading1.setForeground(Color.WHITE);
        heading1.setBounds(250, 250, 500, 60);

        JLabel heading2 = new JLabel("& save all in");
        heading2.setFont(new Font("Gotham Rounded", Font.BOLD, 55));
        heading2.setForeground(Color.WHITE);
        heading2.setBounds(250, 320, 500, 60);

        JLabel heading3 = new JLabel("one place");
        heading3.setFont(new Font("Gotham Rounded", Font.BOLD, 55));
        heading3.setForeground(Color.WHITE);
        heading3.setBounds(250, 390, 500, 60);

        JLabel subheading = new JLabel("<html>Embrace the convenience of <br>cashless living with GCash, <br>the country’s #1 digital wallet.</html>");
        subheading.setFont(new Font("Gotham Rounded", Font.BOLD, 32));
        subheading.setForeground(new Color(217, 217, 217));
        subheading.setBounds(250, 470, 600, 120);

        // Icon
        JLabel iconLaptop = new JLabel();
        iconLaptop.setIcon(new ImageIcon(getClass().getResource("/Resources/Services0.png")));
        iconLaptop.setBounds(850, 250, 500, 400);
        iconLaptop.setOpaque(true);
        iconLaptop.setBackground(new Color(0, 28, 128));

        // Add components to frame
        getContentPane().add(heading1);
        getContentPane().add(heading2);
        getContentPane().add(heading3);
        getContentPane().add(subheading);
        getContentPane().add(iconLaptop);

        // Mouse click to go to ServicesB
        getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new ServicesB().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ServicesA().setVisible(true);
        });
    }
}
