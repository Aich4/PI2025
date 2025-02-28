package Controllers;

import services.SmsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SmsSender {

    @FXML
    private TextField message;  // TextField for the SMS message

    @FXML
    private TextField num;  // TextField for the recipient's phone number

    @FXML
    private Label statusLabel;  // Label to show success/error status


    @FXML
    void send(ActionEvent event) {
        String toPhoneNumber = num.getText().trim();  // Get the phone number from the text field
        String messageBody = message.getText().trim();  // Get the message from the text field

        // Check if both fields are filled in
        if (toPhoneNumber.isEmpty() || messageBody.isEmpty()) {
            statusLabel.setText("Error: Please fill in both phone number and message.");
            return;
        }

        // Validate the phone number format for Tunisia (starting with +216 or 00216 followed by 8 digits)
       /* if (!isValidTunisianPhoneNumber(toPhoneNumber)) {
            statusLabel.setText("Error: Invalid Tunisian phone number. Format: +216XXXXXXXX.");
            return;
        }*/

        try {
            // Call the SmsService to send the SMS
            SmsService.sendSms("+21694838676", messageBody);
            statusLabel.setText("SMS sent successfully!");  // Display success message
        } catch (Exception ex) {
            // Handle errors (e.g., Twilio API error)
            statusLabel.setText("Failed to send SMS: " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    // Method to validate Tunisian phone number
    private boolean isValidTunisianPhoneNumber(String phoneNumber) {
        // Check if the number starts with +216 or 00216 followed by 8 digits
        return phoneNumber.matches("^(\\+216|00216)[0-9]{8}$");
    }
}
