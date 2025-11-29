package com.github.jbence1994.webshop.coupon;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponQueryService couponQueryService;
    private final CouponService couponService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons() {
        return couponQueryService.getCoupons().stream()
                .map(couponMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<CouponDto> createCoupon(@Valid @RequestBody CouponDto couponDto) {
        var coupon = couponMapper.toEntity(couponDto);

        couponService.createCoupon(coupon);

        return ResponseEntity.status(HttpStatus.CREATED).body(couponDto);
    }
}
