package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;

public final class ProductRatingTestObject {
    public static ProductRating productRating(byte value) {
        return new ProductRating(1L, product1(), profileWithoutAvatar(), value);
    }
}
