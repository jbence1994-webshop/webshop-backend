package com.github.jbence1994.webshop.coupon;

public class CouponAlreadyRedeemedException extends RuntimeException {
    public CouponAlreadyRedeemedException(String couponCode) {
        super(String.format("Coupon with the given ID: %s is already redeemed. Try another one.", couponCode));
    }
}
