package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.potsko.db.UserDAO;
import com.potsko.model.User;
import com.potsko.utils.FontUtils;

public class ProfilePanel extends JPanel {

    private NavBarPanel navBar;
    private String profilePicPath = null;

    public ProfilePanel(MainFrame mainFrame, int userId) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 246, 240));

        // Load custom fonts
        Font headerFont = FontUtils.getFont("Poppins-Regular.ttf", 32f);
        Font labelFont = FontUtils.getFont("Poppins-Regular.ttf", 18f);

        // NavBar
        navBar = new NavBarPanel(e -> {
            JButton src = (JButton) e.getSource();
            navBar.setActiveNavButton(src);

            if (src == navBar.getHomeButton()) mainFrame.showHome();
            if (src == navBar.getDashboardButton()) mainFrame.showDashboard();
            if (src == navBar.getJournalButton()) mainFrame.showJournal();
            if (src == navBar.getAvaiplantsButton()) mainFrame.showAvailablePlants();
            if (src == navBar.getProfileButton()) mainFrame.showProfile();
        });
        navBar.setActiveNavButton(navBar.getProfileButton());
        add(navBar, BorderLayout.WEST);

        // Profile content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 0));

        // Header
        User user = UserDAO.getUserById(userId);
        JLabel headerLabel = new JLabel(
            (user != null && user.getUsername() != null && !user.getUsername().isEmpty())
                ? user.getUsername() + "’s Profile"
                : "Profile"
        );
        headerLabel.setFont(headerFont.deriveFont(Font.BOLD, 32f));
        headerLabel.setForeground(new Color(81, 103, 78));
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dateLabel = new JLabel("📅 " + java.time.LocalDate.now());
        dateLabel.setFont(labelFont.deriveFont(Font.PLAIN, 16f));
        dateLabel.setForeground(new Color(80, 80, 80));
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(headerLabel);
        contentPanel.add(Box.createVerticalStrut(4));
        contentPanel.add(dateLabel);
        contentPanel.add(Box.createVerticalStrut(24));

        // Profile Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 227, 200, 180), 2, true),
            BorderFactory.createEmptyBorder(24, 32, 24, 32)
        ));
        cardPanel.setMaximumSize(new Dimension(400, 400));
        cardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Profile Picture Button (circle)
        int picSize = 90;
        JButton picButton = new JButton();
        picButton.setPreferredSize(new Dimension(picSize, picSize));
        picButton.setMaximumSize(new Dimension(picSize, picSize));
        picButton.setBorderPainted(false);
        picButton.setContentAreaFilled(false);
        picButton.setFocusPainted(false);

        profilePicPath = (user != null && user.getProfilePicPath() != null) ? user.getProfilePicPath() : null;
        setProfilePicOnButton(picButton, profilePicPath, picSize);

        picButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                profilePicPath = selectedFile.getAbsolutePath();
                setProfilePicOnButton(picButton, profilePicPath, picSize);
            }
        });

        JPanel picPanel = new JPanel();
        picPanel.setOpaque(false);
        picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.X_AXIS));
        picPanel.add(Box.createHorizontalGlue());
        picPanel.add(picButton);
        picPanel.add(Box.createHorizontalGlue());
        cardPanel.add(picPanel);
        cardPanel.add(Box.createVerticalStrut(18));

        // Editable fields
        int infoGap = 24, fieldWidth = 220, fieldHeight = 28;

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField(user != null ? user.getFullName() : "");
        nameField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel usernameLabel = new JLabel("User Name");
        usernameLabel.setFont(labelFont);
        JTextField usernameField = new JTextField(user != null ? user.getUsername() : "");
        usernameField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField(user != null ? user.getEmail() : "");
        emailField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel locationLabel = new JLabel("Location");
        locationLabel.setFont(labelFont);
        JTextField locationField = new JTextField(user != null ? user.getLocation() : "");
        locationField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(labelFont);
        JTextField phoneField = new JTextField(user != null ? user.getPhone() : "");
        phoneField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        // Save button
        JButton saveBtn = new JButton("Save");
        saveBtn.setPreferredSize(new Dimension(100, 28));
        saveBtn.addActionListener(e -> {
            if (user != null) {
                user.setFullName(nameField.getText());
                user.setUsername(usernameField.getText());
                user.setEmail(emailField.getText());
                user.setLocation(locationField.getText());
                user.setPhone(phoneField.getText());
                user.setProfilePicPath(profilePicPath);
                boolean updated = UserDAO.updateProfileInfo(user);
                JOptionPane.showMessageDialog(this, updated ? "Profile updated!" : "Update failed.");
            } else {
                JOptionPane.showMessageDialog(this, "User not loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to card panel
        cardPanel.add(nameLabel); cardPanel.add(nameField);
        cardPanel.add(usernameLabel); cardPanel.add(usernameField);
        cardPanel.add(emailLabel); cardPanel.add(emailField);
        cardPanel.add(locationLabel); cardPanel.add(locationField);
        cardPanel.add(phoneLabel); cardPanel.add(phoneField);
        cardPanel.add(Box.createVerticalStrut(12));
        cardPanel.add(saveBtn);

        // Add everything to contentPanel
        contentPanel.add(cardPanel);
        contentPanel.add(Box.createVerticalGlue());

        // Add contentPanel to the main panel
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setProfilePicOnButton(JButton button, String path, int size) {
        if (path != null && new File(path).exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } else {
            // Draw a colored circle if no image
            button.setIcon(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    g.setColor(new Color(172, 186, 143));
                    g.fillOval(x, y, size, size);
                }
                @Override public int getIconWidth() { return size; }
                @Override public int getIconHeight() { return size; }
            });
        }
    }
}