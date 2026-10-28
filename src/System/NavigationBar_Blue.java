package System;

import java.awt.Cursor;
import javax.swing.*;


public class NavigationBar_Blue extends javax.swing.JPanel {

    public NavigationBar_Blue() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 216, 237));

        // Customer Button
        CustomerButton = button("Customer");
        
        // Services Button
        ServicesButton = button("Services");
        
        // About Us Button
        AboutUsButton = button("About Us");
        
        // Help Center Button
        HelpCenterButton = button("Help Center");
        
        // Login Button
        LoginButton = button("Log in");

        CustomerButton.addActionListener(evt -> CustomerButtonActionPerformed(evt));
        ServicesButton.addActionListener(evt -> ServicesButtonActionPerformed(evt));
        AboutUsButton.addActionListener(evt -> AboutUsButtonActionPerformed(evt));
        HelpCenterButton.addActionListener(evt -> HelpCenterButtonActionPerformed(evt));
        LoginButton.addActionListener(evt -> LoginButtonActionPerformed(evt));

        jLabel1.setToolTipText("");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Gcash LOGO W_NAME.png")));

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
                    .addComponent(CustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(ServicesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(289, 289, 289)
                    .addComponent(HelpCenterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(AboutUsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ServicesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HelpCenterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AboutUsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addGap(0, 28, Short.MAX_VALUE))
        );
    }
    
    private JButton button(String name) {
        JButton button = new JButton(name);
        button.setFont(new java.awt.Font("Gotham Rounded", 1, 27));
        button.setForeground(new java.awt.Color(128, 161, 219));
        button.setBorder(null);
        button.setFocusPainted(false); // This disables the focus border
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new java.awt.Color(0x00, 0x2b, 0xb8));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(new java.awt.Color(128, 161, 219));
            }
        });
        
        return button;
    }

    private void CustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        LandingPage landingpage = new LandingPage();
        landingpage.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    private void AboutUsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AboutUs abtus = new AboutUs();
        abtus.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    private void ServicesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ServicesA servicesA = new ServicesA();
        servicesA.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    private void HelpCenterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        HelpCenter help = new HelpCenter();
        help.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Login login = new Login();
        login.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
    }

    // Variables declaration 
    javax.swing.JButton AboutUsButton;
    javax.swing.JButton CustomerButton;
    javax.swing.JButton HelpCenterButton;
    javax.swing.JButton LoginButton;
    javax.swing.JButton ServicesButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
}

