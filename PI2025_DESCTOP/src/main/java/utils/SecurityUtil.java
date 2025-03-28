package utils;

import org.mindrot.jbcrypt.BCrypt;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SecurityUtil {
    private static final int SALT_ROUNDS = 12;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Map<String, ResetTokenInfo> resetTokens = new HashMap<>();

    public static class ResetTokenInfo {
        public final String email;
        public final LocalDateTime expiryTime;

        public ResetTokenInfo(String email, LocalDateTime expiryTime) {
            this.email = email;
            this.expiryTime = expiryTime;
        }
    }

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(SALT_ROUNDS));
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static String generateResetToken(String email) {
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
        
        // Store token with expiry time (24 hours from now)
        resetTokens.put(token, new ResetTokenInfo(email, LocalDateTime.now().plusHours(24)));
        
        return token;
    }

    public static String validateResetToken(String token) {
        ResetTokenInfo info = resetTokens.get(token);
        if (info == null) {
            return null; // Token doesn't exist
        }
        
        if (info.expiryTime.isBefore(LocalDateTime.now())) {
            resetTokens.remove(token); // Remove expired token
            return null; // Token expired
        }
        
        return info.email;
    }

    public static void removeResetToken(String token) {
        resetTokens.remove(token);
    }
} 