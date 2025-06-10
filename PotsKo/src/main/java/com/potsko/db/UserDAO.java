package com.potsko.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    // Method for sign up connectivity in the database
    public static boolean userRegister(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection dbConn = SQLiteManager.connect();
             PreparedStatement pstmt = dbConn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Sign up failed: " + e.getMessage());
            return false;
        }
    }

    public static void printAllUsers() {
    String sql = "SELECT user_id, username, email, date_created FROM users";
    try (Connection conn = SQLiteManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %s%n",
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("date_created"));
        }
    } catch (SQLException e) {
        System.err.println("Error reading users: " + e.getMessage());
    }
}
}