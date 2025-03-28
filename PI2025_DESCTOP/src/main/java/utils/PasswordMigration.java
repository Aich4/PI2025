package utils;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PasswordMigration {
    public static void migratePasswords() {
        try {
            Connection conn = MyDb.getInstance().getConnection();
            
            // First, check if we need to modify the password column length
            String alterQuery = "ALTER TABLE user MODIFY COLUMN mot_de_passe VARCHAR(255)";
            try (PreparedStatement alterStmt = conn.prepareStatement(alterQuery)) {
                alterStmt.executeUpdate();
                System.out.println("Password column modified successfully");
            }

            // Get all users and their current passwords
            String selectQuery = "SELECT id, mot_de_passe FROM user";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                ResultSet rs = selectStmt.executeQuery();
                
                // Prepare update statement
                String updateQuery = "UPDATE user SET mot_de_passe = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String plainPassword = rs.getString("mot_de_passe");
                        
                        // Skip if password is already hashed (BCrypt hashes start with '$2a$' or '$2b$')
                        if (plainPassword != null && !plainPassword.startsWith("$2")) {
                            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
                            
                            updateStmt.setString(1, hashedPassword);
                            updateStmt.setInt(2, id);
                            updateStmt.executeUpdate();
                            
                            System.out.println("Updated password for user ID: " + id);
                        }
                    }
                }
                System.out.println("Password migration completed successfully");
            }
        } catch (Exception e) {
            System.err.println("Error during password migration: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 