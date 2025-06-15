package com.potsko.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import com.potsko.model.Plant;
import com.potsko.utils.FontUtils;

public class PlantCard extends JPanel {
    public PlantCard(Plant plant) {
        setLayout(new BorderLayout());
        setBackground(new Color(235, 236, 225));
        setPreferredSize(new Dimension(260, 380));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Image
        String imagePath = plant.getImage();
        java.net.URL imgUrl = getClass().getClassLoader().getResource(imagePath);
        ImageIcon icon = imgUrl != null ? new ImageIcon(imgUrl) : new ImageIcon();
        Image img = icon.getImage().getScaledInstance(160, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Font customFont = FontUtils.getFont("AnnapurnaSIL-Bold.ttf", 20f);

        JLabel nameLabel = new JLabel("Name: " + plant.getTagalogName());
        nameLabel.setFont(customFont);
        nameLabel.setForeground(new Color(81, 103, 78));

        JLabel waterLabel = new JLabel("Water: " + plant.getWateringFrequency());
        waterLabel.setFont(customFont);
        waterLabel.setForeground(new Color(81, 103, 78));

        JLabel categoryLabel = new JLabel("Category: " + plant.getCategory());
        categoryLabel.setFont(customFont);
        categoryLabel.setForeground(new Color(81, 103, 78));

        // Growth stages (split and add each as a separate label)
        JPanel growthPanel = new JPanel();
        growthPanel.setLayout(new BoxLayout(growthPanel, BoxLayout.Y_AXIS));
        growthPanel.setOpaque(false);

        JLabel growthTitle = new JLabel("Growth Stages:");
        growthTitle.setFont(customFont);
        growthTitle.setForeground(new Color(81, 103, 78));
        growthPanel.add(growthTitle);

        List<String> growthStages = plant.getGrowthStages();
        Font stageFont = customFont.deriveFont(Font.PLAIN, 16f);

        if (growthStages != null && !growthStages.isEmpty()) {
            for (String stage : growthStages) {
                JLabel stageLabel = new JLabel(stage.trim());
                stageLabel.setFont(stageFont);
                stageLabel.setForeground(new Color(81, 103, 78));
                growthPanel.add(stageLabel);
            }
        } else {
            JLabel noStagesLabel = new JLabel("No data available");
            noStagesLabel.setFont(stageFont);
            noStagesLabel.setForeground(new Color(120, 120, 120));
            growthPanel.add(noStagesLabel);
        }

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(waterLabel);
        infoPanel.add(categoryLabel);
        infoPanel.add(growthPanel);

        add(imageLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
    }
}