package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.AddProductToWishlistRequestTestObject.addProductToWishlistRequest;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequest;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.notSanitizedChangePasswordRequest;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestObject.decryptedAddressAfterMappingFromDto;
import static com.github.jbence1994.webshop.user.DecryptedUserTestObject.decryptedUser1AfterMappingFromDto;
import static com.github.jbence1994.webshop.user.DecryptedUserTestObject.decryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.DeleteProductFromWishlistRequestTestObject.deleteProductFromWishlistRequest;
import static com.github.jbence1994.webshop.user.ForgotPasswordRequestTestObject.forgotPasswordRequest;
import static com.github.jbence1994.webshop.user.ForgotPasswordRequestTestObject.notSanitizedForgotPasswordRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.registrationRequest;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.notSanitizedResetPasswordRequest;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequest;
import static com.github.jbence1994.webshop.user.UserDtoTestObject.userDto;
import static com.github.jbence1994.webshop.user.WishlistProductDtoTestObject.wishlistProductDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private ChangePasswordRequestSanitizer changePasswordRequestSanitizer;

    @Mock
    private ForgotPasswordRequestSanitizer forgotPasswordRequestSanitizer;

    @Mock
    private ResetPasswordRequestSanitizer resetPasswordRequestSanitizer;

    @Mock
    private RegistrationRequestSanitizer registrationRequestSanitizer;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void getUserTest_HappyPath() {
        when(userQueryService.getDecryptedUser(anyLong())).thenReturn(decryptedUser1WithoutAvatar());
        when(userMapper.toDto(any(DecryptedUser.class))).thenReturn(userDto());

        var result = userController.getUser(1L);

        assertThat(result.id(), equalTo(userDto().id()));
        assertThat(result.email(), equalTo(userDto().email()));
        assertThat(result.firstName(), equalTo(userDto().firstName()));
        assertThat(result.middleName(), equalTo(userDto().middleName()));
        assertThat(result.lastName(), equalTo(userDto().lastName()));
        assertThat(result.dateOfBirth(), equalTo(userDto().dateOfBirth()));
        assertThat(result.phoneNumber(), equalTo(userDto().phoneNumber()));
        assertThat(result.address().addressLine(), equalTo(userDto().address().addressLine()));
        assertThat(result.address().municipality(), equalTo(userDto().address().municipality()));
        assertThat(result.address().province(), equalTo(userDto().address().province()));
        assertThat(result.address().postalCode(), equalTo(userDto().address().postalCode()));
        assertThat(result.address().country(), equalTo(userDto().address().country()));
    }

    @Test
    public void getUserTest_UnhappyPath_UserNotFoundException() {
        when(userQueryService.getDecryptedUser(anyLong())).thenThrow(new UserNotFoundException(1L));

        var result = assertThrows(UserNotFoundException.class, () -> userController.getUser(1L));

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }

    @Test
    public void registerUserTest() {
        when(registrationRequestSanitizer.sanitize(any())).thenReturn(registrationRequest());
        when(userMapper.toEntity(any(RegistrationRequest.AddressDto.class))).thenReturn(decryptedAddressAfterMappingFromDto());
        when(userMapper.toEntity(any(RegistrationRequest.UserDto.class))).thenReturn(decryptedUser1AfterMappingFromDto());
        doNothing().when(userService).registerUser(any());

        var result = userController.registerUser(notSanitizedRegistrationRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void changePasswordTest() {
        when(changePasswordRequestSanitizer.sanitize(any())).thenReturn(changePasswordRequest());

        var result = userController.changePassword(notSanitizedChangePasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void forgotPasswordTest() {
        when(forgotPasswordRequestSanitizer.sanitize(any())).thenReturn(forgotPasswordRequest());

        var result = userController.forgotPassword(notSanitizedForgotPasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.ACCEPTED));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void resetPasswordTest() {
        when(resetPasswordRequestSanitizer.sanitize(any())).thenReturn(resetPasswordRequest());

        var result = userController.resetPassword(notSanitizedResetPasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userService).deleteUser(any());

        var result = userController.deleteUser(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void getWishlistTest() {
        when(userQueryService.getWishlist()).thenReturn(List.of(product1(), product2()));
        when(userMapper.toDto(any(Product.class))).thenReturn(wishlistProductDto());

        var result = userController.getWishlist();

        assertThat(result, not(nullValue()));
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void addProductToWishlistTest() {
        when(userService.addProductToWishlist(any())).thenReturn(product1());
        when(userMapper.toDto(any(Product.class))).thenReturn(wishlistProductDto());

        var result = userController.addProductToWishlist(addProductToWishlistRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().id(), equalTo(wishlistProductDto().id()));

        verify(userService, times(1)).addProductToWishlist(any());
        verify(userMapper, times(1)).toDto(any(Product.class));
    }

    @Test
    public void deleteProductFromWishlistTest() {
        doNothing().when(userService).deleteProductFromWishlist(any());

        var result = userController.deleteProductFromWishlist(deleteProductFromWishlistRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));

        verify(userService, times(1)).deleteProductFromWishlist(any());
    }
}
