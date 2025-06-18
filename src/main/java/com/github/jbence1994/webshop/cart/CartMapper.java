package com.github.jbence1994.webshop.cart;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "appliedCoupon", expression = "java(cart.getAppliedCoupon() != null ? cart.getAppliedCoupon().getCode() : null)")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
