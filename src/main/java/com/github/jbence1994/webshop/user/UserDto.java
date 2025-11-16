package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.coupon.UserCouponDto;
import com.github.jbence1994.webshop.product.WishlistProductDto;

import java.util.List;

public record UserDto(
        Long id,
        String email,
        ProfileDto profile,
        List<UserCouponDto> coupons,
        List<WishlistProductDto> favoriteProducts
) {
}
