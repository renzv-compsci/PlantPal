package com.potsko.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

// This serves as a placeholder for all of the design buttons that I will create, so that I can just call them on each panels
// It practices usability and efficiency in code performance 

public class LandingButtonUtils {
    
    // Set up all of the final elements for button designs 
    private static final Color GREEN_BORDER = new Color (34, 82, 20);
    private static final LineBorder GREEN_LINE = new LineBorder(GREEN_BORDER, 2, false);
    private static final Color LIGHTER_GREEN = new Color(148, 173, 146);

    private static final Color WHITE_BORDER = Color.WHITE;
    private static final LineBorder WHITE_LINE = new LineBorder(WHITE_BORDER, 2, false);

    // Green button design 
    // Calls the private baseStyle to pass all of the methods
    public static void styleGreenButton(JButton button, Font customFont) {
        baseStyle(button, customFont, GREEN_BORDER, GREEN_LINE, LIGHTER_GREEN);
    }

    // White button design 
    public static void styleWhiteButton(JButton button, Font customFont) {
        baseStyle(button, customFont, Color.WHITE, WHITE_LINE, Color.LIGHT_GRAY);
    }

    // Shared logic for how the buttons should look and react to mouse listeners 
    private static void baseStyle(JButton button, Font font, Color fg, LineBorder border, Color hoverBg) {
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setBorder(border);
        if (font != null) button.setFont(font);

        // States of buttons, when normal, hovered, and clicked
        button.getModel().addChangeListener(e -> {
            ButtonModel model = button.getModel();
            if (model.isPressed() && model.isArmed()) {
                button.setContentAreaFilled(false);
                button.setBackground(hoverBg.darker());
                button.setForeground(Color.WHITE);
            } else if (model.isRollover()) {
                button.setContentAreaFilled(false);
                button.setBackground(hoverBg);
                button.setForeground(Color.WHITE);
            } else {
                button.setContentAreaFilled(false);
                button.setBackground(null);
                button.setForeground(fg);
            }
        });
    }
} 