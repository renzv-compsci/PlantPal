package com.potsko.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.potsko.utils.FontUtils;
import com.potsko.utils.PotsKoConstants;

public class HomePanel extends JLayeredPane {

    private NavBarPanel navBar;

    public HomePanel(MainFrame mainFrame, int loggedInUserId) {
        setOpaque(true);
        setPreferredSize(new Dimension(1200, 780));

        // Sets the custom painted background (removed AI and weather sections)
        JPanel homePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int w = getWidth();
                int h = getHeight();

                g.setColor(PotsKoConstants.COLOR_SIDE_NAV_BG);
                g.fillRect(0, 0, PotsKoConstants.SIDE_NAV_WIDTH, h);

                g.setColor(PotsKoConstants.COLOR_MAIN_CONTENT_BG);
                g.fillRect(PotsKoConstants.SIDE_NAV_WIDTH, 0, w - PotsKoConstants.SIDE_NAV_WIDTH, h);
            }
        };
        homePanel.setLayout(null);
        add(homePanel, JLayeredPane.DEFAULT_LAYER);

        // Add the reusable navbarpanel
        navBar = new NavBarPanel(e -> {
            JButton src = (JButton) e.getSource();
            navBar.setActiveNavButton(src);

            if (src == navBar.getHomeButton()) {
                mainFrame.showHome();
            }
            if (src == navBar.getDashboardButton()) {
                mainFrame.showDashboard();
            }
            if (src == navBar.getJournalButton()) {
                mainFrame.showJournal();
            }
            if (src == navBar.getAvaiplantsButton()) {
                mainFrame.showAvailablePlants();
            }
            if (src == navBar.getProfileButton()) {
                mainFrame.showProfile();
            }
        });

        // Position and add navbar panel
        navBar.setBounds(0, 0, PotsKoConstants.SIDE_NAV_WIDTH, getPreferredSize().height);
        add(navBar, JLayeredPane.PALETTE_LAYER);

        // Add the two-line safe space label using FontUtils, big and left-aligned with padding
        int leftPadding = PotsKoConstants.SIDE_NAV_WIDTH + 60;
        Font fontLine1 = FontUtils.getFont("Poppins-Regular.ttf", 64f);
        Font fontLine2 = FontUtils.getFont("Poppins-Regular.ttf", 48f);

        JLabel line1 = new JLabel("A Safe space");
        line1.setFont(fontLine1);
        line1.setBounds(leftPadding, 100, 900, 80);

        JLabel line2 = new JLabel("for you and your plants");
        line2.setFont(fontLine2);
        line2.setBounds(leftPadding, 180, 900, 70);

        homePanel.add(line1);
        homePanel.add(line2);

        // Responsive: keep labels left-aligned with padding
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int h = getHeight();
                homePanel.setBounds(0, 0, getWidth(), h);
                navBar.setBounds(0, 0, PotsKoConstants.SIDE_NAV_WIDTH, h);

                // Always keep the same left padding
                line1.setBounds(leftPadding, 100, 900, 80);
                line2.setBounds(leftPadding, 180, 900, 70);
            }
        });
    }
}