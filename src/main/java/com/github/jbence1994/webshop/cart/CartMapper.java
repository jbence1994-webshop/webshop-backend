package com.github.jbence1994.webshop.cart;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotal().getTotalPrice())")
    @Mapping(target = "appliedCoupon", expression = "java(cart.getAppliedCoupon() != null ? cart.getCouponCode() : null)")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateSubTotal())")
    CartItemDto toDto(CartItem cartItem);
}
