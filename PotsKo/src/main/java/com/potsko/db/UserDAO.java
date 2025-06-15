package com.potsko.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.potsko.model.User;

public class UserDAO {
    // Method for sign up connectivity in the database
    public static int userRegister(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection dbConn = SQLiteManager.connect();
             PreparedStatement pstmt = dbConn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            // Get the generated user_id
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Sign up failed: " + e.getMessage());
        }
        return -1;
    }

    // Returns true if login is successful
    public static int userLogin(String identifier, String password) {
        String sql = "SELECT user_id FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try (Connection dbConn = SQLiteManager.connect();
             PreparedStatement pstmt = dbConn.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            pstmt.setString(3, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            System.err.println("Login failed due to a database error: " + e.getMessage());
            return -1;
        }
    }

    // Returns the user_id if login is successful, -1 otherwise
    public static int getUserIdByCredentials(String identifier, String password) {
        String sql = "SELECT user_id FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            pstmt.setString(3, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user_id: " + e.getMessage());
        }
        return -1;
    }

    public static void printAllUsers() {
        String sql = "SELECT user_id, username, email, date_created FROM users";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
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

    // Fetch user by ID
    public static User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("location"),
                        rs.getString("phone"),
                        rs.getString("profile_pic_path")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    // Update profile picture path
    public static boolean updateProfilePicPath(int userId, String path) {
        String sql = "UPDATE users SET profile_pic_path = ? WHERE user_id = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, path);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating profile picture: " + e.getMessage());
            return false;
        }
    }

    // Update other profile info (full name, location, phone, etc.)
    public static boolean updateProfileInfo(User user) {
        String sql = "UPDATE users SET full_name = ?, username = ?, email = ?, location = ?, phone = ?, profile_pic_path = ? WHERE user_id = ?";
        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getLocation());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getProfilePicPath());
            pstmt.setInt(7, user.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating profile info: " + e.getMessage());
            return false;
        }
    }
}