package com.github.jbence1994.webshop.coupon;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/coupons")
@AllArgsConstructor
public class UserCouponsController {
    private final CouponQueryService couponQueryService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons(@PathVariable("id") Long id) {
        return couponQueryService.getCouponsByUser(id).stream()
                .map(couponMapper::toDto)
                .toList();
    }
}
