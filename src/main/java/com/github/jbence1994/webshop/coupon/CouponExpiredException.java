package com.github.jbence1994.webshop.coupon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(String code, LocalDateTime expirationDate) {
        super(
                String.format(
                        "Coupon with the given code '%s' has expired on %s",
                        code,
                        expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )
        );
    }
}
