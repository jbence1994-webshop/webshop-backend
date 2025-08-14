package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Category getCategory(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new InvalidCategoryException(name));
    }
}
