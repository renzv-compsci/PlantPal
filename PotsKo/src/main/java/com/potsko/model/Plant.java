package com.potsko.model;

// data model or POJO
// stores the properties for a plant 

import java.util.List;

public class Plant {
    private final int id;
    private final String name;
    private final String tagalogName;
    private final String scientificName;
    private final String category;
    private final String wateringFrequency;
    private final String sunlight;
    private final String plantType;
    private final List<String> growthStages;
    private final String image;

    // Constructor
    public Plant(int id, String name, String tagalogName, String scientificName,
                 String category, String wateringFrequency, String sunlight,
                 String plantType, List<String> growthStages, String image) {
        this.id = id;
        this.name = name;
        this.tagalogName = tagalogName;
        this.scientificName = scientificName;
        this.category = category;
        this.wateringFrequency = wateringFrequency;
        this.sunlight = sunlight;
        this.plantType = plantType;
        this.growthStages = growthStages;
        this.image = image;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getTagalogName() { return tagalogName; }
    public String getScientificName() { return scientificName; }
    public String getCategory() { return category; }
    public String getWateringFrequency() { return wateringFrequency; }
    public String getSunlight() { return sunlight; }
    public String getPlantType() { return plantType; }
    public List<String> getGrowthStages() { return growthStages; }
    public String getImage() { return image; }
}