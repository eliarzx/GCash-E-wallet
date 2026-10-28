package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LandingPage extends JFrame {

    private JPanel backgroundPanel;
    private BufferedImage backgroundImage;

    public LandingPage() {
        setTitle("Welcome, ka-GCash.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(0x34, 0x78, 0xf2));

        // Load image resource
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Resources/Gcash BRAND IDENTITY.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Navigation Bar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, getWidth(), 100);
        navbar.setBackground(Color.WHITE);
        navbar.CustomerButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.CustomerButton.setBackground(Color.WHITE);
        navbar.CustomerButton.setOpaque(true);
        navbar.CustomerButton.setBorderPainted(false);
        getContentPane().add(navbar);

        // Background image panel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setBounds(0, 100, getWidth(), getHeight() - 100);
        backgroundPanel.setLayout(null);
        getContentPane().add(backgroundPanel);


        // Resizing behavior
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
                backgroundPanel.setBounds(0, 100, getWidth(), getHeight() - 100);
                backgroundPanel.repaint(); // Force repaint when resizing
            }
        });


        // Click to next page
        getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new GetStarted().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPage().setVisible(true));
    }
}
