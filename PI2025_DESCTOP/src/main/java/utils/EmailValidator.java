package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String HOST = "smtp.gmail.com";
    // Replace these with your actual Gmail credentials
    private static final String EMAIL = "your.email@gmail.com";
    private static final String APP_PASSWORD = "your-16-digit-app-password";
    
    private static final Pattern GMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@gmail\\.com$"
    );

    public static boolean isValidGmail(String emailToVerify) {
        if (emailToVerify == null || emailToVerify.trim().isEmpty()) {
            return false;
        }
        return GMAIL_PATTERN.matcher(emailToVerify.toLowerCase()).matches();
    }

    public static void sendPasswordResetEmail(String toEmail, String token) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", HOST);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, APP_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Réinitialisation de votre mot de passe TrekSwap");

        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        String htmlContent = String.format(
            "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;'>" +
            "<h2 style='color: #B05A36;'>Réinitialisation de votre mot de passe TrekSwap</h2>" +
            "<p>Bonjour,</p>" +
            "<p>Vous avez demandé la réinitialisation de votre mot de passe. Cliquez sur le lien ci-dessous :</p>" +
            "<p><a href='%s' style='background-color: #B05A36; color: #C6B9AB; padding: 10px 20px; " +
            "text-decoration: none; border-radius: 5px; display: inline-block;'>Réinitialiser mon mot de passe</a></p>" +
            "<p>Ce lien expirera dans 24 heures.</p>" +
            "<p>Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet email.</p>" +
            "<p>Cordialement,<br>L'équipe TrekSwap</p>" +
            "</div>",
            resetLink
        );

        message.setContent(htmlContent, "text/html; charset=utf-8");
        Transport.send(message);
    }
} 