package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.product.AddProductToWishlistRequestTestObject.addProductToWishlistRequest;
import static com.github.jbence1994.webshop.product.DeleteProductFromWishlistRequestTestObject.deleteProductFromWishlistRequest;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.WishlistProductDtoTestObject.wishlistProductDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTests {

    @Mock
    private WishlistService wishlistService;

    @Mock
    private WishlistMapper wishlistMapper;

    @InjectMocks
    private WishlistController wishlistController;

    @Test
    public void addProductToWishlistTest() {
        when(wishlistService.addProductToWishlist(any())).thenReturn(product1());
        when(wishlistMapper.toDto(any())).thenReturn(wishlistProductDto());

        var result = wishlistController.addProductToWishlist(addProductToWishlistRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().id(), equalTo(wishlistProductDto().id()));
    }

    @Test
    public void deleteProductFromWishlistTest() {
        doNothing().when(wishlistService).deleteProductFromWishlist(any());

        var result = wishlistController.deleteProductFromWishlist(deleteProductFromWishlistRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
