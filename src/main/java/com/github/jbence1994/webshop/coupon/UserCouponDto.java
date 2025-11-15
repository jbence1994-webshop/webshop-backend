package com.github.jbence1994.webshop.coupon;

import java.time.LocalDateTime;

public record UserCouponDto(String code, LocalDateTime expirationDate) {
}
