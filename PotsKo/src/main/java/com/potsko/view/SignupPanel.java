package com.potsko.view;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.potsko.db.UserDAO;
import com.potsko.utils.LandingButtonUtils;
import com.potsko.utils.FontUtils;
import com.potsko.utils.RoundedPasswordField;
import com.potsko.utils.RoundedTextField;

public class SignupPanel extends JLayeredPane{ // Extends on JLayered Pane for absolute positioning 
    public SignupPanel(MainFrame mainFrame) {
        setOpaque(true);
        setPreferredSize(new Dimension(1200, 780));
        
        // Custom Designing the signup panel (green & white)
        // Blank canvas for easy customization and designing (This allows easy image overlapping, and other design aspects)
        JPanel signupPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int w = getWidth(), h = getHeight();

                // left white
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, w/2, h);

                // right green
                g.setColor(new Color(81, 103, 78));
                g.fillRect(w/2, 0, w - w /2, h);
            }
        };
        signupPanel.setOpaque(true);
        signupPanel.setBounds(0, 0, 1200, 780);
        add(signupPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Setting up the panel for the application of colors
        JPanel whitePanel = new JPanel();
        whitePanel.setOpaque(false);
        whitePanel.setLayout(null); // For absolute positioning 
        whitePanel.setBounds(0, 0, 640, 720); // left half size and position

        // Text panel my displayed text on whitePanel, this is to avoid the disruption of absolute positioning of my colored panels
        JPanel textPanel1 = new JPanel();
        textPanel1.setOpaque(false);
        textPanel1.setLayout(new BoxLayout(textPanel1, BoxLayout.Y_AXIS));
        textPanel1.setBounds(140, 265, 2000, 2000);

        // This is where you add left panel components (below)
        // This loads the image overlapping using JLayeredPane
        try {
            ImageIcon leftPlant = new ImageIcon(getClass().getResource("/images/appImg/plant3.png"));
            Image leftPlantScl = leftPlant.getImage().getScaledInstance(695, 695, Image.SCALE_SMOOTH);
            JLabel leftPlantLbl = new JLabel(new ImageIcon(leftPlantScl));
            leftPlantLbl.setBounds(-304, -143, 695, 695);
            add(leftPlantLbl, JLayeredPane.PALETTE_LAYER);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }

        Font customFont1 = FontUtils.getFont("AnticDidone-Regular.ttf", 75f);
    
        // Creating each label for each dynamic text layout
        JLabel textLabel1 = new JLabel("Already have");
        textLabel1.setForeground(Color.BLACK);
        textLabel1.setFont(customFont1);
        textLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel1.setBorder(new EmptyBorder(40, 45, 0, 0)); 

        JLabel textLabel2 = new JLabel("an account?");
        textLabel2.setForeground(Color.BLACK);
        textLabel2.setFont(customFont1);
        textLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel2.setBorder(new EmptyBorder(0, 45, 0, 0));
        
        // Setting up the login button 
        JButton loginButton = new JButton("Log In");
        Font btnFont = FontUtils.getFont("Poppins-Regular.ttf", 20f);
        LandingButtonUtils.styleGreenButton(loginButton, btnFont);
        loginButton.addActionListener(e -> mainFrame.showLogin());

        // Preffered size for the log in button
        loginButton.setPreferredSize(new Dimension(109, 37));
        loginButton.setMaximumSize(new Dimension(109, 37));

        // Manual Positioning of the button 
        loginButton.setBounds(185, 500, 109, 37);

        JPanel greenPanel = new JPanel();
        greenPanel.setOpaque(false);
        greenPanel.setLayout(new GridBagLayout()); // New layout for dynamic positioning 
        greenPanel.setBounds(640, 0, 640, 720); // right half size and position 
        // This is where you add right panel components (below)

        // Text panel my displayed text on whitePanel, this is to avoid the disruption of absolute positioning of my colored panels
        JPanel textPanel2 = new JPanel();
        textPanel2.setOpaque(false);
        textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.Y_AXIS));
        textPanel2.setBounds(640, 0, 640, 140); 

        Font customFont2 = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 75f);

        JLabel textLabel3 = new JLabel("Sign Up");
        textLabel3.setForeground(Color.WHITE);
        textLabel3.setFont(customFont2);

        // Sets GridBag constraints for the Sign Up title 
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
        fpW.insets = new Insets (125, 0, 20, 0);
        fpW.anchor = GridBagConstraints.CENTER;

        // Sets GridBag constraints for the sign up forms 
        GridBagConstraints sf = new GridBagConstraints();
        sf.gridx = 0;
        sf.insets = new Insets(20,15,30,15);
        sf.fill = GridBagConstraints.HORIZONTAL; // Sets all of the sign up forms horizontally 

        // Designing the signup forms 

        // Declare input fields for database connection 
        RoundedTextField userNamefield = new RoundedTextField(40);
        RoundedTextField userEmailfield = new RoundedTextField(40);
        RoundedPasswordField userPassfield = new RoundedPasswordField(40);
        RoundedPasswordField finalPassfield = new RoundedPasswordField(40);

        // Full Name
        sf.gridy = 0;
        sf.insets = new Insets(0, 0, 5, 0);
        JLabel userName = new JLabel("Full Name:");
        userName.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userName, sf);

        sf.gridy++; //increment the rown number to 1
        sf.insets = new Insets(20, 0, 30, 0); 
        formPanelWhite.add(userNamefield, sf); 

        // Email
        sf.gridy ++; //increment the row number to 2 
        sf.insets = new Insets(0, 0, 5, 0);
        JLabel userEmail = new JLabel("Email");
        userEmail.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userEmail,sf);

        sf.gridy++; //increment the row number to 3 
        sf.insets = new Insets(20, 0, 30, 0); 
        formPanelWhite.add(userEmailfield, sf);

        // Password
        sf.gridy++; //increment the row number to 4
        sf.insets = new Insets(0, 0, 5, 0);
        JLabel userPass = new JLabel("Password:");
        userPass.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userPass,sf);

        sf.gridy++; //increment the row number to 5 
        sf.insets = new Insets(20, 0, 30, 0); 
        formPanelWhite.add(userPassfield, sf);

        // Confirm Password
        sf.gridy++; //increment the row number to 6 
        sf.insets = new Insets(0, 0, 5, 0);
        JLabel userFinalPass = new JLabel("Confirm Password:");
        userFinalPass.setFont(FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 30f));
        formPanelWhite.add(userFinalPass,sf);

        sf.gridy++; //increment the row number to 7
        sf.insets = new Insets(20, 0, 30, 0); 
        formPanelWhite.add(finalPassfield ,sf);

        // Sign Up button
        sf.gridy++;
        JButton signupButton = new JButton("Sign Up");
        Font signupButtonfnt = FontUtils.getFont("Poppins-Regular.ttf", 30f);
        LandingButtonUtils.styleGreenButton(signupButton, signupButtonfnt);


        // DB connection logic for the signup Panel
        signupButton.addActionListener(e -> {
            String username = userNamefield.getText();
            String email = userEmailfield.getText();
            String password = new String(userPassfield.getPassword());
            String confirmPassword = new String(finalPassfield.getPassword());
        
            // Conditionals for each user inputs

            if (!password.equals(confirmPassword)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Password do not match", "Input Error", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            else if(username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Username, Email, and Password can not be empty", "Input Error", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            int newUserId = UserDAO.userRegister(username, email, password);
            if (newUserId != -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Sign Up successful! Welcome to PotsKo");
                mainFrame.setCurrentUserId(newUserId); // <-- set the current user
                mainFrame.showHome(); // or showDashboard(), etc.
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Sign up failed. Try different email or username");
            }
        });

        sf.gridy++;
        formPanelWhite.add(signupButton, sf);
        
        // Adding panels inside LayeredPane 
        add(whitePanel, JLayeredPane.MODAL_LAYER);
        add(textPanel1, JLayeredPane.MODAL_LAYER);

        add(greenPanel, JLayeredPane.MODAL_LAYER);
        add(textPanel2, JLayeredPane.MODAL_LAYER);

        // Adding buttons in whitePanel
        whitePanel.add(loginButton);

        // Addind text inside respective panels 
        textPanel1.add(textLabel1);    
        textPanel1.add(textLabel2);

        // Adding elements label / panels using GridBag
        greenPanel.add(textLabel3, gbc);
        greenPanel.add(formPanelWhite,fpW);

        // Makes the sign up panel fill up the whole window
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                signupPanel.setBounds(0, 0, size.width, size.height);
                whitePanel.setBounds(0, 0, size.width / 2, size.height);
                greenPanel.setBounds(size.width / 2, 0, size.width - size.width / 2, size.height);
            }
        });
    }
}