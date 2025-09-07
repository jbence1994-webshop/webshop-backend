package com.github.jbence1994.webshop.cart;

import org.springframework.stereotype.Component;

@Component
public class ApplyCouponToCartRequestSanitizer {

    public ApplyCouponToCartRequest sanitize(ApplyCouponToCartRequest request) {
        var sanitizedCouponCode = request.getCouponCode().trim();
        return new ApplyCouponToCartRequest(sanitizedCouponCode);
    }
}
