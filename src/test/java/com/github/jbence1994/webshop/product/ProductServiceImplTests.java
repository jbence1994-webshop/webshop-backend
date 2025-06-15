package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void createProductTest() {
        when(productRepository.save(any())).thenReturn(product1());

        assertDoesNotThrow(() -> productService.createProduct(product1()));
    }

    @Test
    public void updateProductTest() {
        when(productRepository.save(any())).thenReturn(product1());

        assertDoesNotThrow(() -> productService.updateProduct(product1()));
    }
}
