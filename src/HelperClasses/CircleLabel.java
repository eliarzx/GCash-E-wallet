package HelperClasses;

import javax.swing.*;
import java.awt.*;

public class CircleLabel extends JLabel {

    public CircleLabel(String text) {
        super(text, SwingConstants.CENTER); // center the text
        setPreferredSize(new Dimension(100, 100)); // initial size (circle)
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // Draw the filled circle (background color)
        g2.setColor(getBackground());
        g2.fillOval(x, y, diameter, diameter);

        // Draw the border
        int borderThickness = 10; 
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(Color.WHITE);
        g2.drawOval(x + borderThickness / 2, y + borderThickness / 2, diameter - borderThickness, diameter - borderThickness);

        // Draw the centered text
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2.setFont(getFont());
        g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4);

        g2.dispose();
    }



    @Override
    public boolean contains(int x, int y) {
        // Only return true if the (x,y) is inside the circle
        int radius = Math.min(getWidth(), getHeight()) / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int dx = x - centerX;
        int dy = y - centerY;
        return dx * dx + dy * dy <= radius * radius;
    }
    
    @Override
    public void updateUI() {
        super.updateUI();
        setOpaque(false); // We will manually paint background
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    // Sample/Test DONT RUN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sample");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            CircleLabel label = new CircleLabel("SAMPLE");
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);
            label.setFont(new Font("Arial", Font.BOLD, 16));

            frame.add(label);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
