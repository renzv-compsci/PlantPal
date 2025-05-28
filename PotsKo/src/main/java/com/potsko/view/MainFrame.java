package com.potsko.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    // Setting up the mainframe view for login, signin, and landing (this will create a dynamic switching of each page)
    public MainFrame() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LandingPage landingPage = new LandingPage(this);
        LoginPanel loginPanel = new LoginPanel(this);
        SignupPanel signupPanel = new SignupPanel(this);

        cardPanel.add(landingPage, "landing");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(signupPanel, "signup");

        setContentPane(cardPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 780);
        setLocationRelativeTo(null);
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
}