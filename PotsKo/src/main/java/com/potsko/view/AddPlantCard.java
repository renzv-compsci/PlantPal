package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.potsko.db.UserPlantDAO;
import com.potsko.model.Plant;

public class AddPlantCard extends JPanel {
    public AddPlantCard(MainFrame mainFrame, int userId, List<Plant> allPlants, Runnable reloadDashboard) {
        setPreferredSize(new Dimension(320, 260));
        setBackground(new Color(235, 236, 210));
        setLayout(new BorderLayout());
        JButton addBtn = new JButton("+");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 48));
        addBtn.setFocusPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setBorderPainted(false);
        addBtn.setBorder(null);
        add(addBtn, BorderLayout.CENTER);

        // Plant list setup
        DefaultListModel<Plant> model = new DefaultListModel<>();
        for (Plant p : allPlants) model.addElement(p);

        JList<Plant> plantList = new JList<>(model);
        plantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addBtn.addActionListener(e -> {
            System.out.println("Add Plant button clicked");
            // Show plant selection dialog
            Plant selectedPlant = PlantSelectionDialog.showDialog(mainFrame, allPlants);
            System.out.println("Selected plant: " + selectedPlant);
            if (selectedPlant != null) {
                List<String> stages = selectedPlant.getGrowthStages();
                System.out.println("Growth stages: " + stages);
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
                System.out.println("Selected stage: " + stage);
                if (stage == null) return;
                String nickname = JOptionPane.showInputDialog(mainFrame, "Enter a nickname (optional):");
                System.out.println("Nickname: " + nickname);
                UserPlantDAO.addUserPlant(userId, selectedPlant.getId(), nickname, stage);
                reloadDashboard.run();
            }
        });

        // Custom cell renderer for plant list
        plantList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Plant plant = (Plant) value;
                label.setText(
                    "<html><b>" + plant.getName() + "</b> (" + plant.getTagalogName() + ")<br/>" +
                    "<i>Category: " + plant.getCategory() + "</i></html>"
                );
                return label;
            }
        });
    }
}
