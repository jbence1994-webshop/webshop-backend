package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;

public final class CategoryTestObject {
    public static Category category1() {
        byte id = 1;

        return new Category(id, CATEGORY_1_NAME);
    }
}
