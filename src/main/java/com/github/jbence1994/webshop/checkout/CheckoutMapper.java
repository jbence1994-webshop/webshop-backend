package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {

    @Mapping(target = "cartId", source = "cart.id")
    @Mapping(target = "appliedCoupon", expression = "java(getCouponCode(checkoutSession))")
    @Mapping(target = "orderId", source = "order.id")
    CheckoutSessionDto toDto(CheckoutSession checkoutSession);

    default String getCouponCode(CheckoutSession checkoutSession) {
        return checkoutSession.getAppliedCoupon()
                .map(Coupon::getCode)
                .orElse(null);
    }
}
