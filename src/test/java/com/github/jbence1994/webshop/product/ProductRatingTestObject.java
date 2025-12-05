package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;

public final class ProductRatingTestObject {
    public static ProductRating productRating(byte value) {
        return new ProductRating(1L, product1(), encryptedUser1WithoutAvatar(), value);
    }
}
