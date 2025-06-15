package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.potsko.db.UserPlantDAO;
import com.potsko.model.Plant;
import com.potsko.model.UserPlant;
import com.potsko.utils.PlantDataLoader;
import com.potsko.utils.UserPlantDataLoader;

public class DashboardPanel extends JPanel {
    private NavBarPanel navBar;
    private final MainFrame mainFrame;
    private final int userId;
    private final List<Plant> allPlants;

    public DashboardPanel(MainFrame mainFrame, int userId) {
        this.mainFrame = mainFrame;
        this.userId = userId;
        this.allPlants = PlantDataLoader.loadPlants("data/plants.json");

        setLayout(new BorderLayout());
        setBackground(new Color(245, 246, 240));

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
        navBar.setActiveNavButton(navBar.getDashboardButton());
        add(navBar, BorderLayout.WEST);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        JLabel headerLabel = new JLabel("Your Garden");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(81, 103, 78));
        headerPanel.add(headerLabel);

        JLabel dateLabel = new JLabel("📅 " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy")));
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateLabel.setForeground(new Color(80, 80, 80));
        headerPanel.add(dateLabel);

        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // Load user's plants
        List<UserPlant> userPlants = UserPlantDataLoader.loadUserPlants(userId, allPlants);

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 40, 40));
        gridPanel.setOpaque(false);

        if (userPlants.isEmpty()) {
            // Center the AddPlantCard if no plants
            JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            centerPanel.setLayout(new GridBagLayout());
            centerPanel.add(new AddPlantCard());
            contentPanel.add(centerPanel, BorderLayout.CENTER);
        } else {
            for (UserPlant up : userPlants) {
                gridPanel.add(new UserPlantCard(up, () -> mainFrame.showDashboard()));
            }
            // Always allow adding more plants
            gridPanel.add(new AddPlantCard());
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(headerPanel);
        mainPanel.add(contentPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    // AddPlantCard with click action to add a plant
    private class AddPlantCard extends JPanel {
        public AddPlantCard() {
            setPreferredSize(new Dimension(200, 250));
            setBackground(new Color(220, 230, 220));
            setBorder(BorderFactory.createDashedBorder(Color.GRAY));
            JLabel label = new JLabel("+ Add Plant");
            label.setFont(new Font("SansSerif", Font.BOLD, 18));
            label.setForeground(new Color(81, 103, 78));
            label.setHorizontalAlignment(JLabel.CENTER);
            setLayout(new BorderLayout());
            add(label, BorderLayout.CENTER);

            // Add click action
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Plant selectedPlant = PlantSelectionDialog.showDialog(mainFrame, allPlants);
                    if (selectedPlant != null) {
                        // Ask for growth stage
                        java.util.List<String> stages = selectedPlant.getGrowthStages();
                        if (stages == null || stages.isEmpty()) {
                            JOptionPane.showMessageDialog(mainFrame, "No growth stages found for this plant.");
                            return;
                        }
                        String[] stagesArray = stages.toArray(new String[0]);
                        String stage = (String) JOptionPane.showInputDialog(
                            mainFrame,
                            "Select current growth stage:",
                            "Growth Stage",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            stagesArray,
                            stagesArray[0]
                        );
                        if (stage == null) return; // User cancelled

                        String nickname = JOptionPane.showInputDialog(mainFrame, "Enter a nickname (optional):");
                        UserPlantDAO.addUserPlant(userId, selectedPlant.getId(), nickname, stage);
                        mainFrame.showDashboard(); // Refresh dashboard
                    }
                }
            });
        }
    }
}