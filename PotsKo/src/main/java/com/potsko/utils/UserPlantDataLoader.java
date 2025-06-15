package com.potsko.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.potsko.db.SQLiteManager;
import com.potsko.model.Plant;
import com.potsko.model.UserPlant;

public class UserPlantDataLoader {
    public static List<UserPlant> loadUserPlants(int userId, List<Plant> allPlants) {
        List<UserPlant> userPlants = new ArrayList<>();
        String sql = "SELECT * FROM userplants WHERE user_id = ?";

        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int userPlantId = rs.getInt("user_plant_id");
                    int plantId = rs.getInt("plant_id");
                    String nickname = rs.getString("nickname");
                    long dateAddedLong = rs.getLong("date_added");
                    long lastWateredLong = rs.getLong("last_watered");
                    String currentStage = rs.getString("current_stage");

                    Instant dateAdded = dateAddedLong > 0 ? Instant.ofEpochSecond(dateAddedLong) : null;
                    Instant lastWatered = lastWateredLong > 0 ? Instant.ofEpochSecond(lastWateredLong) : null;

                    // Find the Plant object from the loaded plants.json list
                    Plant plant = allPlants.stream()
                        .filter(p -> p.getId() == plantId)
                        .findFirst()
                        .orElse(null);

                    if (plant != null) {
                        UserPlant userPlant = new UserPlant(
                            userPlantId, userId, plantId, nickname, dateAdded, lastWatered, currentStage
                        );
                        userPlant.setPlant(plant);
                        userPlants.add(userPlant);
                    } else {
                        System.err.println("Warning: Plant with ID " + plantId + " not found in allPlants list.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading user plants: " + e.getMessage());
        }

        return userPlants;
    }

    public static boolean addUserPlant(int userId, int plantId, String nickname, String currentStage) {
        String sql = "INSERT INTO userplants (user_id, plant_id, nickname, date_added, last_watered, current_stage) VALUES (?, ?, ?, strftime('%s','now'), NULL, ?)";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, plantId);
            pstmt.setString(3, nickname);
            pstmt.setString(4, currentStage);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding user plant: " + e.getMessage());
            return false;
        }
    }
}
