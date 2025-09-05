package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.common.ClientAppConfig;
import com.github.jbence1994.webshop.coupon.DiscountType;
import com.github.jbence1994.webshop.order.OrderItem;
import com.github.jbence1994.webshop.order.OrderStatus;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StripePaymentGateway implements PaymentGateway {
    private final ClientAppConfig clientAppConfig;

    @Value("${webshop.stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Value("${webshop.stripe.shippingCostId}")
    private String shippingCostId;

    @Value("${webshop.stripe.freeShippingId}")
    private String freeShippingId;

    @Override
    public CheckoutSessionResponse createCheckoutSession(CheckoutSessionRequest request) {
        try {
            var cartId = request.cartId();
            var orderId = request.orderId();

            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setSuccessUrl(clientAppConfig.url() + "/checkout-success?orderId=" + orderId)
                    .setCancelUrl(clientAppConfig.url() + "/checkout-cancel")
                    .setPaymentIntentData(buildPaymentIntent(cartId, orderId));

            var coupon = request.appliedCoupon();
            if (coupon != null) {
                builder.addAllDiscount(buildDiscounts(coupon.getCode()));

                if (DiscountType.FREE_SHIPPING.equals(coupon.getType())) {
                    builder.addAllShippingOption(buildShippingOptions(freeShippingId));
                }
            } else {
                builder.addAllShippingOption(buildShippingOptions(shippingCostId));
            }

            request.items().forEach(item -> {
                var lineItem = buildLineItem(item);
                builder.addLineItem(lineItem);
            });

            var session = Session.create(builder.build());

            return new CheckoutSessionResponse(session.getUrl());
        } catch (StripeException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    private static SessionCreateParams.PaymentIntentData buildPaymentIntent(UUID cartId, Long orderId) {
        return SessionCreateParams.PaymentIntentData.builder()
                .putMetadata("cart_id", cartId.toString())
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
                        Optional.of(new PaymentResult(extractCartId(event), extractOrderId(event), OrderStatus.COMPLETED));

                case "payment_intent.payment_failed" ->
                        Optional.of(new PaymentResult(extractCartId(event), extractOrderId(event), OrderStatus.FAILED));

                default ->
                        Optional.of(new PaymentResult(extractCartId(event), extractOrderId(event), OrderStatus.CANCELLED));
            };
        } catch (SignatureVerificationException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    private Long extractOrderId(Event event) {
        var paymentIntent = getPaymentIntent(event);
        return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
    }

    private UUID extractCartId(Event event) {
        var paymentIntent = getPaymentIntent(event);
        return UUID.fromString(paymentIntent.getMetadata().get("cart_id"));
    }

    private PaymentIntent getPaymentIntent(Event event) {
        var stripeObject = event
                .getDataObjectDeserializer()
                .getObject()
                .orElseThrow(() -> new PaymentException("Could not deserialize Stripe event. Check the SDK and API version."));

        return (PaymentIntent) stripeObject;
    }

    private SessionCreateParams.LineItem buildLineItem(OrderItem item) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(item.getQuantity()))
                .setPriceData(buildPriceData(item))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData buildPriceData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100)))
                .setProductData(buildProductData(item))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData buildProductData(OrderItem item) {
        return SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(item.getProduct().getName())
                .setDescription(item.getProduct().getDescription())
                .build();
    }

    private List<SessionCreateParams.ShippingOption> buildShippingOptions(String shippingRate) {
        var shippingOption = SessionCreateParams.ShippingOption.builder()
                .setShippingRate(shippingRate)
                .build();

        return List.of(shippingOption);
    }

    private List<SessionCreateParams.Discount> buildDiscounts(String couponCode) {
        var discount = SessionCreateParams.Discount.builder()
                .setCoupon(couponCode)
                .build();

        return List.of(discount);
    }
}
