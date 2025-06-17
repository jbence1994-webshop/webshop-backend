package com.github.jbence1994.webshop.coupon;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(String code) {
        super(String.format("No coupon was found with the give coupon code: '%s'", code));
    }
}
