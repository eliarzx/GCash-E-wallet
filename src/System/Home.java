/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package System;

import HelperClasses.*;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafae
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        loadBalance();
        
        NavigationBar_Inside navbar = new NavigationBar_Inside();
        navbar.setBounds(0, 0, getWidth(), 100); 
        navbar.setBackground(new java.awt.Color(255, 255, 255));
        navbar.HomeButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.HomeButton.setBackground(Color.WHITE);
        navbar.HomeButton.setOpaque(true);
        navbar.HomeButton.setBorderPainted(false);
        navbar.setVisible(true);
        
        getContentPane().add(navbar);
        getContentPane().setLayout(null);
        
        Cashin_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().setBackground(new java.awt.Color(0xCC, 0xD8, 0xED));
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); //Maximize the device screen
    }

  private void loadBalance() {
        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;", "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD");

            int accountId = Session.getInstance().getAccountID();

            String sql = "SELECT Balance FROM Account WHERE AccountID = ?";
            stmt = conn.prepareStatement(sql); 
            stmt.setInt(1, accountId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("Balance");
                Subheading1.setText(" PHP " + String.format("%.2f", balance));
            } else {
                Subheading1.setText("Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Subheading1.setText("Error retrieving balance.");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

 private void showAccountTransactions() {
    java.sql.Connection conn = null;
    java.sql.PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Establish the connection to the database
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BankDB;user=YOUR_DB_USERNAME;password=YOUR_DB_PASSWORD;encrypt=true;trustServerCertificate=true;", "YOUR_DB_USERNAME", "YOUR_DB_PASSWORD");

        // Get the Account ID from the session
        int accountId = Session.getInstance().getAccountID();

        // Prepare the SQL query to fetch transactions for the given account
        String sql = "SELECT TransactionID, TransactionDate, TransactionAmount, Transact_Type FROM Account_Transaction WHERE AccountID = ?";
        stmt = conn.prepareStatement(sql); // Prepare the statement
        stmt.setInt(1, accountId); // Set the account ID in the query
        rs = stmt.executeQuery(); // Execute the query

        // Create a table model to display the transactions
        String[] columns = {"Transaction ID", "Date", "Amount", "Transaction Type"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Add each transaction to the table model
        while (rs.next()) {
            int transactionId = rs.getInt("TransactionID");
            Date date = rs.getDate("TransactionDate"); // Use the correct column name
            double amount = rs.getDouble("TransactionAmount");
            String transactionType = rs.getString("Transact_Type"); // Fetch Transact_Type instead of Description

            // Add the transaction details as a row to the table model
            tableModel.addRow(new Object[]{transactionId, date, amount, transactionType});
        }

        // Create a JTable to display the transaction data
        JTable transactionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transactionTable);

        // Create and configure the dialog to display the table
        GStatementDialog history = new GStatementDialog(this, scrollPane);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources in the finally block to ensure they are always released
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}




  
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBG = new javax.swing.JPanel();
        Subheading1 = new javax.swing.JLabel();
        Subheading = new javax.swing.JLabel();
        Cashin_Button = new javax.swing.JButton();
        PanelBG1 = new javax.swing.JPanel();
        ExpressSendLogo = new javax.swing.JLabel();
        BankTransferLogo = new javax.swing.JLabel();
        GLoanLogo = new javax.swing.JLabel();
        GStatementLogo = new javax.swing.JLabel();
        ExprSendLabel = new javax.swing.JLabel();
        BankTransLabel = new javax.swing.JLabel();
        GLoanLabel = new javax.swing.JLabel();
        GStatementLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GCash");

        PanelBG.setBackground(new java.awt.Color(52, 120, 242));
        PanelBG.setForeground(new java.awt.Color(52, 120, 242));
        PanelBG.setAutoscrolls(true);

        Subheading1.setFont(new java.awt.Font("Gotham Rounded", 1, 48)); // NOI18N
        Subheading1.setForeground(new java.awt.Color(255, 255, 255));
        Subheading1.setText("PHP CHANGE THIS");
        Subheading1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Subheading.setFont(new java.awt.Font("Gotham Rounded", 1, 30)); // NOI18N
        Subheading.setForeground(new java.awt.Color(204, 216, 237));
        Subheading.setText("AVAILABLE BALANCE");
        Subheading.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        Cashin_Button.setBackground(new java.awt.Color(221, 229, 244));
        Cashin_Button.setFont(new java.awt.Font("Gotham Rounded", 0, 34)); // NOI18N
        Cashin_Button.setForeground(new java.awt.Color(52, 120, 242));
        Cashin_Button.setText("+ Cash In");
        Cashin_Button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        Cashin_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cashin_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBGLayout = new javax.swing.GroupLayout(PanelBG);
        PanelBG.setLayout(PanelBGLayout);
        PanelBGLayout.setHorizontalGroup(
            PanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBGLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(PanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Subheading, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Subheading1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cashin_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        PanelBGLayout.setVerticalGroup(
            PanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBGLayout.createSequentialGroup()
                .addGroup(PanelBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBGLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Subheading, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Subheading1))
                    .addGroup(PanelBGLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Cashin_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        PanelBG1.setBackground(new java.awt.Color(255, 255, 255));

        ExpressSendLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExpressSendLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Express Send BUTTON.png"))); // NOI18N
        ExpressSendLogo.setText(" ");
        ExpressSendLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExpressSendLogoMouseClicked(evt);
            }
        });

        BankTransferLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BankTransferLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Bank Transfer BUTTON.png"))); // NOI18N
        BankTransferLogo.setText(" ");
        BankTransferLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BankTransferLogoMouseClicked(evt);
            }
        });

        GLoanLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GLoanLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/GLoan BUTTON.png"))); // NOI18N
        GLoanLogo.setText(" ");
        GLoanLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GLoanLogoMouseClicked(evt);
            }
        });

        GStatementLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GStatementLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/GStatement BUTTON.png"))); // NOI18N
        GStatementLogo.setText(" ");
        GStatementLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GStatementLogoMouseClicked(evt);
            }
        });

        ExprSendLabel.setFont(new java.awt.Font("Gotham Rounded", 1, 40)); // NOI18N
        ExprSendLabel.setForeground(new java.awt.Color(0, 43, 184));
        ExprSendLabel.setText("Express Send");
        ExprSendLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExprSendLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExprSendLabelMouseEntered(evt);
            }
        });

        BankTransLabel.setFont(new java.awt.Font("Gotham Rounded", 1, 40)); // NOI18N
        BankTransLabel.setForeground(new java.awt.Color(0, 43, 184));
        BankTransLabel.setText("Bank Transfer");
        BankTransLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BankTransLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BankTransLabelMouseEntered(evt);
            }
        });

        GLoanLabel.setFont(new java.awt.Font("Gotham Rounded", 1, 40)); // NOI18N
        GLoanLabel.setForeground(new java.awt.Color(0, 43, 184));
        GLoanLabel.setText("GLoan");
        GLoanLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GLoanLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GLoanLabelMouseEntered(evt);
            }
        });

        GStatementLabel.setFont(new java.awt.Font("Gotham Rounded", 1, 40)); // NOI18N
        GStatementLabel.setForeground(new java.awt.Color(0, 43, 184));
        GStatementLabel.setText("GStatement");
        GStatementLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GStatementLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GStatementLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout PanelBG1Layout = new javax.swing.GroupLayout(PanelBG1);
        PanelBG1.setLayout(PanelBG1Layout);
        PanelBG1Layout.setHorizontalGroup(
            PanelBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBG1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(ExpressSendLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BankTransferLogo)
                .addGap(93, 93, 93)
                .addComponent(GLoanLogo)
                .addGap(107, 107, 107)
                .addComponent(GStatementLogo)
                .addGap(83, 83, 83))
            .addGroup(PanelBG1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(ExprSendLabel)
                .addGap(76, 76, 76)
                .addComponent(BankTransLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(GLoanLabel)
                .addGap(111, 111, 111)
                .addComponent(GStatementLabel)
                .addGap(63, 63, 63))
        );
        PanelBG1Layout.setVerticalGroup(
            PanelBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBG1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(PanelBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BankTransferLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PanelBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ExpressSendLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GLoanLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GStatementLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelBG1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExprSendLabel)
                    .addComponent(BankTransLabel)
                    .addComponent(GLoanLabel)
                    .addComponent(GStatementLabel))
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelBG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addComponent(PanelBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelBG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Cashin_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cashin_ButtonActionPerformed
        CashIn nextWindow = new CashIn();
        nextWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Cashin_ButtonActionPerformed

    private void ExpressSendLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExpressSendLogoMouseClicked
        ExpressSend expressSend = null;
        try {
            expressSend = new ExpressSend();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        expressSend.setVisible(true);

        // Dispose the parent JFrame that holds this panel
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
        this.dispose();
    }//GEN-LAST:event_ExpressSendLogoMouseClicked

    private void ExprSendLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExprSendLabelMouseEntered
        java.awt.Color originalColor = ExprSendLabel.getForeground();
        java.awt.Color hoverColor = new java.awt.Color(0,0,0); 

        ExprSendLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExprSendLabel.setForeground(hoverColor);
                ExprSendLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExprSendLabel.setForeground(originalColor);
            }
        });
    }//GEN-LAST:event_ExprSendLabelMouseEntered

    private void BankTransLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BankTransLabelMouseEntered
        java.awt.Color originalColor = BankTransLabel.getForeground();
        java.awt.Color hoverColor = new java.awt.Color(0,0,0); 

        BankTransLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BankTransLabel.setForeground(hoverColor);
                BankTransLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BankTransLabel.setForeground(originalColor);
            }
        });
    }//GEN-LAST:event_BankTransLabelMouseEntered

    private void GLoanLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GLoanLabelMouseEntered
        java.awt.Color originalColor = GLoanLabel.getForeground();
        java.awt.Color hoverColor = new java.awt.Color(0,0,0); 

        GLoanLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GLoanLabel.setForeground(hoverColor);
                GLoanLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GLoanLabel.setForeground(originalColor);
            }
        });
    }//GEN-LAST:event_GLoanLabelMouseEntered

    private void GStatementLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GStatementLabelMouseEntered
        java.awt.Color originalColor = GStatementLabel.getForeground();
        java.awt.Color hoverColor = new java.awt.Color(0,0,0); 

        GStatementLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GStatementLabel.setForeground(hoverColor);
                GStatementLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GStatementLabel.setForeground(originalColor);
            }
        });
    }//GEN-LAST:event_GStatementLabelMouseEntered

    private void BankTransferLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BankTransferLogoMouseClicked
        BankTransfer bnkTrans = new BankTransfer();
        bnkTrans.setVisible(true);

        // Dispose the parent JFrame that holds this panel
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
        this.dispose();
    }//GEN-LAST:event_BankTransferLogoMouseClicked

    private void GLoanLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GLoanLogoMouseClicked
        Loan gloan = new Loan();
        gloan.setVisible(true);

        // Dispose the parent JFrame that holds this panel
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) window).dispose();
        }
        this.dispose();
    }//GEN-LAST:event_GLoanLogoMouseClicked

    private void GStatementLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GStatementLogoMouseClicked
        // BANK STATEMENT
        showAccountTransactions();
    }//GEN-LAST:event_GStatementLogoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BankTransLabel;
    private javax.swing.JLabel BankTransferLogo;
    private javax.swing.JButton Cashin_Button;
    private javax.swing.JLabel ExprSendLabel;
    private javax.swing.JLabel ExpressSendLogo;
    private javax.swing.JLabel GLoanLabel;
    private javax.swing.JLabel GLoanLogo;
    private javax.swing.JLabel GStatementLabel;
    private javax.swing.JLabel GStatementLogo;
    private javax.swing.JPanel PanelBG;
    private javax.swing.JPanel PanelBG1;
    private javax.swing.JLabel Subheading;
    private javax.swing.JLabel Subheading1;
    // End of variables declaration//GEN-END:variables
}
