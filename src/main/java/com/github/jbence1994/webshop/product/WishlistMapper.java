package com.github.jbence1994.webshop.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    WishlistProductDto toDto(Product product);
}
