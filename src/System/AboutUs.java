package System;

import HelperClasses.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AboutUs extends JFrame {

    private JLabel sidePhoto;
    private JPanel panelPurpose;
    private JPanel panelVision;
    private JPanel panelMission;

    public AboutUs() {
        setTitle("About GCash");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        getContentPane().setBackground(new Color(0x34, 0x78, 0xf2));
        setLayout(null); 

        // Add NavigationBar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, 1920, 100);
        navbar.setVisible(true);
        navbar.AboutUsButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.AboutUsButton.setBackground(Color.WHITE);
        navbar.AboutUsButton.setOpaque(true);
        navbar.AboutUsButton.setBorderPainted(false);
        getContentPane().add(navbar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
            }
        });

        // Side Photo
        sidePhoto = new JLabel();
        sidePhoto.setIcon(new ImageIcon(getClass().getResource("/Resources/Gcash SIDE.jpg")));
        sidePhoto.setBounds(945, 100, 600, 700);
        add(sidePhoto);

        // Purpose Panel
        panelPurpose = createInfoPanel("Purpose", "Make Filipinos’ everyday lives better");
        panelPurpose.setBounds(150, 150, 500, 130);
        add(panelPurpose);

        // Vision Panel
        panelVision = createInfoPanel("Vision", "Finance for all");
        panelVision.setBounds(150, 320, 500, 130);
        add(panelVision);

        // Mission Panel
        panelMission = createInfoPanel("Mission", "<html>Provide meaningful digital finance solutions<br>that empower people, organizations,<br>and the nation</html>");
        panelMission.setBounds(150, 490, 500, 180);
        add(panelMission);
    }

    private JPanel createInfoPanel(String title, String content) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 50, 204));
        panel.setBorder(new RoundedPanel(15));
        panel.setLayout(null);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Gotham Rounded", Font.PLAIN, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 10, 450, 40);
        panel.add(titleLabel);

        JLabel contentLabel = new JLabel(content);
        contentLabel.setFont(new Font("Gotham Rounded", Font.PLAIN, 20));
        contentLabel.setForeground(Color.WHITE);
        contentLabel.setBounds(20, 50, 450, 100);
        panel.add(contentLabel);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AboutUs().setVisible(true);
        });
    }
}
