package com.github.jbence1994.webshop.coupon;

public interface CouponService {
    void redeemCoupon(Long userId, String couponCode, Long orderId);
}
