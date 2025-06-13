package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.coupon.CouponTestObject.expiredCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.notExpiredCoupon;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CouponTests {

    @Test
    public void isExpiredTest_Expired() {
        var result = expiredCoupon().isExpired();

        assertThat(result, is(true));
    }

    @Test
    public void isExpiredTest_NotExpired() {
        var result = notExpiredCoupon().isExpired();

        assertThat(result, is(false));
    }
}
