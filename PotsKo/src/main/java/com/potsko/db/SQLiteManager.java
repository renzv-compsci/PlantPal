// Manages database connections 
package com.potsko.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/com/potsko/db/potsko.db"; // File path for db
      
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
