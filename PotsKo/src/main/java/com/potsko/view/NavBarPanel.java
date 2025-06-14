package com.potsko.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.potsko.utils.FontUtils;
import com.potsko.utils.ImageUtils;
import com.potsko.utils.NavButtonUtils;
import com.potsko.utils.PotsKoConstants;

public final class NavBarPanel extends JPanel {

    // Final initialization of each buttons 
    private final JButton homeButton;
    private final JButton dashboardButton;
    private final JButton journalButton;
    private final JButton avaiPlantsButton;
    private final JButton profileButton;

    private final JButton[] navButtons;

    public NavBarPanel(ActionListener navListener) {
        
        // sets the design of the navbag bg
        setPreferredSize(new Dimension(PotsKoConstants.SIDE_NAV_WIDTH, 780));
        setBackground(PotsKoConstants.COLOR_SIDE_NAV_BG);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 0));

        // PotsKo Panel
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        logoPanel.setOpaque(false);
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // logo
        JLabel logo = new JLabel();
        ImageIcon logoIcon = ImageUtils.scaledIcon("/images/UIicons/PotsKo_Logo.png", 50, 50);
        logo.setIcon(logoIcon);

        // PotsKo Text
        JLabel title = new JLabel("PotsKo");
        Font customFont = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 40f);
        title.setFont(customFont);
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        add(Box.createVerticalStrut(70));
        logoPanel.add(logo);
        logoPanel.add(title);
        
        add(logoPanel);
        add(Box.createVerticalStrut(60));

        // Create the buttons here
        homeButton = createNavButton("Home", "/images/UIicons/home.png", navListener);
        dashboardButton = createNavButton("Dashboard", "/images/UIicons/dashboard.png", navListener);
        journalButton = createNavButton("Journal", "/images/UIicons/news.png", navListener);
        avaiPlantsButton = createNavButton("Available Plants", "/images/UIicons/potted-plant.png", navListener);
        profileButton = createNavButton("Profile", "/images/UIicons/user.png", navListener);
        
        navButtons = new JButton[] {homeButton, dashboardButton, journalButton, avaiPlantsButton, profileButton}; // Stores them in a array

        // Add the buttons to the panel with respectable spacing
        for (int i = 0; i < navButtons.length; i++) {
            JButton btn = navButtons[i];
             btn.setAlignmentX(Component.LEFT_ALIGNMENT); // Align button itself left in panel
            btn.setHorizontalAlignment(SwingConstants.LEFT); // Align icon/text inside button left
            btn.setHorizontalTextPosition(SwingConstants.RIGHT); // Icon left of text
            btn.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0)); // Add left padding inside pill
            add(btn);
            if (i < navButtons.length -1) {
                add(Box.createVerticalStrut(60));
            } 
        }
        add(Box.createVerticalGlue());

        setActiveNavButton(homeButton); // Active by default
    }

    private JButton createNavButton (String text, String iconPath, ActionListener navListener) {
        
        // Designing all of the buttons inside the array
        JButton btn = new JButton(text);

        // set icon besides button using imageUtils
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = ImageUtils.scaledIcon(iconPath, 25, 25);
            if (icon != null) {
                btn.setIcon(icon);
                btn.setIconTextGap(15);
            }

        }

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(PotsKoConstants.SIDE_NAV_WIDTH - 40, 48));

        // style the button using navbutton utils
        NavButtonUtils.styleNavButton(btn, PotsKoConstants.COLOR_SIDE_NAV_BG, Color.WHITE, 50, false);
        btn.addActionListener(navListener);
        return btn;
    }

    // Calls this function to update which button is active
    public void setActiveNavButton(JButton activeButton) {
        for (JButton btn : navButtons) {

            boolean isActive = (btn == activeButton);
            NavButtonUtils.setActive(btn, isActive);
        }
    }

    // Getters for parent panels
    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getJournalButton() {
        return journalButton;
    }

    public JButton getAvaiplantsButton() {
        return avaiPlantsButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

}