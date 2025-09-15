package com.github.jbence1994.webshop.checkout;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {

    @Mapping(target = "cartId", source = "cart.id")
    @Mapping(
            target = "appliedCoupon",
            expression = "java(checkoutSession.getAppliedCoupon().map(coupon -> coupon.getCode()).orElse(null))"
    )
    CheckoutSessionDto toDto(CheckoutSession checkoutSession);
}
