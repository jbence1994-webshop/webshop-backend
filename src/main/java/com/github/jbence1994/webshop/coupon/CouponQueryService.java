package com.github.jbence1994.webshop.coupon;

import java.util.List;

public interface CouponQueryService {
    List<Coupon> getCoupons();

    List<Coupon> getCouponsByUser(Long userId);
}
