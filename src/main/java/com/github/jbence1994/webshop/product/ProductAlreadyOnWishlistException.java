package com.github.jbence1994.webshop.product;

public class ProductAlreadyOnWishlistException extends RuntimeException {
    public ProductAlreadyOnWishlistException(Long productId) {
        super(String.format("This product with the given ID: #%d is already on your wishlist.", productId));
    }
}
