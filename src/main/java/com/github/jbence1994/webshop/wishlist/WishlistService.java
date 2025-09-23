package com.github.jbence1994.webshop.wishlist;

import com.github.jbence1994.webshop.product.Product;

public interface WishlistService {
    Product addProductToWishlist(Long productId);

    void deleteProductFromWishlist(Long productId);
}
