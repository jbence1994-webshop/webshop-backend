package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.product.CategoryTestObject.category1;
import static com.github.jbence1994.webshop.product.ProductFeedbackTestObject.productFeedback;
import static com.github.jbence1994.webshop.product.ProductRatingTestObject.productRating;
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
                category1(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static Product product1WithRating() {
        return new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                category1(),
                new ArrayList<>(),
                List.of(productRating((byte) 5)),
                new ArrayList<>()
        );
    }

    public static Product product1WithUpdatedRating() {
        return new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                category1(),
                new ArrayList<>(),
                List.of(productRating((byte) 4)),
                new ArrayList<>()
        );
    }

    public static Product product1WithFeedback() {
        return new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                category1(),
                new ArrayList<>(),
                List.of(productRating((byte) 4)),
                List.of(productFeedback())
        );
    }

    public static Product product1AfterMappingFromDto() {
        return new Product(
                null,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static Product product1WithPhotos() {
        var product = new Product(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                category1(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
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
                category1(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static Product product2WithPhotos() {
        var product = new Product(
                2L,
                PRODUCT_2_NAME,
                BigDecimal.valueOf(89.99),
                PRODUCT_2_UNIT,
                PRODUCT_2_DESCRIPTION,
                category1(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        product.addPhoto(PHOTO_FILE_NAME);
        return product;
    }
}
