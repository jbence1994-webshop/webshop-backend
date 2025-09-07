package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.ApplyCouponToCartRequestTestObject.notSantizedApplyCouponToCartRequest;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ApplyCouponToCartRequestSanitizerTests {

    @InjectMocks
    private ApplyCouponToCartRequestSanitizer applyCouponToCartRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = applyCouponToCartRequestSanitizer.sanitize(notSantizedApplyCouponToCartRequest());

        assertThat(result.getCouponCode(), equalTo(COUPON_1_CODE));
    }
}
