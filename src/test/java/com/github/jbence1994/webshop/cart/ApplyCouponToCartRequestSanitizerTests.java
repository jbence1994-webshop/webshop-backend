package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.ApplyCouponToCartRequestTestObject.applyCouponToCartRequest;
import static com.github.jbence1994.webshop.cart.ApplyCouponToCartRequestTestObject.notSanitizedApplyCouponToCartRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ApplyCouponToCartRequestSanitizerTests {

    @InjectMocks
    private ApplyCouponToCartRequestSanitizer applyCouponToCartRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = applyCouponToCartRequestSanitizer.sanitize(notSanitizedApplyCouponToCartRequest());

        assertThat(result.getCouponCode(), equalTo(applyCouponToCartRequest().getCouponCode()));
    }
}
