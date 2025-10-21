package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.common.ClientAppConfig;
import com.github.jbence1994.webshop.coupon.Coupon;
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
    private final FreeShippingConfig freeShippingConfig;
    private final ClientAppConfig clientAppConfig;

    @Value("${webshop.stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @Value("${webshop.stripe.shippingCostId}")
    private String shippingCostId;

    @Value("${webshop.stripe.freeShippingId}")
    private String freeShippingId;

    @Override
    public PaymentSessionResponse createPaymentSession(PaymentSessionRequest request) {
        try {
            var checkoutSession = request.checkoutSession();
            var order = request.order();

            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setSuccessUrl(clientAppConfig.url() + "/checkout-success?orderId=" + order.getId())
                    .setCancelUrl(clientAppConfig.url() + "/checkout-cancel")
                    .setPaymentIntentData(buildPaymentIntent(checkoutSession.getCart().getId(), order.getId(), checkoutSession.getId()));

            checkoutSession.getAppliedCoupon()
                    .map(Coupon::getCode)
                    .map(this::buildDiscounts)
                    .ifPresent(builder::addAllDiscount);

            if (order.isEligibleForFreeShipping(freeShippingConfig.threshold())) {
                builder.addAllShippingOption(buildShippingOptions(freeShippingId));
            } else {
                builder.addAllShippingOption(buildShippingOptions(shippingCostId));
            }

            order.getItems().forEach(item -> {
                var lineItem = buildLineItem(item);
                builder.addLineItem(lineItem);
            });

            var session = Session.create(builder.build());

            return new PaymentSessionResponse(session.getUrl());
        } catch (StripeException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    @Override
    public Optional<PaymentResult> parseWebhookRequest(WebhookRequest request) {
        try {
            var payload = request.payload();
            var signature = request.headers().get("stripe-signature");
            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);
            var eventType = event.getType();

            return switch (eventType) {
                case "payment_intent.created" -> Optional.of(
                        new PaymentResult(
                                eventType,
                                extractCartId(event),
                                extractOrderId(event),
                                extractCheckoutSessionId(event),
                                OrderStatus.CREATED,
                                CheckoutStatus.PENDING
                        )
                );
                case "payment_intent.succeeded" -> Optional.of(
                        new PaymentResult(
                                eventType,
                                extractCartId(event),
                                extractOrderId(event),
                                extractCheckoutSessionId(event),
                                OrderStatus.CONFIRMED,
                                CheckoutStatus.COMPLETED
                        )
                );
                case "payment_intent.payment_failed" -> Optional.of(
                        new PaymentResult(
                                eventType,
                                extractCartId(event),
                                extractOrderId(event),
                                extractCheckoutSessionId(event),
                                OrderStatus.FAILED,
                                CheckoutStatus.FAILED
                        )
                );
                case "payment_intent.canceled" -> Optional.of(
                        new PaymentResult(
                                eventType,
                                extractCartId(event),
                                extractOrderId(event),
                                extractCheckoutSessionId(event),
                                OrderStatus.CANCELED,
                                CheckoutStatus.CANCELED
                        )
                );
                default -> Optional.empty();
            };
        } catch (SignatureVerificationException exception) {
            throw new PaymentException(exception.getMessage());
        }
    }

    private static SessionCreateParams.PaymentIntentData buildPaymentIntent(
            UUID cartId,
            Long orderId,
            UUID checkoutSessionId
    ) {
        return SessionCreateParams.PaymentIntentData.builder()
                .putMetadata("cart_id", cartId.toString())
                .putMetadata("order_id", orderId.toString())
                .putMetadata("checkout_session_id", checkoutSessionId.toString())
                .build();
    }

    private UUID extractCartId(Event event) {
        var paymentIntent = getPaymentIntent(event);
        return UUID.fromString(paymentIntent.getMetadata().get("cart_id"));
    }

    private Long extractOrderId(Event event) {
        var paymentIntent = getPaymentIntent(event);
        return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
    }

    private UUID extractCheckoutSessionId(Event event) {
        var paymentIntent = getPaymentIntent(event);
        return UUID.fromString(paymentIntent.getMetadata().get("checkout_session_id"));
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
