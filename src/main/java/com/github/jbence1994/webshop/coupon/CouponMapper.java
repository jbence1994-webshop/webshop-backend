package com.github.jbence1994.webshop.coupon;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponDto toDto(Coupon coupon);

    Coupon toEntity(CouponDto couponDto);
}
