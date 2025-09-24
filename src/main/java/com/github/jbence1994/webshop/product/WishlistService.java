package com.github.jbence1994.webshop.product;

public interface WishlistService {
    Product addProductToWishlist(Long productId);

    void deleteProductFromWishlist(Long productId);
}
