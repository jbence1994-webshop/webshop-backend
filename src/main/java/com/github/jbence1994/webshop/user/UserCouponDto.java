package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public record UserCouponDto(String code, LocalDateTime expirationDate) {
}
