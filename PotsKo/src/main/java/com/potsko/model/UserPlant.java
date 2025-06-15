package com.potsko.model;

import java.time.Instant;

/**
 * Represents a plant owned by a user, including its nickname, watering info, and growth stage.
 *
 * OOP Principles demonstrated:
 * - Encapsulation: All fields are private and accessed via public getters/setters.
 * - Abstraction: The class abstracts the concept of a user's plant.
 * - Mutability: Most fields can be changed after object creation via setters.
 * - Association: Optionally holds a reference to a Plant object (composition/association).
 */
public class UserPlant {
    private final int userPlantId;
    private final int userId;
    private final int plantId;
    private String nickname;
    private Instant dateAdded;
    private Instant lastWatered;
    private String currentStage;
    private java.time.LocalDateTime stageStartDateTime;

    // Optionally, you can add a Plant reference if you want to join with Plant data
    private Plant plant;

    public UserPlant(int userPlantId, int userId, int plantId, String nickname,
                     Instant dateAdded, Instant lastWatered, String currentStage) {
        this.userPlantId = userPlantId;
        this.userId = userId;
        this.plantId = plantId;
        this.nickname = nickname;
        this.dateAdded = dateAdded;
        this.lastWatered = lastWatered;
        this.currentStage = currentStage;
    }

    public int getUserPlantId() {
        return userPlantId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPlantId() {
        return plantId;
    }

    public String getNickname() {
        return nickname;
    }

    public Instant getDateAdded() {
        return dateAdded;
    }

    public Instant getLastWatered() {
        return lastWatered;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public java.time.LocalDateTime getStageStartDateTime() {
        return stageStartDateTime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDateAdded(Instant dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setLastWatered(Instant lastWatered) {
        this.lastWatered = lastWatered;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public void setStageStartDateTime(java.time.LocalDateTime stageStartDateTime) {
        this.stageStartDateTime = stageStartDateTime;
    }

    // Optional: Plant reference for joined data
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
