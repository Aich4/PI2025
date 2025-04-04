package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppSender {

    // Remplace par tes propres identifiants Twilio
    public static final String ACCOUNT_SID = "AC870e360f84c23724845f79804bd75bb4"; // Remplacez par votre SID Twilio
    public static final String AUTH_TOKEN = "481c50e54a12a2ae2f9176918b9075b8";  // Remplacez par votre Auth Token Twilio

    // Numéro du destinataire
    private static final String NUMERO_DESTINATAIRE = "+21698754739"; // Remplacez par votre numéro cible

    public static void envoyerMessageWhatsApp(String messageText) {
        // Initialiser Twilio avec le SID et le Token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            // Envoyer un message WhatsApp
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + NUMERO_DESTINATAIRE),  // Numéro du destinataire
                            new PhoneNumber("whatsapp:+14155238886"),  // Numéro WhatsApp Twilio (Sandbox)

                            messageText)
                    .create();

            System.out.println("Message envoyé avec succès. SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi du message WhatsApp: " + e.getMessage());
        }
    }
}
