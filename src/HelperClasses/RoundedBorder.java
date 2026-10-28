/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import java.awt.*;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new java.awt.Color(217, 217, 217));
        g2.setStroke(new BasicStroke(4)); // <- 4px thickness
        g2.drawRoundRect(x + 2, y + 2, width - 5, height - 5, radius, radius); // Adjust position for thickness
        g2.dispose();
    }

}
