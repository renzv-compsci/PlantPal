package com.potsko.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LandingPage extends JFrame {
    
    public LandingPage() {

        // Creating the frame of PotsKo
        JFrame frame = new JFrame("PotsKo: A Smart Way to go Green");
        frame.setSize(1200, 780);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

        // Adding PotsKo Icon 
        ImageIcon icon = new ImageIcon("PotsKo\\src\\main\\resources\\images\\UIicons\\PotsKo_Logo.png");
        Image scaledImage = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        frame.setIconImage(scaledImage);
        frame.setVisible(true);

        // Adding dynamic text for the Landing Page using try catch method
        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnnapurnaSIL-Bold.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(128f);

            JLabel label = new JLabel ("PotsKo:");
            label.setForeground(new Color(34, 82, 20));
            label.setFont(customFont);
            frame.add(label);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 1: " + e.getMessage());
        } 
        frame.setVisible(true);

        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnticDidone-Regular.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(90f);

            JLabel label = new JLabel ("A Smart way to GO GREEN");
            label.setForeground(new Color (30, 30, 30));
            label.setFont(customFont);
            frame.add(label);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 2: " + e.getMessage());
        }
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new LandingPage();
    }
}