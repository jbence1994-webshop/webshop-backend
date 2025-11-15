package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.coupon.UserCouponDto;

import java.util.List;

public record UserDto(
        Long id,
        String email,
        ProfileDto profile,
        List<UserCouponDto> coupons
) {
}
