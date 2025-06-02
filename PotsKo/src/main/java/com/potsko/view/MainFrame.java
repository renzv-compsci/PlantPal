package com.potsko.view;

import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    // Moved the setting up of the frame in main frame, this allows compatibility of cardlayout in all of my panels
    public MainFrame() {
        // Set up the frame
        setTitle("PotsKo: A Smart Way to go Green");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set window icon
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/UIicons/PotsKo_Logo.png"));
            Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); // 64x64 is a good icon size
            setIconImage(scaledImage);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }

        // setting up cardlayout for dynamic switching of pages 
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LandingPage landingPage = new LandingPage(this);
        LoginPanel loginPanel = new LoginPanel(this);
        SignupPanel signupPanel = new SignupPanel(this);

        cardPanel.add(landingPage, "landing");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(signupPanel, "signup");

        setContentPane(cardPanel);
        setVisible(true);
    }

    public void showLogin() {
        cardLayout.show(cardPanel, "login");
    }

    public void showSignup() {
        cardLayout.show(cardPanel, "signup");
    }

    public void showLanding() {
        cardLayout.show(cardPanel, "landing");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}