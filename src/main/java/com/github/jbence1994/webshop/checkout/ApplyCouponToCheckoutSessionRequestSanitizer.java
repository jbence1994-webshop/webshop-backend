package com.github.jbence1994.webshop.checkout;

import org.springframework.stereotype.Component;

@Component
public class ApplyCouponToCheckoutSessionRequestSanitizer {

    public ApplyCouponToCheckoutSessionRequest sanitize(ApplyCouponToCheckoutSessionRequest request) {
        var sanitizedCouponCode = request.getCouponCode().trim();

        return new ApplyCouponToCheckoutSessionRequest(sanitizedCouponCode);
    }
}
