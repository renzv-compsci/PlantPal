package com.potsko.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.potsko.model.JournalEntry;

public class JournalEntryDAO {
    public static boolean addEntry(int userId, Integer userPlantId, String title, String content) {
        String sql = "INSERT INTO journalentries (user_id, user_plant_id, title, content) VALUES (?, ?, ?, ?)";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            if (userPlantId != null) {
                pstmt.setInt(2, userPlantId);
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setString(3, title);
            pstmt.setString(4, content);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding journal entry: " + e.getMessage());
            return false;
        }
    }

    public static List<JournalEntry> getEntries(int userId) {
        List<JournalEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM journalentries WHERE user_id = ? ORDER BY date_written DESC";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                entries.add(new JournalEntry(
                    rs.getInt("entry_id"),
                    rs.getInt("user_id"),
                    rs.getObject("user_plant_id") != null ? rs.getInt("user_plant_id") : null,
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getLong("date_written")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching journal entries: " + e.getMessage());
        }
        return entries;
    }
}
