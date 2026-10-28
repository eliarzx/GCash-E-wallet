package System;

import HelperClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BankTransfer extends JFrame {

    private JPanel titleBG, panelBanks;
    private JLabel titleLabel, headingLabel, subheadingLabel, iconLabel, partnerBanksLabel;
    private JButton BPI_Button, BDO_Button, Metrobank_Button, PSBank_Button, Landbank_Button, RCBC_Button;

    public BankTransfer() {
        // Frame settings
        setTitle("Transfer Easily!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Navbar
        NavigationBar_Inside navbar = new NavigationBar_Inside();
        navbar.setBackground(Color.WHITE);
        navbar.setVisible(true);
        navbar.setBounds(0, 0, 1920, 100);
        add(navbar);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setBounds(0, 0, getWidth(), 100);
            }
        });

        // Title background
        titleBG = new JPanel();
        titleBG.setBackground(new Color(52, 120, 242));
        titleBG.setLayout(null);
        titleBG.setBounds(0, 100, 1920, 110);
        add(titleBG);

        // Title label
        titleLabel = new JLabel("Bank Transfer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(560, 0, 400, 110);
        titleBG.add(titleLabel);

        // Main heading
        headingLabel = new JLabel("Easily transfer funds now!");
        headingLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 38));
        headingLabel.setForeground(new Color(0, 43, 184));
        headingLabel.setBounds(180, 240, 700, 50);
        add(headingLabel);

        // Subheading
        subheadingLabel = new JLabel("<html>Fast, secure, and hassle-free transfers <br> at your fingertips.</html>");
        subheadingLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 28));
        subheadingLabel.setForeground(new Color(128, 161, 219));
        subheadingLabel.setBounds(180, 290, 700, 100);
        add(subheadingLabel);

        // Icon
        iconLabel = new JLabel(new ImageIcon(getClass().getResource("/Resources/Bank Transfer LOGO.png")));
        iconLabel.setBounds(1000, 230, 221, 171);
        add(iconLabel);

        // Panel for banks
        panelBanks = new JPanel();
        panelBanks.setBackground(new Color(221, 229, 244));
        panelBanks.setLayout(null);
        panelBanks.setBounds(0, 480, 1920, 350);
        add(panelBanks);

        // Partner Banks label
        partnerBanksLabel = new JLabel("Partner Banks", SwingConstants.CENTER);
        partnerBanksLabel.setFont(new Font("Gotham Rounded", Font.BOLD, 42));
        partnerBanksLabel.setForeground(new Color(0, 43, 184));
        partnerBanksLabel.setBounds(420, 20, 700, 50);
        panelBanks.add(partnerBanksLabel);

        // Create bank buttons
        BPI_Button = createBankButton("/Resources/BPI LOGO.png", 150, 100);
        BDO_Button = createBankButton("/Resources/BDO LOGO.png", 350, 100);
        Metrobank_Button = createBankButton("/Resources/Metrobank LOGO.png", 550, 100);
        Landbank_Button = createBankButton("/Resources/Landbank LOGO.png", 750, 100);
        RCBC_Button = createBankButton("/Resources/RCBC LOGO.png", 950, 100);
        PSBank_Button = createBankButton("/Resources/PSBank LOGO.png", 1150, 100);

        // Add buttons to panel
        panelBanks.add(BPI_Button);
        panelBanks.add(BDO_Button);
        panelBanks.add(Metrobank_Button);
        panelBanks.add(Landbank_Button);
        panelBanks.add(RCBC_Button);
        panelBanks.add(PSBank_Button);

        // Add action listeners that set the selected bank
        BPI_Button.addActionListener(e -> {
            BankSession.setSelectedBank("BPI");
            openBankTransfer2();
            this.dispose();
        });

        BDO_Button.addActionListener(e -> {
            BankSession.setSelectedBank("BDO");
            openBankTransfer2();
            this.dispose();
        });

        Metrobank_Button.addActionListener(e -> {
            BankSession.setSelectedBank("Metrobank");
            openBankTransfer2();
            this.dispose();
        });

        Landbank_Button.addActionListener(e -> {
            BankSession.setSelectedBank("Landbank");
            openBankTransfer2();
            this.dispose();
        });

        RCBC_Button.addActionListener(e -> {
            BankSession.setSelectedBank("RCBC");
            openBankTransfer2();
            this.dispose();
        });

        PSBank_Button.addActionListener(e -> {
            BankSession.setSelectedBank("PSBank");
            openBankTransfer2();
            this.dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createBankButton(String iconPath, int x, int y) {
        JButton button = new JButton(new ImageIcon(getClass().getResource(iconPath)));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setBackground(Color.WHITE);
        button.setBounds(x, y, 170, 100);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 220, 220));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }

    private void openBankTransfer2() {
        BankTransfer2 btf = new BankTransfer2();
        btf.setVisible(true);

        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            ((JFrame) window).dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankTransfer::new);
    }
}
