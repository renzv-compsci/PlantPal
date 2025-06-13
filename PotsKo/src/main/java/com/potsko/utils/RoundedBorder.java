package com.potsko.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;


// A helper class which creates a custom border (rounder corders) for active buttons 
public class RoundedBorder implements Border {
    
    private final int radius;
    private Color color = new Color(81, 103, 78);

    public RoundedBorder(Color color, int radius) {
        
        this.radius = radius;
        this.color = color;

    }
    // Override methods ensures that each method is from the Border interface
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius / 2, this.radius / 2, this.radius / 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    // This create the background borders
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g. create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.color); 
        g2d.fillRoundRect(x, y, width -1, height - 1, radius, radius);
        g2d.dispose();
    }
}
