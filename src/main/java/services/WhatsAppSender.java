package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppSender {
    // Remplace par tes identifiants Twilio
    public static final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
    public static final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";

    public static void sendWhatsAppMessage(String to, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),  // Destinataire
                new PhoneNumber("whatsapp:+14155238886"), // Ton numéro Twilio WhatsApp
                messageBody // Contenu du message
        ).create();

        System.out.println("Message envoyé avec SID: " + message.getSid());
    }
}
