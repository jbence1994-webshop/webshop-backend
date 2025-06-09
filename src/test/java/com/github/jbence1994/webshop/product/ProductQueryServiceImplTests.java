package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductQueryServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductQueryServiceImpl productQueryService;

    private static Stream<Arguments> sortByParams() {
        return Stream.of(
                Arguments.of("SortBy and orderBy are null", null, null, -1, 20),
                Arguments.of("SortBy and orderBy are empty", "", "", 0, 20),
                Arguments.of("SortBy and orderBy are an unknown property", "testValue", "testValue", 1, 20),
                Arguments.of("SortBy 'id', orderBy 'asc'", "id", "asc", 2, 20),
                Arguments.of("SortBy 'id', orderBy 'desc'", "id", "desc", -1, 20),
                Arguments.of("SortBy 'price', orderBy 'asc'", "price", "asc", 0, 20),
                Arguments.of("SortBy 'price', orderBy 'desc'", "price", "desc", 1, 20)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("sortByParams")
    public void getProductsTest(
            String testCase,
            String sortBy,
            String orderBy,
            int page,
            int size
    ) {
        var products = List.of(product1(), product2());
        var productsPage = new PageImpl<>(products, PageRequest.of(0, 20), products.size());
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(productsPage);

        var result = productQueryService.getProducts(sortBy, orderBy, page, size);

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void getProductTest_HappyPath() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product1()));

        var result = assertDoesNotThrow(() -> productQueryService.getProduct(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, samePropertyValuesAs(product1()));
    }

    @Test
    public void getProductTest_UnhappyPath() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                ProductNotFoundException.class,
                () -> productQueryService.getProduct(1L)
        );

        assertThat(result.getMessage(), equalTo("No product was found with the given ID: #1."));
    }

    @Test
    public void isProductExistByIdTest_HappyPath() {
        when(productRepository.existsById(any())).thenReturn(true);

        assertThat(productQueryService.isProductExistById(1L), is(true));
    }

    @Test
    public void isProductExistByIdTest_UnhappyPath() {
        when(productRepository.existsById(any())).thenReturn(false);

        assertThat(productQueryService.isProductExistById(1L), is(false));
    }
}
