package com.potsko.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LandingPage extends JFrame {
    
    public LandingPage(MainFrame mainFrame) {

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

        // Setting up JLayered Pane for image design positioning (Allows image overlapping)
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000,2000));
        frame.setContentPane(layeredPane);

        // JPanel for the positions of dynamic text 
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBounds (250, 220, 2000, 2000);

        // Creating JButtons for the login and signup functions 
        JButton login = new JButton("Log In");
        JButton signup = new JButton("Sign Up");

        // Event listeners 
        login.addActionListener(e -> mainFrame.showLogin());
        signup.addActionListener(e -> mainFrame.showSignup());
        
        frame.add(login);
        frame.add(signup);

        // Uses try catch method for debugging purposes (this loads text)
        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnnapurnaSIL-Bold.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(128f);

            // Creating each label for dynamic text layout
            JLabel label1 = new JLabel ("PotsKo:");
            label1.setForeground(new Color(34, 82, 20));
            label1.setFont(customFont);
            label1.setAlignmentX(Component.LEFT_ALIGNMENT);
            textPanel.add(label1);

        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 1: " + e.getMessage());
        } 

        // Uses try catch method for debugging purposes (this loads text)
        try (InputStream fontStream = LandingPage.class.getResourceAsStream("/font/AnticDidone-Regular.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    
            // Creating each label for dynamic text layout
            JLabel label2 = new JLabel ("A Smart way");
            label2.setBorder(BorderFactory.createEmptyBorder(-20, 0, 0, 0)); // negative bottom border
            label2.setForeground(new Color (30, 30, 30));
            label2.setAlignmentX(Component.LEFT_ALIGNMENT);
            label2.setFont(customFont.deriveFont(90f));
            textPanel.add(label2);

            JLabel label3 = new JLabel ("to GO GREEN");
            label3.setBorder(BorderFactory.createEmptyBorder(-10, 0, 0, 0)); // negative top border
            label3.setForeground(new Color (30, 30, 30));
            label3.setAlignmentX(Component.LEFT_ALIGNMENT);
            label3.setFont(customFont.deriveFont(96f));
            textPanel.add(label3);
        
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font 2: " + e.getMessage());
        }  

        // Image overlapping layout using layeredPane
        try {
        ImageIcon leftPlant = new ImageIcon(getClass().getResource("/images/appImg/plant3.png"));
        Image leftPlantScl = leftPlant.getImage().getScaledInstance(695, 695, Image.SCALE_SMOOTH);
        JLabel leftPlantLbl = new JLabel(new ImageIcon(leftPlantScl));
        leftPlantLbl.setBounds(-304, -143, 695, 695);
        layeredPane.add(leftPlantLbl, JLayeredPane.MODAL_LAYER);

        ImageIcon rightPlant = new ImageIcon(getClass().getResource("/images/appImg/plant1.png"));
        Image rightPlantScl = rightPlant.getImage().getScaledInstance(649, 649, Image.SCALE_SMOOTH);
        JLabel rightPlantLbl = new JLabel(new ImageIcon(rightPlantScl));
        rightPlantLbl.setBounds(1470, 565, 649, 649);
        layeredPane.add(rightPlantLbl, JLayeredPane.MODAL_LAYER);
        } catch (Exception e) {
            System.err.println("Failed to load the image: " + e.getMessage());
        }

        layeredPane.add(textPanel, JLayeredPane.PALETTE_LAYER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    public static void main(String[] args) {
        new LandingPage(null);
    }
}