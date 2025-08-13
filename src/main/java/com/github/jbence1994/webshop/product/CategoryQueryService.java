package com.github.jbence1994.webshop.product;

import java.util.List;

public interface CategoryQueryService {
    List<Category> getCategories();

    Category getCategory(String name);
}
