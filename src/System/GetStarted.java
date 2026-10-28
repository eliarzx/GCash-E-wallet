package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import HelperClasses.RoundedBorder;

public class GetStarted extends JFrame {

    private JLabel ProgressLabel;
    private JLabel SideIcon;
    private JLabel Text1;
    private JLabel Text2;
    private JLabel Text3;
    private RoundedProgressBar jProgressBar2;  
    
    public GetStarted() {
        // Frame settings
        setTitle("Get Started!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(0x34, 0x78, 0xf2));

        // Navigation bar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, getWidth(), 100);
        navbar.setBackground(Color.WHITE);
        navbar.CustomerButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.CustomerButton.setBackground(Color.WHITE);
        navbar.CustomerButton.setOpaque(true);
        navbar.CustomerButton.setBorderPainted(false);
        getContentPane().add(navbar);

        // Headings
        Text1 = new JLabel("Get Started");
        Text1.setFont(new Font("Gotham Rounded", Font.BOLD, 40));
        Text1.setForeground(Color.WHITE);
        Text1.setBounds(150, 250, 600, 40);
        Text1.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(Text1);

        Text2 = new JLabel("Your Gcash story ");
        Text2.setFont(new Font("Gotham Rounded", Font.BOLD, 63));
        Text2.setForeground(Color.WHITE);
        Text2.setBounds(150, 300, 600, 80);
        Text2.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(Text2);

        Text3 = new JLabel(" begins here!");
        Text3.setFont(new Font("Gotham Rounded", Font.BOLD, 63));
        Text3.setForeground(Color.WHITE);
        Text3.setBounds(150, 380, 600, 80);
        Text3.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(Text3);

        // Rounded Progress Bar
        jProgressBar2 = new RoundedProgressBar(0, 100);
        jProgressBar2.setValue(0);
        jProgressBar2.setBounds(200, 500, 500, 50);
        jProgressBar2.setForeground(Color.WHITE);  // Set progress bar fill color to white
        jProgressBar2.setBackground(new Color(0xD3, 0xD3, 0xD3));  // Light gray background for unfilled portion
        jProgressBar2.setBorder(new RoundedBorder(3));
        getContentPane().add(jProgressBar2);

        // Progress Label
        ProgressLabel = new JLabel("In progress...");
        ProgressLabel.setFont(new Font("Gotham Rounded", Font.PLAIN, 32));
        ProgressLabel.setForeground(Color.WHITE);
        ProgressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ProgressLabel.setBounds(200, 560, 500, 40);
        getContentPane().add(ProgressLabel);

        // Side Icon
        SideIcon = new JLabel(new ImageIcon(getClass().getResource("/Resources/Phone SIDEBAR.png")));
        SideIcon.setBounds(800, 100, 635, 675);
        getContentPane().add(SideIcon);

        // Handle resizing
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setSize(getWidth(), 100);
            }
        });

        // Start progress animation
        startProgress();
    }

    private void startProgress() {
        Timer timer = new Timer(100, new ActionListener() {
            private int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter < 100) {
                    counter++;
                    jProgressBar2.setValue(counter);

                    if (counter == 25) {
                        ProgressLabel.setText("Preparing everything");
                    } else if (counter == 50) {
                        ProgressLabel.setText("Almost ready...");
                    } else if (counter == 75) {
                        ProgressLabel.setText("Just a few more seconds.");
                    } else if (counter == 99) {
                        ProgressLabel.setText("Ready to go. Welcome!");
                    }

                } else {
                    ((Timer) e.getSource()).stop(); // Stops the timer once progress reaches 100%
                    new Application().setVisible(true); // Open the Application window
                    dispose(); // Close the GetStarted window

                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GetStarted().setVisible(true));
    }

    // Custom class to create a rounded progress bar
    class RoundedProgressBar extends JProgressBar {
        public RoundedProgressBar(int min, int max) {
            super(min, max);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isOpaque()) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);  
            }
            super.paintComponent(g);
        }
    }
}
