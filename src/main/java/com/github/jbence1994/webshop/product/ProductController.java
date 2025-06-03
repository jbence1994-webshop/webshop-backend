package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false, defaultValue = "id", name = "sort") String sortBy
    ) {
        return productService.getProducts(sortBy).stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        var product = productService.getProduct(id);
        return productMapper.toDto(product);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody CreateProductDto productDto) {
        var product = productMapper.toEntity(productDto);
        var createdProduct = productService.createProduct(product);
        return productMapper.toDto(createdProduct);
    }
}
