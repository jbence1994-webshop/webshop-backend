package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryQueryService categoryQueryService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryQueryService.getCategories().stream()
                .map(productMapper::toDto)
                .toList();
    }
}
