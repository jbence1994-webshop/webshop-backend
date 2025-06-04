package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.product.CreateProductDtoTestObject.createProductDto1;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDto1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithNullId;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    public void getProductsTest() {
        when(productQueryService.getProducts(any(), any())).thenReturn(List.of(product1(), product2()));

        var result = productController.getProducts("id", "asc");

        assertEquals(2, result.size());
    }

    @Test
    public void getProductTest_HappyPath() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(productMapper.toDto(any())).thenReturn(productDto1());

        var result = productController.getProduct(1L);

        assertEquals(product1().getId(), result.getId());
        assertEquals(product1().getName(), result.getName());
        assertEquals(product1().getPrice(), result.getPrice());
        assertEquals(product1().getUnit(), result.getUnit());
        assertEquals(product1().getDescription(), result.getDescription());
    }

    @Test
    public void getProductTest_UnhappyPath_ProductNotFoundException() {
        when(productQueryService.getProduct(any())).thenThrow(new ProductNotFoundException(1L));

        var result = assertThrows(
                ProductNotFoundException.class,
                () -> productController.getProduct(1L)
        );

        assertEquals("No product was found with the given ID: #1.", result.getMessage());
    }

    @Test
    public void createProductTest() {
        when(productMapper.toEntity(any())).thenReturn(product1WithNullId());
        when(productService.createProduct(any())).thenReturn(product1());
        when(productMapper.toDto(any())).thenReturn(productDto1());

        var result = productController.createProduct(createProductDto1());

        assertEquals(product1().getId(), result.getId());
        assertEquals(product1().getName(), result.getName());
        assertEquals(product1().getPrice(), result.getPrice());
        assertEquals(product1().getUnit(), result.getUnit());
        assertEquals(product1().getDescription(), result.getDescription());
    }
}
