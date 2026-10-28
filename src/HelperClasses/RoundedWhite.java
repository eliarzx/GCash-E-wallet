package HelperClasses;

import java.awt.*;
import javax.swing.border.Border;

public class RoundedWhite implements Border {

    private int radius;

    public RoundedWhite(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.decode("#3478F2"));  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
