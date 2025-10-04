package com.github.jbence1994.webshop.coupon;

public final class CouponAlreadyRedeemedException extends RuntimeException {
    public CouponAlreadyRedeemedException(String couponCode) {
        super(String.format("Coupon with the given code: '%s' is already redeemed. Try another one.", couponCode));
    }
}
