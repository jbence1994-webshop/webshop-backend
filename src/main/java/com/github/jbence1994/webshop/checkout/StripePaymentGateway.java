package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderItem;
import com.github.jbence1994.webshop.order.OrderStatus;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Value("${webshop.clientAppUrl}")
    private String clientAppUrl;

    @Value("${webshop.stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Override
    public CheckoutSessionResponse createCheckoutSession(CheckoutSessionRequest request) {
        try {
            var orderId = request.orderId();

            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(clientAppUrl + "/checkout-success?orderId=" + orderId)
                    .setCancelUrl(clientAppUrl + "/checkout-cancel")
                    .setPaymentIntentData(buildPaymentIntent(orderId));

            request.items().forEach(item -> {
                var lineItem = buildLineItem(item);
                builder.addLineItem(lineItem);
            });
            builder.addLineItem(buildShippingLineItem(request.shippingCost()));

            var session = Session.create(builder.build());

            return new CheckoutSessionResponse(session.getUrl());
        } catch (StripeException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    private static SessionCreateParams.PaymentIntentData buildPaymentIntent(Long orderId) {
        return SessionCreateParams.PaymentIntentData.builder()
                .putMetadata("order_id", orderId.toString())
                .build();
    }

    @Override
    public Optional<PaymentResult> parseWebhookRequest(WebhookRequest request) {
        try {
            var payload = request.payload();
            var signature = request.headers().get("stripe-signature");
            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);

            return switch (event.getType()) {
                case "payment_intent.succeeded" ->
                        Optional.of(new PaymentResult(extractOrderId(event), OrderStatus.COMPLETED));

                case "payment_intent.payment_failed" ->
                        Optional.of(new PaymentResult(extractOrderId(event), OrderStatus.FAILED));

                default -> Optional.of(new PaymentResult(extractOrderId(event), OrderStatus.CANCELLED));
            };
        } catch (SignatureVerificationException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    private Long extractOrderId(Event event) {
        var stripeObject = event
                .getDataObjectDeserializer()
                .getObject()
                .orElseThrow(() -> new PaymentException("Could not deserialize Stripe event. Check the SDK and API version."));

        var paymentIntent = (PaymentIntent) stripeObject;

        return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
    }

    private SessionCreateParams.LineItem buildLineItem(OrderItem item) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(item.getQuantity()))
                .setPriceData(buildPriceData(item))
                .build();
    }

    private SessionCreateParams.LineItem buildShippingLineItem(BigDecimal shippingCost) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(buildShippingPriceData(shippingCost))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData buildPriceData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100)))
                .setProductData(buildProductData(item))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData buildShippingPriceData(BigDecimal shippingCost) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(shippingCost.multiply(BigDecimal.valueOf(100)))
                .setProductData(buildShippingProductData())
                .build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData buildProductData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(item.getProduct().getName())
                .setDescription(item.getProduct().getDescription())
                .build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData buildShippingProductData() {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName("Shipping Cost")
                .build();
    }
}
