package com.github.jbence1994.webshop.cart;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    CartItemDto toDto(CartItem cartItem);
}
