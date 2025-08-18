package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;
import static com.github.jbence1994.webshop.product.CategoryTestObject.category1;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDtoWithNullId;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1AfterMappingFromDto;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private CategoryQueryService categoryQueryService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    public void getProductsTest() {
        when(productQueryService.getProducts(anyString(), anyString(), anyInt(), anyInt(), anyByte())).thenReturn(List.of(product1(), product2()));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto());
        when(productMapper.toDto(any(), any())).thenReturn(productPhotoDto());

        byte categoryId = 1;
        var result = productController.getProducts("id", "asc", 0, 20, categoryId);

        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void getProductTest_HappyPath() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto());

        var result = productController.getProduct(1L);

        assertThat(result, allOf(
                hasProperty("id", equalTo(productDto().getId())),
                hasProperty("name", equalTo(productDto().getName())),
                hasProperty("price", equalTo(productDto().getPrice())),
                hasProperty("unit", equalTo(productDto().getUnit())),
                hasProperty("description", equalTo(productDto().getDescription()))
        ));
    }

    @Test
    public void createProductTest() {
        when(categoryQueryService.getCategory(any())).thenReturn(category1());
        when(productMapper.toEntity(any())).thenReturn(product1AfterMappingFromDto());
        doNothing().when(productService).createProduct(any());

        var result = productController.createProduct(productDtoWithNullId());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("name", equalTo(productDto().getName())),
                hasProperty("price", equalTo(productDto().getPrice())),
                hasProperty("unit", equalTo(productDto().getUnit())),
                hasProperty("description", equalTo(productDto().getDescription()))
        ));
    }
}
