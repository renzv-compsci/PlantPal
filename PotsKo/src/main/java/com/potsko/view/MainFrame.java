package com.potsko.view;


import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.potsko.db.UserDAO;

public class MainFrame extends JFrame {
    
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private int currentUserId = -1; // Default to -1 (not logged in)

    // Moved the setting up of the frame in main frame, this allows compatibility of cardlayout in all of my panels
    public MainFrame() {
        // Set up the frame
        setTitle("PotsKo: A Smart Way to go Green");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        UserDAO.printAllUsers(); // This will print all users to the console
        // Uncomment the line above if UserDAO and printAllUsers() exist and are correctly imported.

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

        // Add initial panels with placeholder userId (-1)
        cardPanel.add(new LandingPage(this), "landing");
        cardPanel.add(new LoginPanel(this), "login");
        cardPanel.add(new SignupPanel(this), "signup");
        cardPanel.add(new HomePanel(this, currentUserId), "home");
        cardPanel.add(new DashboardPanel(this, currentUserId), "dashboard");
        cardPanel.add(new JournalPanel(this, currentUserId), "journal");
        cardPanel.add(new AvailablePlantsPanel(this, currentUserId), "available");
        cardPanel.add(new ProfilePanel(this, currentUserId), "profile");

        setContentPane(cardPanel);
        setVisible(true);
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    public int getCurrentUserId() {
        return currentUserId;
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

    public void showHome() {
        setContentPane(new HomePanel(this, currentUserId));
        revalidate();
        repaint();
    }

    public void showDashboard() {
        setContentPane(new DashboardPanel(this, currentUserId));
        revalidate();
        repaint();
    }

    public void showJournal() {
        setContentPane(new JournalPanel(this, currentUserId));
        revalidate();
        repaint();
    }

    public void showAvailablePlants() {
        setContentPane(new AvailablePlantsPanel(this, currentUserId));
        revalidate();
        repaint();
    }

    public void showProfile() {
        setContentPane(new ProfilePanel(this, currentUserId));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}