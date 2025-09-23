package com.github.jbence1994.webshop.wishlist;

import com.github.jbence1994.webshop.product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    WishlistProductDto toDto(Product product);
}
