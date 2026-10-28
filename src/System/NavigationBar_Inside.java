package System;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

class NavigationBar_Inside extends JPanel {

    JButton HomeButton;
    private JButton ProfileButton;
    private JButton LogoutButton;
    private JLabel jLabel1;
    private JLabel jLabel2;

    private final Color normalColor = new Color(128, 161, 219);
    private final Color hoverColor = new Color(0, 43, 184);

    public NavigationBar_Inside() {
        initComponents();
    }

    private void initComponents() {

        HomeButton = new JButton("Home");
        ProfileButton = new JButton("Profile");
        LogoutButton = new JButton("Log out");
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        setBackground(Color.WHITE);

        setupButton(HomeButton);
        setupButton(ProfileButton);
        setupButton(LogoutButton);

        // Add action listeners for navigation
        HomeButton.addActionListener(evt -> HomeButtonActionPerformed(evt));
        ProfileButton.addActionListener(evt -> ProfileButtonActionPerformed(evt));
        LogoutButton.addActionListener(evt -> LogoutButtonActionPerformed(evt));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Gcash LOGO W_NAME.png"))); // Your logo

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(717, 717, 717)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 28, Short.MAX_VALUE))
        );
    }

    private void setupButton(JButton button) {
        button.setFont(new java.awt.Font("Gotham Rounded", 1, 27));
        button.setForeground(normalColor);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(normalColor);
            }
        });
    }

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Home home = new Home();
        home.setVisible(true);

        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    private void ProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Profile profile = new Profile();
        profile.setVisible(true);

        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
    JDialog dialog = new JDialog((Frame) null, "Confirm Logout", true);
    dialog.setSize(400, 200);
    dialog.setLayout(null);
    dialog.getContentPane().setBackground(Color.decode("#3478f2"));
    dialog.setLocationRelativeTo(null); // Center the dialog

    Font customFont = new Font("Gotham Rounded", Font.BOLD, 14);

    JLabel message = new JLabel("Are you sure you want to logout?");
    message.setForeground(Color.WHITE);
    message.setBounds(60, 30, 300, 30);
    message.setFont(new Font("Gotham Rounded", Font.BOLD, 16));
    dialog.add(message);

    JButton confirmBtn = new JButton("Confirm");
    confirmBtn.setBackground(Color.WHITE);
    confirmBtn.setForeground(Color.decode("#001c80"));
    confirmBtn.setBounds(70, 100, 100, 40);
    confirmBtn.setFocusPainted(false);
    confirmBtn.setFont(customFont);
    confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    dialog.add(confirmBtn);

    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.setBackground(Color.WHITE);
    cancelBtn.setForeground(Color.decode("#001c80"));
    cancelBtn.setBounds(220, 100, 100, 40);
    cancelBtn.setFocusPainted(false);
    cancelBtn.setFont(customFont);
    cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    dialog.add(cancelBtn);

    // Action for Confirm
    confirmBtn.addActionListener(e -> {
        dialog.dispose();
        dialog.setVisible(false);
        new LandingPage().setVisible(true);
        // Also dispose the current admin panel if needed
        SwingUtilities.getWindowAncestor((Component) evt.getSource()).dispose();
    });

    // Action for Cancel
    cancelBtn.addActionListener(e -> dialog.dispose());

    dialog.setVisible(true);
    }
}
