package com.github.jbence1994.webshop.checkout;

import org.springframework.stereotype.Component;

@Component
public class ApplyCouponToCheckoutSessionRequestSanitizer {

    public ApplyCouponToCheckoutSessionRequest sanitize(ApplyCouponToCheckoutSessionRequest request) {
        return new ApplyCouponToCheckoutSessionRequest(request.couponCode().trim());
    }
}
