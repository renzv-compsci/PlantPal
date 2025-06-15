package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import com.potsko.db.UserPlantDAO;
import com.potsko.model.Plant;
import com.potsko.model.UserPlant;

public class UserPlantCard extends JPanel {
    private UserPlant userPlant;
    private Runnable reloadDashboard;
    private final JLabel timerLabel;
    private JCheckBox wateredCheck;
    private Timer swingTimer;
    private long secondsLeft;

    public UserPlantCard(UserPlant userPlant, Runnable reloadDashboard) {
        this.userPlant = userPlant;
        this.reloadDashboard = reloadDashboard;
        setPreferredSize(new Dimension(200, 250));
        setBackground(new Color(220, 230, 220));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setLayout(new BorderLayout());

        Plant plant = userPlant.getPlant();
        List<String> stages = plant.getGrowthStages();
        int currentStageIndex = 0;
        if (userPlant.getCurrentStage() != null) {
            for (int i = 0; i < stages.size(); i++) {
                if (stages.get(i).equalsIgnoreCase(userPlant.getCurrentStage())) {
                    currentStageIndex = i;
                    break;
                }
            }
        }
        String currentStage = userPlant.getCurrentStage();
        if (currentStage == null) currentStage = "";
        int daysRequired = getDaysForStage(currentStage);

        // Use the real stage start date from the model
        final LocalDateTime stageStartDateTime = userPlant.getStageStartDateTime() != null
            ? userPlant.getStageStartDateTime()
            : LocalDateTime.now();

        JProgressBar progressBar = new JProgressBar(0, daysRequired);
        progressBar.setStringPainted(true);

        Timer progressTimer = new Timer(1000 * 60 * 60 * 6, e -> { // update every 6 hours
            long daysElapsed = ChronoUnit.DAYS.between(stageStartDateTime, LocalDateTime.now());
            progressBar.setValue((int) Math.min(daysElapsed, daysRequired));
            progressBar.setString(daysElapsed + " / " + daysRequired + " days");
        });
        progressTimer.setInitialDelay(0);
        progressTimer.start();

        // Nickname or plant name
        JLabel nameLabel = new JLabel(
            (userPlant.getNickname() != null && !userPlant.getNickname().isEmpty())
                ? userPlant.getNickname()
                : (plant != null ? plant.getName() : "Unknown Plant")
        );
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        // Tagalog name
        JLabel tagalogLabel = new JLabel(
            plant != null ? "Tagalog: " + plant.getTagalogName() : ""
        );
        tagalogLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tagalogLabel.setHorizontalAlignment(JLabel.CENTER);

        // Category
        JLabel categoryLabel = new JLabel(
            plant != null ? "Category: " + plant.getCategory() : ""
        );
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);

        // Growth stage
        JLabel stageLabel = new JLabel(
            "Stage: " + (userPlant.getCurrentStage() != null ? userPlant.getCurrentStage() : "")
        );
        stageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        stageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Layout
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(nameLabel);
        infoPanel.add(tagalogLabel);
        infoPanel.add(categoryLabel);
        infoPanel.add(stageLabel);
        infoPanel.add(progressBar);

        add(infoPanel, BorderLayout.CENTER);

        // Bottom panel with timer and watered checkbox
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new GridLayout(2, 1));

        timerLabel = new JLabel("Next water: --:--", JLabel.CENTER);
        bottomPanel.add(timerLabel);

        wateredCheck = new JCheckBox("Watered");
        wateredCheck.setOpaque(false);
        bottomPanel.add(wateredCheck);

        add(bottomPanel, BorderLayout.SOUTH);

        // Remove button
        JButton removeBtn = new JButton("X");
        removeBtn.setMargin(new Insets(2, 6, 2, 6));
        removeBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        removeBtn.setForeground(Color.RED);
        removeBtn.setFocusPainted(false);
        removeBtn.setBorderPainted(false);
        removeBtn.setContentAreaFilled(false);
        removeBtn.setToolTipText("Remove this plant");
        removeBtn.addActionListener(ev -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Remove this plant from your garden?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                UserPlantDAO.deleteUserPlant(userPlant.getUserPlantId());
                reloadDashboard.run();
            }
        });

        // Add remove button to the card
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(nameLabel, BorderLayout.CENTER);
        headerPanel.add(removeBtn, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Watering logic (interval depends on stage)
        updateTimer();

        wateredCheck.addActionListener(e -> {
            if (wateredCheck.isSelected()) {
                Instant last = userPlant.getLastWatered();
                Instant now = Instant.now();
                long interval = getWateringIntervalForStage(userPlant.getCurrentStage());
                if (last != null) {
                    long elapsed = java.time.Duration.between(last, now).getSeconds();
                    if (elapsed < interval) {
                        wateredCheck.setSelected(false); // uncheck it
                        JOptionPane.showMessageDialog(
                            this,
                            "Wait for the timer to finish. You just watered your plant!",
                            "Too Soon!",
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }
                }
                // It's OK to water
                userPlant.setLastWatered(now);
                UserPlantDAO.updateLastWatered(userPlant.getUserPlantId(), now.getEpochSecond());
                updateTimer();
            }
        });
    }

    private void updateTimer() {
        if (swingTimer != null) swingTimer.stop();
        Instant last = userPlant.getLastWatered();
        Instant now = Instant.now();
        long interval = getWateringIntervalForStage(userPlant.getCurrentStage());
        if (last != null) {
            long elapsed = ChronoUnit.SECONDS.between(last, now);
            secondsLeft = Math.max(0, interval - elapsed);
        } else {
            secondsLeft = 0;
        }
        swingTimer = new Timer(1000, e -> {
            if (secondsLeft > 0) {
                long hours = (secondsLeft % 86400) / 3600;
                long mins = (secondsLeft % 3600) / 60;
                long secs = secondsLeft % 60;
                timerLabel.setText(String.format("Next water: %02dh %02dm %02ds", hours, mins, secs));
                secondsLeft--;
            } else {
                timerLabel.setText("Water now!");
                wateredCheck.setSelected(false);
                swingTimer.stop();
            }
        });
        swingTimer.start();
    }

    // Accurate days per stage
    private int getDaysForStage(String stage) {
        if (stage == null) return 5;
        switch (stage.toLowerCase()) {
            case "germination": return 7;
            case "seedling": return 14;
            case "juvenile plant": return 21;
            case "vegetative growth": return 30;
            case "fenestration": return 10;
            case "flowering": return 14;
            case "rareflower": return 21;
            case "reproduction": return 10;
            case "propagation": return 14;
            case "maturation": return 21;
            case "mature": return 5;
            default: return 5;
        }
    }

    // Accurate watering interval per stage (in seconds)
    private long getWateringIntervalForStage(String stage) {
        if (stage == null) return 172800; // default 48 hours
        switch (stage.toLowerCase()) {
            case "germination": return 86400;      // 24 hours
            case "seedling": return 172800;        // 48 hours
            case "juvenile plant": return 259200;  // 72 hours
            case "vegetative growth": return 172800; // 48 hours
            case "fenestration": return 259200;    // 72 hours
            case "flowering": return 172800;       // 48 hours
            case "rareflower": return 172800;      // 48 hours
            case "reproduction": return 259200;    // 72 hours
            case "propagation": return 172800;     // 48 hours
            case "maturation": return 259200;      // 72 hours
            case "mature": return 345600;          // 96 hours
            default: return 172800;                // default 48 hours
        }
    }
}