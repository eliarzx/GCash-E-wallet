
package System;
    
import javax.swing.*;
import java.awt.*;

public class ServicesC extends JFrame {

    public ServicesC() {
        setTitle("Our Services");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(0xfb, 0xeb, 0xe6));

        // Add Navigation Bar
        NavigationBar navbar = new NavigationBar();
        navbar.setBounds(0, 0, getWidth(), 100);
        navbar.setBackground(Color.WHITE);
        navbar.ServicesButton.setForeground(new Color(0x00, 0x2b, 0xb8));
        navbar.ServicesButton.setBackground(Color.WHITE);
        navbar.ServicesButton.setOpaque(true);
        navbar.ServicesButton.setBorderPainted(false);
        getContentPane().add(navbar);

        // Dynamic navbar width on resize
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                navbar.setSize(getWidth(), 100);
            }
        });

        // Heading
        JLabel heading1 = new JLabel("Borrow Money");
        heading1.setFont(new Font("Gotham Rounded", Font.PLAIN, 54));
        heading1.setForeground(new Color(137, 113, 107));
        heading1.setHorizontalAlignment(SwingConstants.RIGHT);
        heading1.setBounds(980, 150, 500, 60);
        getContentPane().add(heading1);

        // Subheading
        JLabel subheading1 = new JLabel("<html>Reliable credit & loan solutions<br>only from GCash</html>");
        subheading1.setFont(new Font("Gotham Rounded", Font.PLAIN, 38));
        subheading1.setForeground(new Color(95, 39, 28));
        subheading1.setHorizontalAlignment(SwingConstants.RIGHT);
        subheading1.setBounds(880, 230, 600, 100);
        getContentPane().add(subheading1);

        // Text1 - "Enjoy"
        JLabel text1 = new JLabel("Enjoy");
        text1.setFont(new Font("Gotham Rounded", Font.PLAIN, 41));
        text1.setForeground(new Color(95, 39, 28));
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setBounds(270, 430, 400, 50);
        getContentPane().add(text1);

        // jLabel4 - Lifestyle message
        JLabel lifestyleMsg = new JLabel("<html>Whatever your lifestyle,<br>GCash got you covered</html>");
        lifestyleMsg.setFont(new Font("Gotham Rounded", Font.PLAIN, 52));
        lifestyleMsg.setForeground(new Color(95, 39, 28));
        lifestyleMsg.setHorizontalAlignment(SwingConstants.CENTER);
        lifestyleMsg.setBounds(100, 470, 800, 150);
        getContentPane().add(lifestyleMsg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ServicesC().setVisible(true);
        });
    }
}
