package utils;

public class MigrationRunner {
    public static void main(String[] args) {
        System.out.println("Starting password migration...");
        PasswordMigration.migratePasswords();
        System.out.println("Password migration process completed.");
    }
} 