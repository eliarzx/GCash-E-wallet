
package System;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpCenter extends JFrame {

    // Components
    private JPanel LinePanel;
    private JLabel QuestionLabel;
    private JTextArea QuestionArea;
    private JButton SubmitButton;
    private JLabel ImageLabel;
    private JScrollPane ScrollPane;

    public HelpCenter() {
        // Frame Settings
        setTitle("Have a Question? Inquire!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(new Color(52, 120, 242)); // #3478f2
        getContentPane().setBackground(new Color(52, 120, 242));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Add NavigationBar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, 1920, 100); // temp value
        navbar.setVisible(true);
        navbar.HelpCenterButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.HelpCenterButton.setBackground(Color.WHITE);
        navbar.HelpCenterButton.setOpaque(true);
        navbar.HelpCenterButton.setBorderPainted(false);
        getContentPane().add(navbar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
            }
        });

        // Components
        QuestionLabel = new JLabel("Reach out to us!");
        QuestionLabel.setFont(new Font("Gotham Rounded", Font.PLAIN, 40));
        QuestionLabel.setForeground(Color.WHITE);
        QuestionLabel.setBounds(218 + 69, 172 + 69, 598, 50);
        add(QuestionLabel);

        QuestionArea = new JTextArea();
        QuestionArea.setFont(new Font("Gotham Rounded", Font.PLAIN, 30));
        QuestionArea.setRows(5);
        QuestionArea.setColumns(20);

        ScrollPane = new JScrollPane(QuestionArea);
        ScrollPane.setBounds(218 + 69, 172 + 69 + 50 + 6, 598, 239);
        add(ScrollPane);
        
        int x = QuestionLabel.getX(); 
        int y = ScrollPane.getY() + ScrollPane.getHeight() + 18; // below textarea + 18px gap

        SubmitButton = new JButton("Submit");
        SubmitButton.setFont(new Font("Gotham Rounded", Font.PLAIN, 28));
        SubmitButton.setForeground(new Color(128, 161, 219)); // #80a1db
        SubmitButton.setFocusPainted(false);
        SubmitButton.setBorder(BorderFactory.createLineBorder(new Color(128, 161, 219), 2)); // Add visible border
        SubmitButton.setContentAreaFilled(true); // Make it opaque (button fills background)
        SubmitButton.setOpaque(true);           
        SubmitButton.setBackground(Color.WHITE);
        SubmitButton.setBounds(x, y, 143, 52);
        SubmitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(SubmitButton);


        // Hover Effect for Submit Button
        Color originalColor = SubmitButton.getForeground();
        Color hoverColor = new Color(0x00, 0x2b, 0xb8);

        SubmitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SubmitButton.setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SubmitButton.setForeground(originalColor);
            }
        });

        // Image
        ImageLabel = new JLabel(new ImageIcon(getClass().getResource("/Resources/Gcash INQUIRE.png")));
        ImageLabel.setBounds(218 + 69 + 598 + 18, 172, 520, 490);
        add(ImageLabel);

        // Line Panel
        LinePanel = new JPanel();
        LinePanel.setBackground(new Color(0, 28, 128)); // #001c80
        LinePanel.setBounds(218, 172 + 490 + 12, 1089, 9);
        add(LinePanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HelpCenter::new);
    }
}
