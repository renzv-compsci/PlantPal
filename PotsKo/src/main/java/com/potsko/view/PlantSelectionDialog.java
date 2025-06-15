package com.potsko.view;

import com.potsko.model.Plant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlantSelectionDialog extends JDialog {
    private Plant selectedPlant = null;

    public PlantSelectionDialog(Frame owner, List<Plant> allPlants) {
        super(owner, "Select a Plant", true);
        setLayout(new BorderLayout());

        DefaultListModel<Plant> model = new DefaultListModel<>();
        for (Plant p : allPlants) model.addElement(p);

        JList<Plant> plantList = new JList<>(model);
        plantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Custom cell renderer to show Tagalog name and category
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

        JScrollPane scrollPane = new JScrollPane(plantList);
        add(scrollPane, BorderLayout.CENTER);

        JButton selectBtn = new JButton("Select");
        selectBtn.addActionListener(e -> {
            selectedPlant = plantList.getSelectedValue();
            dispose();
        });
        add(selectBtn, BorderLayout.SOUTH);

        setSize(300, 400);
        setLocationRelativeTo(owner);
    }

    public static Plant showDialog(Frame owner, List<Plant> allPlants) {
        PlantSelectionDialog dialog = new PlantSelectionDialog(owner, allPlants);
        dialog.setVisible(true);
        return dialog.selectedPlant;
    }
}
