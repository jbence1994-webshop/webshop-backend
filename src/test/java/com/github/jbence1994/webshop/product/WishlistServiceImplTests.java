package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.user.User;
import com.github.jbence1994.webshop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    private final User user = user();

    @BeforeEach
    public void setUp() {
        when(authService.getCurrentUser()).thenReturn(user);
        doNothing().when(userService).updateUser(any());
    }

    @Test
    public void addProductToWishlistTest() {
        when(productQueryService.getProduct(any())).thenReturn(product1());

        var result = wishlistService.addProductToWishlist(1L);

        assertThat(result.getId(), equalTo(product1().getId()));
        assertThat(user.getProfile().getFavoriteProducts().size(), equalTo(1));

        verify(authService, times(1)).getCurrentUser();
        verify(productQueryService, times(1)).getProduct(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void deleteProductFromWishlistTest() {
        wishlistService.deleteProductFromWishlist(1L);

        assertThat(user.getProfile().getFavoriteProducts().size(), equalTo(0));

        verify(authService, times(1)).getCurrentUser();
        verify(userService, times(1)).updateUser(any());
    }
}
