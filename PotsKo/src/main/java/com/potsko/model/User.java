package com.potsko.model;

public class User {
    private final int id;
    private String fullName;
    private String username;
    private String email;
    private String location;
    private String phone;
    private String profilePicPath; // Local file path to profile picture

    public User(int id, String fullName, String username, String email, String location, String phone, String profilePicPath) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.profilePicPath = profilePicPath;
    }

    // Getters
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getProfilePicPath() { return profilePicPath; }

    // Setters
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setLocation(String location) { this.location = location; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setProfilePicPath(String profilePicPath) { this.profilePicPath = profilePicPath; }
}