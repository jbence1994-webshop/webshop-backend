package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;

public final class CategoryDtoTestObject {
    public static CategoryDto categoryDto() {
        byte id = 1;

        return new CategoryDto(id, CATEGORY_1_NAME);
    }
}
