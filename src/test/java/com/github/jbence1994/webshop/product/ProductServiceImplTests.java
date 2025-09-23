package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithRating;
import static com.github.jbence1994.webshop.product.RateProductResponseTestObject.rateProductResponse;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AuthService authService;

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

    @Test
    public void rateProductTest() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(authService.getCurrentUser()).thenReturn(user());
        when(productRepository.save(any())).thenReturn(product1WithRating());

        var result = productService.rateProduct(1L, (byte) 5);

        assertThat(result.productId(), equalTo(rateProductResponse().productId()));
        assertThat(result.yourRating(), equalTo(rateProductResponse().yourRating()));
        assertThat(result.averageRating(), equalTo(rateProductResponse().averageRating()));
        assertThat(result.totalRatings(), equalTo(rateProductResponse().totalRatings()));
    }
}
