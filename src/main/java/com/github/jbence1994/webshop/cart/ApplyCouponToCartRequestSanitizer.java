package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.common.InputSanitizer;
import org.springframework.stereotype.Component;

@Component
public class ApplyCouponToCartRequestSanitizer implements InputSanitizer<ApplyCouponToCartRequest> {

    @Override
    public ApplyCouponToCartRequest sanitize(ApplyCouponToCartRequest input) {
        var sanitizedCouponCode = input.getCouponCode().trim();

        return new ApplyCouponToCartRequest(sanitizedCouponCode);
    }
}
