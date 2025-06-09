package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
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

        assertThat(result, samePropertyValuesAs(product1()));
    }

    @Test
    public void updateProductTest_HappyPath() {
        when(productRepository.save(any())).thenReturn(product1());

        productService.updateProduct(product1());

        verify(productRepository, times(1)).save(any());
    }
}
