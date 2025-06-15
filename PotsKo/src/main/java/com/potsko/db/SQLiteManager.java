// Manages database connections 
package com.potsko.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {
    private static final String DB_URL = "jdbc:sqlite:/Users/Renz/PotsKo/PotsKo/src/main/java/com/potsko/db/potsko.db"; // File path for db
      
    public static Connection connect() throws SQLException {
        System.out.println("Connecting to DB: " + DB_URL); // or whatever variable holds your DB path
        return DriverManager.getConnection(DB_URL);
    }
}