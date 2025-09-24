package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.checkout.ApplyCouponToCheckoutSessionRequestTestObject.notSanitizedApplyCouponToCheckoutSessionRequest;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ApplyCouponToCheckoutSessionRequestSanitizerTests {

    @InjectMocks
    private ApplyCouponToCheckoutSessionRequestSanitizer applyCouponToCheckoutSessionRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = applyCouponToCheckoutSessionRequestSanitizer.sanitize(notSanitizedApplyCouponToCheckoutSessionRequest());

        assertThat(result.getCouponCode(), equalTo(COUPON_1_CODE));
    }
}
