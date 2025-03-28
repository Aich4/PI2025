package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {

    // Twilio Credentials
    private static final String ACCOUNT_SID = "AC26a6881dbae5ab0cd2ae4f4b1a9c679a";
    private static final String AUTH_TOKEN = "45a7ba1d719c519d089e68d9ffdbb270";
    private static final String TWILIO_PHONE_NUMBER = "+15167892852";

    // Method to send SMS
    public static void sendSms(String toPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),  // Recipient's phone number
                new PhoneNumber(TWILIO_PHONE_NUMBER),  // Your Twilio number
                messageBody  // SMS text content
        ).create();

        System.out.println("Message SID: " + message.getSid());
        System.out.println("Message Status: " + message.getStatus());
    }
}
