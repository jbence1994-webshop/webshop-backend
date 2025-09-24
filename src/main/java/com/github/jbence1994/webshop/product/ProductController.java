package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CategoryQueryService categoryQueryService;
    private final ProductQueryService productQueryService;
    private final ProductDtoSanitizer productDtoSanitizer;
    private final ImageUrlBuilder imageUrlBuilder;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(
            final CategoryQueryService categoryQueryService,
            final ProductQueryService productQueryService,
            final ProductDtoSanitizer productDtoSanitizer,
            @Qualifier("productPhotoUrlBuilder") final ImageUrlBuilder imageUrlBuilder,
            final ProductService productService,
            final ProductMapper productMapper
    ) {
        this.categoryQueryService = categoryQueryService;
        this.productQueryService = productQueryService;
        this.productDtoSanitizer = productDtoSanitizer;
        this.imageUrlBuilder = imageUrlBuilder;
        this.productService = productService;
        this.productMapper = productMapper;
    }

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
                    product.getFirstPhoto()
                            .map(photo -> productMapper.toDto(photo, imageUrlBuilder))
                            .ifPresent(productDto::setPhoto);
                    return productDto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        var product = productQueryService.getProduct(id);

        var productDto = productMapper.toDto(product);
        product.getFirstPhoto()
                .map(photo -> productMapper.toDto(photo, imageUrlBuilder))
                .ifPresent(productDto::setPhoto);

        return productDto;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        var sanitizedProductDto = productDtoSanitizer.sanitize(productDto);

        var category = categoryQueryService.getCategory(sanitizedProductDto.getCategory());

        var product = productMapper.toEntity(sanitizedProductDto);
        product.setCategory(category);

        productService.createProduct(product);
        sanitizedProductDto.setId(product.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(sanitizedProductDto);
    }

    @PostMapping("{id}/rating")
    public ResponseEntity<ProductRatingResponse> createProductRating(
            @PathVariable Long id,
            @Valid @RequestBody CreateProductRatingRequest request
    ) {
        var productRating = productService.createProductRating(id, request.getRateValue());

        return ResponseEntity.status(HttpStatus.CREATED).body(productRating);
    }

    @PutMapping("{id}/rating")
    public ProductRatingResponse updateProductRating(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRatingRequest request
    ) {
        return productService.updateProductRating(id, request.getRateValue());
    }
}
