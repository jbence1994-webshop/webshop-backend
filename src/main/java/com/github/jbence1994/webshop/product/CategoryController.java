package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryQueryService categoryQueryService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryQueryService.getCategories().stream()
                .map(productMapper::toCategoryDto)
                .toList();
    }
}
