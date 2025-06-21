package com.github.jbence1994.webshop.coupon;

public class ExpiredCouponException extends RuntimeException {
    public ExpiredCouponException(String code) {
        super(String.format("Coupon with the given code: '%s' has expired.", code));
    }
}
