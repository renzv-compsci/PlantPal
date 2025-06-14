package com.potsko.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

// Button utils for nav side bar
// Adds a hover effect to the button (shows on hover, removes on exit)
public class NavButtonUtils {

    public static void styleNavButton(JButton navButton, Color fillColor, Color borderColor, int radius, boolean isActive) {
        
        navButton.setOpaque(false);
        navButton.setContentAreaFilled(false);
        navButton.setFocusPainted(false);
        navButton.setBorderPainted(false);
        navButton.setForeground(isActive ? Color.WHITE : Color.BLACK);

        Font customFont = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 26f);
        navButton.setFont(customFont.deriveFont(isActive ? Font.BOLD : Font.PLAIN));

        // Custom UI Paints
        ButtonPillUI ui = new ButtonPillUI(fillColor, radius);
        navButton.setUI(ui);

        // Mouse hover effect
        for (MouseAdapter ma : navButton.getListeners(MouseAdapter.class)) {
            navButton.removeMouseListener(ma);
        }
        navButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e) {
                navButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                navButton.repaint();
            }
        });
    }

    // set the active state button 
    public static void setActive(JButton navButton, boolean isActive) {
        Color fillColor = isActive
            ? PotsKoConstants.COLOR_ACTIVE_NAV_BG
            : PotsKoConstants.COLOR_SIDE_NAV_BG;
        styleNavButton(navButton, fillColor, fillColor, 36, isActive);
    }

    private static class ButtonPillUI extends BasicButtonUI {
        
        private final Color fillColor;
        private final int radius;

        public ButtonPillUI(Color fillColor, int radius) {
            
            this.fillColor = fillColor;
            this.radius = radius;
        }

        @Override 
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = b.getWidth();
            int h = b.getHeight();

            // Fills the background
            g2.setColor(fillColor);
            g2.fillRoundRect(0, 0, w, h, radius, radius);

            g2.dispose();
            super.paint(g, c);
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            Dimension d = super.getPreferredSize(c);
            int pad = radius / 2;
            d.width += pad * 2;
            d.height = Math.max(d.height, radius);
            return d;
        }
    }
}