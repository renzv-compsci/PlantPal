package com.potsko.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel{
    public LoginPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Log In Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(e -> mainFrame.showLanding());
        add(back, BorderLayout.SOUTH);
        
    }
}
