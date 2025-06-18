package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.user.User;

import java.util.List;

public interface CouponQueryService {
    List<Coupon> getCoupons();

    Coupon getCoupon(String code);

    List<Coupon> getCouponsByUser(User user);
}
