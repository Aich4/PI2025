package services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class Payment {
// Set your secret key
static {
    Stripe.apiKey = "sk_test_51Qvj4hLpJVuoWHzzyi8ZrFRMdfog1cxiX0K2nvkNal6gM1d1X1jQHouzQuOcVJ6PhQ3cuwluyhAt1mHjoLGaO1A300vre6pcGP";
}

    public String createCheckoutSession(long priceLong) {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setSuccessUrl("https://your-site.com/success")
                    .setCancelUrl("https://your-site.com/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount(priceLong) // Amount in cents
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Abonnement")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            Session session = Session.create(params);
            return session.getUrl();

        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
