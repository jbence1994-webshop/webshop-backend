package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.product.CategoryTestObject.category1;
import static com.github.jbence1994.webshop.product.CreateProductRatingRequestTestObject.createProductRatingRequest;
import static com.github.jbence1994.webshop.product.CreateProductReviewRequestTestObject.createProductReviewRequest;
import static com.github.jbence1994.webshop.product.CreateProductReviewRequestTestObject.notSanitizedCreateProductReviewRequest;
import static com.github.jbence1994.webshop.product.ProductByIdDtoTestObject.productByIdDtoWithPhoto;
import static com.github.jbence1994.webshop.product.ProductByIdDtoTestObject.productByIdDtoWithoutPhoto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.notSanitizedProductDto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDtoWithNullIdAndNullPhoto;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductRatingResponseTestObject.productRatingResponse;
import static com.github.jbence1994.webshop.product.ProductRatingResponseTestObject.updatedProductRatingResponse;
import static com.github.jbence1994.webshop.product.ProductReviewResponseTestObject.productReviewResponse;
import static com.github.jbence1994.webshop.product.ProductReviewSummaryResponseTestObject.productReviewSummaryResponse;
import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.notExpiredProductReviewSummary;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_SUMMARY;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1AfterMappingFromDto;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithOneReview;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithPhotos;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithRating;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithUpdatedRating;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2WithPhotos;
import static com.github.jbence1994.webshop.product.UpdateProductRatingRequestTestObject.updateProductRatingRequest;
import static com.github.jbence1994.webshop.product.WishlistProductDtoTestObject.wishlistProductDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductControllerTests {

    @Mock
    private CreateProductReviewRequestSanitizer createProductReviewRequestSanitizer;

    @Mock
    private CategoryQueryService categoryQueryService;

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductDtoSanitizer productDtoSanitizer;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    public void getProductsTest_ProductsWithoutPhotos() {
        when(productQueryService.getProducts(anyString(), anyString(), anyInt(), anyInt(), anyByte())).thenReturn(List.of(product1(), product2()));
        when(productMapper.toProductDto(any(Product.class))).thenReturn(productDto());

        byte categoryId = 1;
        var result = productController.getProducts("id", "asc", 0, 20, categoryId);

        assertThat(result.size(), equalTo(2));

        verify(productQueryService, times(1)).getProducts(anyString(), anyString(), anyInt(), anyInt(), anyByte());
        verify(productMapper, times(2)).toProductDto(any(Product.class));
        verify(productMapper, never()).toProductPhotoDto(any(), any());
    }

    @Test
    public void getProductsTest_ProductsWithPhotos() {
        when(productQueryService.getProducts(anyString(), anyString(), anyInt(), anyInt(), anyByte())).thenReturn(List.of(product1WithPhotos(), product2WithPhotos()));
        when(productMapper.toProductDto(any(Product.class))).thenReturn(productDto());
        when(productMapper.toProductPhotoDto(any(), any())).thenReturn(productPhotoDto());

        byte categoryId = 1;
        var result = productController.getProducts("id", "asc", 0, 20, categoryId);

        assertThat(result.size(), equalTo(2));

        verify(productQueryService, times(1)).getProducts(anyString(), anyString(), anyInt(), anyInt(), anyByte());
        verify(productMapper, times(2)).toProductDto(any(Product.class));
        verify(productMapper, times(2)).toProductPhotoDto(any(), any());
    }

    @Test
    public void getProductTest_HappyPath_ProductWithoutPhoto() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(productMapper.toProductByIdDto(any(Product.class))).thenReturn(productByIdDtoWithoutPhoto());

        var result = productController.getProduct(1L);

        assertThat(result, allOf(
                hasProperty("id", equalTo(productByIdDtoWithoutPhoto().getId())),
                hasProperty("name", equalTo(productByIdDtoWithoutPhoto().getName())),
                hasProperty("price", equalTo(productByIdDtoWithoutPhoto().getPrice())),
                hasProperty("unit", equalTo(productByIdDtoWithoutPhoto().getUnit())),
                hasProperty("description", equalTo(productByIdDtoWithoutPhoto().getDescription())),
                hasProperty("photo", equalTo(productByIdDtoWithoutPhoto().getPhoto()))
        ));

        verify(productQueryService, times(1)).getProduct(any());
        verify(productMapper, times(1)).toProductByIdDto(any(Product.class));
        verify(productMapper, never()).toProductPhotoDto(any(), any());
    }

    @Test
    public void getProductTest_HappyPath_ProductWithPhoto() {
        when(productQueryService.getProduct(any())).thenReturn(product1WithPhotos());
        when(productMapper.toProductByIdDto(any(Product.class))).thenReturn(productByIdDtoWithPhoto());
        when(productMapper.toProductPhotoDto(any(), any())).thenReturn(productPhotoDto());

        var result = productController.getProduct(1L);

        assertThat(result, allOf(
                hasProperty("id", equalTo(productByIdDtoWithPhoto().getId())),
                hasProperty("name", equalTo(productByIdDtoWithPhoto().getName())),
                hasProperty("price", equalTo(productByIdDtoWithPhoto().getPrice())),
                hasProperty("unit", equalTo(productByIdDtoWithPhoto().getUnit())),
                hasProperty("description", equalTo(productByIdDtoWithPhoto().getDescription())),
                hasProperty("photo", equalTo(productByIdDtoWithPhoto().getPhoto()))
        ));

        verify(productQueryService, times(1)).getProduct(any());
        verify(productMapper, times(1)).toProductByIdDto(any(Product.class));
        verify(productMapper, times(1)).toProductPhotoDto(any(), any());
    }

    @Test
    public void createProductTest() {
        when(productDtoSanitizer.sanitize(any())).thenReturn(productDtoWithNullIdAndNullPhoto());
        when(categoryQueryService.getCategory(any())).thenReturn(category1());
        when(productMapper.toEntity(any())).thenReturn(product1AfterMappingFromDto());
        doNothing().when(productService).createProduct(any());

        var result = productController.createProduct(notSanitizedProductDto());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("name", equalTo(productDto().getName())),
                hasProperty("price", equalTo(productDto().getPrice())),
                hasProperty("unit", equalTo(productDto().getUnit())),
                hasProperty("description", equalTo(productDto().getDescription())),
                hasProperty("photo", is(nullValue()))
        ));

        verify(productDtoSanitizer, times(1)).sanitize(any());
        verify(categoryQueryService, times(1)).getCategory(any());
        verify(productMapper, times(1)).toEntity(any());
        verify(productService, times(1)).createProduct(any());
    }

    @Test
    public void addProductToWishlistTest() {
        when(productService.addProductToWishlist(any())).thenReturn(product1());
        when(productMapper.toWishlistProductDto(any())).thenReturn(wishlistProductDto());

        var result = productController.addProductToWishlist(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().id(), equalTo(wishlistProductDto().id()));

        verify(productService, times(1)).addProductToWishlist(any());
        verify(productMapper, times(1)).toWishlistProductDto(any());
    }

    @Test
    public void deleteProductFromWishlistTest() {
        doNothing().when(productService).deleteProductFromWishlist(any());

        var result = productController.deleteProductFromWishlist(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));

        verify(productService, times(1)).deleteProductFromWishlist(any());
    }

    @Test
    public void createProductRatingTest() {
        when(productService.createProductRating(any(), any())).thenReturn(product1WithRating());
        when(productMapper.toProductRatingResponse(any(CreateProductRatingRequest.class), any())).thenReturn(productRatingResponse());

        var result = productController.createProductRating(1L, createProductRatingRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().productId(), equalTo(1L));
        assertThat(result.getBody().yourRating(), equalTo((byte) 5));
        assertThat(result.getBody().averageRating(), equalTo(5.0));
        assertThat(result.getBody().totalRatings(), equalTo(1));

        verify(productService, times(1)).createProductRating(any(), any());
        verify(productMapper, times(1)).toProductRatingResponse(any(CreateProductRatingRequest.class), any());
    }

    @Test
    public void updateProductRatingTest() {
        when(productService.updateProductRating(any(), any())).thenReturn(product1WithUpdatedRating());
        when(productMapper.toProductRatingResponse(any(UpdateProductRatingRequest.class), any())).thenReturn(updatedProductRatingResponse());

        var result = productController.updateProductRating(1L, updateProductRatingRequest());

        assertThat(result.productId(), equalTo(1L));
        assertThat(result.yourRating(), equalTo((byte) 4));
        assertThat(result.averageRating(), equalTo(4.0));
        assertThat(result.totalRatings(), equalTo(1));

        verify(productService, times(1)).updateProductRating(any(), any());
        verify(productMapper, times(1)).toProductRatingResponse(any(UpdateProductRatingRequest.class), any());
    }

    @Test
    public void createProductReviewTest() {
        when(createProductReviewRequestSanitizer.sanitize(any())).thenReturn(createProductReviewRequest());
        when(productService.createProductReview(any(), any())).thenReturn(product1WithOneReview());
        when(productMapper.toProductReviewResponse(any(), any())).thenReturn(productReviewResponse());

        var result = productController.createProductReview(1L, notSanitizedCreateProductReviewRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().productId(), equalTo(1L));
        assertThat(result.getBody().review(), equalTo(PRODUCT_1_REVIEW));

        verify(createProductReviewRequestSanitizer, times(1)).sanitize(any());
        verify(productService, times(1)).createProductReview(any(), any());
        verify(productMapper, times(1)).toProductReviewResponse(any(), any());
    }

    @Test
    public void generateProductReviewSummaryTest() {
        when(productService.generateProductReviewSummary(any())).thenReturn(notExpiredProductReviewSummary());
        when(productMapper.toProductReviewSummaryResponse(any())).thenReturn(productReviewSummaryResponse());

        var result = productController.generateProductReviewSummary(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().text(), equalTo(PRODUCT_1_REVIEW_SUMMARY));

        verify(productService, times(1)).generateProductReviewSummary(any());
        verify(productMapper, times(1)).toProductReviewSummaryResponse(any());
    }
}
