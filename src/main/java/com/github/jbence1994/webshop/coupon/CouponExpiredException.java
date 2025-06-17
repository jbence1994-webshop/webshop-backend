package com.github.jbence1994.webshop.coupon;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(String code) {
        super(String.format("Coupon with the given code '%s' has expired", code));
    }
}