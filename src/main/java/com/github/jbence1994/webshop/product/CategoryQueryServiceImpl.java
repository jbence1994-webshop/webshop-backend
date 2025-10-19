package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new InvalidCategoryException(name));
    }
}
