package com.github.jbence1994.webshop.coupon;

import java.util.List;

public interface CouponQueryService {
    List<Coupon> getCoupons();

    Coupon getCoupon(String code);

    boolean isCouponRedeemed(String code);
}
