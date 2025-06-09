package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;
import java.util.HashSet;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_UNIT;

public final class ProductTestObject {
    public static Product product1() {
        return new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                new HashSet<>()
        );
    }

    public static Product product1WithNullId() {
        return new Product(
                null,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                new HashSet<>()
        );
    }

    public static Product product1WithPhotos() {
        var product = new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                new HashSet<>()
        );
        product.addPhoto(PHOTO_FILE_NAME);
        return product;
    }

    public static Product product2() {
        return new Product(
                2L,
                PRODUCT_2_NAME,
                BigDecimal.valueOf(89.99),
                PRODUCT_2_UNIT,
                PRODUCT_2_DESCRIPTION,
                new HashSet<>()
        );
    }
}
