package com.github.jbence1994.webshop.coupon;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponDto toDto(Coupon coupon);

    @Mapping(target = "users", ignore = true)
    Coupon toEntity(CouponDto couponDto);
}
