package com.potsko.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.potsko.utils.ButtonUtils;
import com.potsko.utils.FontUtils;
import com.potsko.utils.RoundedPasswordField;
import com.potsko.utils.RoundedTextField;

public class LoginPanel extends JLayeredPane{
    public LoginPanel(MainFrame mainFrame) {
        setOpaque(true);
        setPreferredSize(new Dimension(1200, 780));

        // Custom desinging the login panel (green & white)
        // Blank canvs for easy customization and designing (Allows easy image overlapping, and other design aspects)
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int w = getWidth(), h = getHeight();

                // left green
                g.setColor(new Color(81, 103, 78));
                g.fillRect(0, 0, w/2, h);

                // right white
                g.setColor(Color.WHITE);
                g.fillRect(w/2, 0, w - w/2, h);
            }
        };

        loginPanel.setOpaque(true);
        loginPanel.setBounds(0, 0, 1200, 780);
        add(loginPanel, JLayeredPane.DEFAULT_LAYER);

        // Setting up the panel for the application of colors 
        JPanel greenPanel = new JPanel();
        greenPanel.setOpaque(false);
        greenPanel.setLayout(new GridBagLayout()); // New layout for dynamic positioning
        greenPanel.setBounds(640, 0, 640, 720); // left half size and position
        // This is where you add left panel components (below)

        JPanel textPanel1 = new JPanel();
        textPanel1.setOpaque(false);
        textPanel1.setLayout(new BoxLayout(textPanel1, BoxLayout.Y_AXIS));
        textPanel1.setBounds(640, 0, 640, 140);

        Font customFont1 = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 75f);
        JLabel textLabel1 = new JLabel("Log In");
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setFont(customFont1);

        // Sets GridBag constraints for the Log In title
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets (0, 0, 850, 0);

        // Panel form box 
        JPanel formPanelWhite = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, 700, 850, 30, 30);
            }
        };

        formPanelWhite.setOpaque(false);
        formPanelWhite.setPreferredSize(new Dimension(700, 850));
        formPanelWhite.setLayout(new GridBagLayout());

        // Sets GridBag constraints for the form box 
        GridBagConstraints fpW = new GridBagConstraints();
        fpW.gridx = 0;
        fpW.gridy = 0; 
        fpW.weightx = 1.0;
        fpW.weighty = 1.0;
        fpW.insets = new Insets(125, 15, 20, 15);
        fpW.anchor = GridBagConstraints.CENTER;

        // Sets GridBag constraints for the log in forms 
        GridBagConstraints lf = new GridBagConstraints();
        lf.gridx = 0;
        lf.weightx = 1.0;
        lf.weighty = 0.0;
        lf.fill = GridBagConstraints.HORIZONTAL;
        lf.anchor = GridBagConstraints.NORTHWEST;

        // Setting up and designing the login forms 

        // Email 
        lf.gridy = 0;
        lf.insets = new Insets(80, 20, 10, 20);
        JLabel userEmail = new JLabel("Email");
        userEmail.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userEmail,lf);

        lf.gridy++; // increment the row number to 1 
        lf.insets = new Insets(5,20, 5, 20);
        formPanelWhite.add(new RoundedTextField(40), lf);

        // Password 
        lf.gridy++;
        lf.insets = new Insets(50, 20, 10, 20);
        JLabel userPass = new JLabel("Password");
        userPass.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userPass,lf);

        lf.gridy++; // increment the row number to 2 
        lf.insets = new Insets (5, 20, 5, 20);
        formPanelWhite.add(new RoundedPasswordField(40), lf);

        // Log In button 
        lf.gridy++;
        JButton loginButton = new JButton("Log In");
        Font loginButtonfnt = FontUtils.getFont("Poppins-Regular.ttf", 30f);
        ButtonUtils.styleGreenButton(loginButton, loginButtonfnt);
        loginButton.addActionListener(e -> mainFrame.showHome());
        lf.insets = new Insets(250, 20, 15, 20);
        formPanelWhite.add(loginButton, lf);

        // Setting up Sign up text clickable label
        lf.gridy++; 
        lf.insets = new Insets (15, 50, 0, 50);
        JLabel signupLabel = new JLabel ( "<html>"
        + "<div style='text-align:center;'>"
        + "<span style='Arial, sans-serif; font-size:15pt; color:rgb(81,103,78);'>"
        + "Don't have an account? "
        + "<a href='' style='color:#51774e; text-decoration:underline; font-weight:bold;'>Sign Up</a>"
        + "</span>"
        + "</div>"
    + "</html>");
        signupLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mainFrame.showSignup();
            }
        });

        // Position 
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanelWhite.add(signupLabel, lf);

        // This moves every component above 
        lf.gridy++; 
        lf.weighty = 1.0;
        lf.fill = GridBagConstraints.BOTH;
        formPanelWhite.add(Box.createVerticalGlue(), lf);

        // Seting up the panel for the application of colors 
        JPanel whitePanel = new JPanel();
        whitePanel.setOpaque(false);
        whitePanel.setLayout(null);
        whitePanel.setBounds(640, 0, 640, 720);

        // Text panel my displayed text on whitePanel, this is to avoid the disruption of absolute positioning
        JPanel textPanel2 = new JPanel();
        textPanel2.setOpaque(false);
        textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.Y_AXIS));
        textPanel2.setBounds(140, 265, 2000, 2000);

        // This is where you add right panel components
        // This loads the image overlapping using JLayeredPane
        try {
            ImageIcon rightPlant = new ImageIcon(getClass().getResource("/images/appImg/plant1.png"));
            Image rightPlantScl = rightPlant.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            JLabel rightPlantLbl = new JLabel(new ImageIcon(rightPlantScl));
            rightPlantLbl.setBounds(1400, 460, 800, 800);
            add(rightPlantLbl, JLayeredPane.PALETTE_LAYER);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }
    
        Font customFont2 = FontUtils.getFont("AnticDidone-Regular.ttf", 90f);
        Font customFont3 = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 110);
        
        JLabel textLabel2 = new JLabel("Welcome Back");
        textLabel2.setForeground(Color.BLACK);
        textLabel2.setFont(customFont2);
        textLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel2.setBorder(new EmptyBorder(0, 900, 0, 0));

        JLabel textLabel3 = new JLabel("to");
        textLabel3.setForeground(Color.BLACK);
        textLabel3.setFont(customFont2);
        textLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel3.setBorder(new EmptyBorder(0, 910, 0, 0));

        JLabel textLabel4 = new JLabel("PotsKo");
        textLabel4.setForeground(new Color(34, 82, 20));
        textLabel4.setFont(customFont3);
        textLabel4.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel4.setBorder(new EmptyBorder(0, 1030, 0, 0));

        // Adding panels inside LayeredPane
        add(greenPanel, JLayeredPane.MODAL_LAYER);
        add(textPanel1, JLayeredPane.MODAL_LAYER);

        add(whitePanel, JLayeredPane.MODAL_LAYER);
        add(textPanel2, JLayeredPane.MODAL_LAYER);

        // Adding text inside respective panels
        textPanel2.add(textLabel2);
        textPanel2.add(Box.createVerticalStrut(10));
        textPanel2.add(textLabel3);
        textPanel2.add(Box.createVerticalStrut(-123)); 
        textPanel2.add(textLabel4);

        // Adding elements label / panels using GridBag
        greenPanel.add(formPanelWhite, fpW);
        greenPanel.add(textLabel1, gbc);
        
        // Makes the log in panel fill up the whole window
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                loginPanel.setBounds(0, 0, size.width, size.height);
                greenPanel.setBounds(0, 0, size.width / 2, size.height);
                whitePanel.setBounds(size.width / 2, 0, size.width - size.width / 2, size.height);
            }
        });
    }
}