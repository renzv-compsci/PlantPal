package com.potsko.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserPlantDAO {
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

    public static void updateLastWatered(int userPlantId, long epochSeconds) {
        String sql = "UPDATE userplants SET last_watered = ? WHERE user_plant_id = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, epochSeconds);
            pstmt.setInt(2, userPlantId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error updating last watered: " + e.getMessage());
        }
    }

    public static void deleteUserPlant(int userPlantId) {
        String sql = "DELETE FROM userplants WHERE user_plant_id = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userPlantId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error deleting user plant: " + e.getMessage());
        }
    }
}
