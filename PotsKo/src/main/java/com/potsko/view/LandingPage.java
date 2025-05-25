package com.potsko.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        // Uses try catch method for debugging purposes (this loads text)
        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnnapurnaSIL-Bold.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(128f);

            // Creating each label for dynamic text layout
            JLabel label = new JLabel ("PotsKo:");
            label.setForeground(new Color(34, 82, 20));
            label.setBorder(BorderFactory.createEmptyBorder(144, 227, 0, 0));
            label.setFont(customFont);
            frame.add(label);

        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 1: " + e.getMessage());
        } 
        frame.pack();
        frame.setVisible(true);

        // Uses try catch method for debugging purposes (this loads text)
        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnticDidone-Regular.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    
            // Creating each label for dynamic text layout
            JLabel label = new JLabel ("A Smart way");
            label.setForeground(new Color (30, 30, 30));
            label.setBorder(BorderFactory.createEmptyBorder(279, 227, 0, 0));
            label.setFont(customFont.deriveFont(90f));

            JLabel label1 = new JLabel ("to GO GREEN");
            label1.setForeground(new Color (30, 30, 30));
            label1.setBorder(BorderFactory.createEmptyBorder(0, 227, 376, 0));
            label1.setFont(customFont.deriveFont(96f));

            // Let stack multiple labels for dynamic layout
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(label);
            panel.add(label1);
            frame.add(panel);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 2: " + e.getMessage());
        }
        frame.setVisible(true);

        // try catch method for debugging purposes (this loads images)
        try {
            ImageIcon img1 = new ImageIcon(getClass().getResource("/images/appImg/plant1.png"));
            ImageIcon img2 = new ImageIcon(getClass().getResource("/images/appImg/plant3.png"));
            
            // Setting image size 
            Image scaledImg1 = img1.getImage().getScaledInstance(649, 649, Image.SCALE_SMOOTH);
            Image scaledImg2 = img2.getImage().getScaledInstance(695, 695, Image.SCALE_SMOOTH);

            JLabel label1 = new JLabel(new ImageIcon(scaledImg1));
            JLabel label2 = new JLabel(new ImageIcon(scaledImg2));
            
            
            JPanel panel = new JPanel();
            panel.add(label1);
            panel.add(label2);

            frame.add(panel);
            frame.pack();
        } catch (Exception e) {
            System.err.println("Failed to load the image: " + e.getMessage());
        }
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new LandingPage();
    }
}
