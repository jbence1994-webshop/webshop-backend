package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private final ProductQueryService productQueryService;
    private final CategoryQueryService categoryQueryService;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ImageUrlBuilder imageUrlBuilder;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false, name = "sort") String sortBy,
            @RequestParam(required = false, name = "order") String orderBy,
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "size") int size,
            @RequestParam(required = false, name = "categoryId") Byte categoryId
    ) {
        var products = productQueryService.getProducts(sortBy, orderBy, page, size, categoryId);

        return products.stream()
                .map(product -> {
                    var productDto = productMapper.toDto(product);
                    productDto.setPhoto(productMapper.toDto(product.getFirstPhoto(), imageUrlBuilder));
                    return productDto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        var product = productQueryService.getProduct(id);
        return productMapper.toDto(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        var category = categoryQueryService.getCategory(productDto.getCategory());

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);

        productService.createProduct(product);
        productDto.setId(product.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }
}
