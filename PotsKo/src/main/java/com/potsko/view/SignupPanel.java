package com.potsko.view;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SignupPanel extends JPanel{
    public SignupPanel(MainFrame mainFrame) {
        
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Signup Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(e -> mainFrame.showLanding());
        add(back, BorderLayout.SOUTH);
    }
}
