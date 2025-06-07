package com.potsko.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {

    // this sets the design for the text field 
    // Initilized it for personalize and customizable UI
    Color labelBg = new Color(81, 103, 78); //color for the label 

    public RoundedTextField(int size) {
        super(size);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 20f));
        setForeground(Color.WHITE);
    }

    // This controls how to component should be rendered 
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(labelBg);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enables smoother and rounded corners 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }

}
