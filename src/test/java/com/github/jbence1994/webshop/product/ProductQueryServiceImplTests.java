package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductQueryServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductQueryServiceImpl productQueryService;

    @Test
    public void getProductsTest() {
        when(productRepository.findAll()).thenReturn(List.of(product1(), product2()));

        var result = productQueryService.getProducts();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getProductTest_HappyPath() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product1()));

        var result = assertDoesNotThrow(() -> productQueryService.getProduct(1L));

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void getProductTest_UnhappyPath() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                ProductNotFoundException.class,
                () -> productQueryService.getProduct(1L)
        );

        assertEquals("No product was found with the given ID: #1.", result.getMessage());
    }

    @Test
    public void isProductExistByIdTest_HappyPath() {
        when(productRepository.existsById(any())).thenReturn(true);

        assertTrue(productQueryService.isProductExistById(1L));
    }

    @Test
    public void isProductExistByIdTest_UnhappyPath() {
        when(productRepository.existsById(any())).thenReturn(false);

        assertFalse(productQueryService.isProductExistById(1L));
    }
}
