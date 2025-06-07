package com.potsko.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel{

    public HomePanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Home Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(e -> mainFrame.showSignup());
        add(back, BorderLayout.SOUTH);
        
    }
}
