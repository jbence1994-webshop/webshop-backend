package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public interface CheckoutService {
    CheckoutSession createCheckoutSession(UUID cartId);

    CheckoutSession applyCouponToCheckoutSession(UUID id, String couponCode);

    CheckoutSession removeCouponFromCheckoutSession(UUID id);

    CompleteCheckoutSessionResponse completeCheckoutSession(UUID checkoutSessionId, RewardPointsAction action);

    // void handleCompleteCheckoutSessionWebhookEvent(WebhookRequest request);
}
