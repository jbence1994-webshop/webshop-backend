package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void createProductTest_HappyPath() {
        when(productRepository.save(any())).thenReturn(product1());

        var result = productService.createProduct(product1());

        assertEquals(product1().getId(), result.getId());
        assertEquals(product1().getName(), result.getName());
        assertEquals(product1().getPrice(), result.getPrice());
        assertEquals(product1().getUnit(), result.getUnit());
        assertEquals(product1().getDescription(), result.getDescription());
        assertEquals(product1().getPhotos(), result.getPhotos());
    }

    @Test
    public void updateProductTest_HappyPath() {
        when(productRepository.save(any())).thenReturn(product1());

        productService.updateProduct(product1());

        verify(productRepository, times(1)).save(any());
    }
}
