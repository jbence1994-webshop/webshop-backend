package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;

public final class WishlistProductDtoTestObject {
    public static WishlistProductDto wishlistProductDto() {
        return new WishlistProductDto(1L, PRODUCT_1_NAME);
    }
}
