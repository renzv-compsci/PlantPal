package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.potsko.model.Plant;
import com.potsko.utils.FontUtils;
import com.potsko.utils.PlantDataLoader;
import com.potsko.utils.PotsKoConstants;

public class AvailablePlantsPanel extends JPanel {

    private NavBarPanel navBar;

    public AvailablePlantsPanel(MainFrame mainFrame, int loggedInUserId) {
        setOpaque(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1200, 780));
        setLayout(new BorderLayout());

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
        navBar.setActiveNavButton(navBar.getAvaiplantsButton());
        add(navBar, BorderLayout.WEST);

        // Background panel for main content area
        JPanel availablePlantsBgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int w = getWidth();
                int h = getHeight();
                g.setColor(PotsKoConstants.COLOR_MAIN_CONTENT_BG);
                g.fillRect(0, 0, w, h);
            }
        };
        availablePlantsBgPanel.setOpaque(true);
        availablePlantsBgPanel.setLayout(new BorderLayout());

        // Load plant data
        List<Plant> plants = PlantDataLoader.loadPlants("data/plants.json");

        // Header label and panel (keep your font and color)
        JLabel headerLabel = new JLabel("PotsKo’s Plantdex");
        Font customFont = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 70f);
        headerLabel.setFont(customFont);
        headerLabel.setForeground(new Color(81, 103, 78));

        JLabel dateLabel = new JLabel("📅 " + java.time.LocalDate.now());
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateLabel.setForeground(new Color(80, 80, 80));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));

        // Use BoxLayout for vertical stacking
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 10, 0)); // top, left, bottom, right
        headerPanel.add(headerLabel);
        headerPanel.add(dateLabel);

        // Grid panel
        int cols = 3;
        JPanel gridPanel = new JPanel(new GridLayout(0, cols, 40, 40));
        gridPanel.setOpaque(false);
        for (Plant plant : plants) {
            gridPanel.add(new PlantCard(plant));
        }

        // Scroll pane for grid (hide scrollbars)
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Main content panel with padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        int sidePadding = 120;
        JPanel leftPad = new JPanel();
        leftPad.setOpaque(false);
        leftPad.setPreferredSize(new Dimension(sidePadding, 10));
        JPanel rightPad = new JPanel();
        rightPad.setOpaque(false);
        rightPad.setPreferredSize(new Dimension(sidePadding, 10));
        scrollPane.getVerticalScrollBar().setUnitIncrement(24); // 24 is a good, smooth value
        contentPanel.add(leftPad, BorderLayout.WEST);
        contentPanel.add(rightPad, BorderLayout.EAST);

        // Add header and scrollable grid to content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add to background panel and then to main panel
        availablePlantsBgPanel.add(contentPanel, BorderLayout.CENTER);
        add(availablePlantsBgPanel, BorderLayout.CENTER);
    }
}