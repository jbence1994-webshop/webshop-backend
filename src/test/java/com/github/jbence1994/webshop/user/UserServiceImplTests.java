package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.EmailService;
import com.github.jbence1994.webshop.common.EmailTemplateBuilder;
import com.github.jbence1994.webshop.common.WebshopEmailAddressConfig;
import com.github.jbence1994.webshop.product.ProductAlreadyOnWishlistException;
import com.github.jbence1994.webshop.product.ProductQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.common.EmailContentTestObject.emailContent;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.HASHED_TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.expiredTemporaryPassword;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.notExpiredTemporaryPassword;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithFavoriteProducts;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    private TemporaryPasswordRepository temporaryPasswordRepository;

    @Mock
    private TemporaryPasswordGenerator temporaryPasswordGenerator;

    @Mock
    private WebshopEmailAddressConfig webshopEmailAddressConfig;

    @Mock
    private EmailTemplateBuilder emailTemplateBuilder;

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private PasswordManager passwordManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserServiceImpl userService;

    private final User user = user1WithoutAvatar();

    @Test
    public void registerUserTest_HappyPath() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(passwordManager.hash(any())).thenReturn(HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user1WithoutAvatar());

        assertDoesNotThrow(() -> userService.registerUser(user1WithoutAvatar()));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_EmailAlreadyExistsException() {
        when(userRepository.existsByEmail(any())).thenReturn(true);

        var result = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(user1WithoutAvatar())
        );

        assertThat(result.getMessage(), equalTo("Email address is already in use."));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, never()).existsByPhoneNumber(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_PhoneNumberAlreadyExistsException() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(true);

        var result = assertThrows(
                PhoneNumberAlreadyExistsException.class,
                () -> userService.registerUser(user1WithoutAvatar())
        );

        assertThat(result.getMessage(), equalTo("Phone number is already registered."));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, times(1)).existsByPhoneNumber(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void changePasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(passwordManager.hash(any())).thenReturn(NEW_HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user1WithoutAvatar());

        assertDoesNotThrow(() -> userService.changePassword(OLD_PASSWORD, NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void changePasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
        when(passwordManager.verify(any(), any())).thenReturn(false);

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.changePassword(INVALID_OLD_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid old password."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void forgotPasswordTest() {
        when(userQueryService.getUser(anyString())).thenReturn(user1WithoutAvatar());
        when(temporaryPasswordGenerator.generate()).thenReturn(TEMPORARY_PASSWORD);
        when(passwordManager.hash(any())).thenReturn(HASHED_TEMPORARY_PASSWORD);
        when(temporaryPasswordRepository.save(any())).thenReturn(notExpiredTemporaryPassword());
        when(emailTemplateBuilder.buildForForgotPassword(any(), any(), any())).thenReturn(emailContent());
        when(webshopEmailAddressConfig.username()).thenReturn("from@example.com");
        doNothing().when(emailService).sendEmail(any(), any(), any(), any());

        assertDoesNotThrow(() -> userService.forgotPassword(EMAIL_1));

        verify(userQueryService, times(1)).getUser(anyString());
        verify(temporaryPasswordGenerator, times(1)).generate();
        verify(passwordManager, times(1)).hash(any());
        verify(temporaryPasswordRepository, times(1)).save(any());
        verify(emailTemplateBuilder, times(1)).buildForForgotPassword(any(), any(), any());
        verify(webshopEmailAddressConfig, times(1)).username();
        verify(emailService, times(1)).sendEmail(any(), any(), any(), any());
    }

    @Test
    public void resetPasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
        when(temporaryPasswordRepository.findAllByUserId(any())).thenReturn(List.of(expiredTemporaryPassword(), notExpiredTemporaryPassword()));
        when(temporaryPasswordRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(notExpiredTemporaryPassword()));
        doNothing().when(temporaryPasswordRepository).deleteAll(any());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(passwordManager.hash(any())).thenReturn(NEW_HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user1WithoutAvatar());
        doNothing().when(temporaryPasswordRepository).delete(any());

        assertDoesNotThrow(() -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(temporaryPasswordRepository, times(1)).findAllByUserId(any());
        verify(temporaryPasswordRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(temporaryPasswordRepository, times(1)).deleteAll(any());
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
        verify(temporaryPasswordRepository, times(1)).delete(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
        when(temporaryPasswordRepository.findAllByUserId(any())).thenReturn(List.of(expiredTemporaryPassword(), notExpiredTemporaryPassword()));
        when(temporaryPasswordRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(notExpiredTemporaryPassword()));
        doNothing().when(temporaryPasswordRepository).deleteAll(any());
        when(passwordManager.verify(any(), any())).thenReturn(false);

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid temporary password."));

        verify(authService, times(1)).getCurrentUser();
        verify(temporaryPasswordRepository, times(1)).findAllByUserId(any());
        verify(temporaryPasswordRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(temporaryPasswordRepository, times(1)).deleteAll(any());
        verify(temporaryPasswordRepository, never()).delete(any());
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_ExpiredTemporaryPasswordException() {
        when(authService.getCurrentUser()).thenReturn(user1WithoutAvatar());
        when(temporaryPasswordRepository.findAllByUserId(any())).thenReturn(List.of(expiredTemporaryPassword()));
        when(temporaryPasswordRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(expiredTemporaryPassword()));
        doNothing().when(temporaryPasswordRepository).deleteAll(any());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        doNothing().when(temporaryPasswordRepository).delete(any());

        var result = assertThrows(
                ExpiredTemporaryPasswordException.class,
                () -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Temporary password has expired."));

        verify(authService, times(1)).getCurrentUser();
        verify(temporaryPasswordRepository, times(1)).findAllByUserId(any());
        verify(temporaryPasswordRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(temporaryPasswordRepository, times(1)).deleteAll(any());
        verify(passwordManager, times(1)).verify(any(), any());
        verify(temporaryPasswordRepository, times(1)).delete(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void updateUserTest() {
        when(userRepository.save(any())).thenReturn(user1WithoutAvatar());

        assertDoesNotThrow(() -> userService.updateUser(user1WithoutAvatar()));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(any());

        assertDoesNotThrow(() -> userService.deleteUser(any()));
    }

    @Test
    public void addProductToWishlistTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user);
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(userRepository.save(any())).thenReturn(user1WithFavoriteProducts());

        var result = userService.addProductToWishlist(1L);

        assertThat(result.getId(), equalTo(product1().getId()));
        assertThat(user.getFavoriteProducts().size(), equalTo(1));

        verify(authService, times(1)).getCurrentUser();
        verify(productQueryService, times(1)).getProduct(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void addProductToWishlistTest_UnhappyPath_ProductAlreadyOnWishlistException() {
        when(authService.getCurrentUser()).thenReturn(user);
        when(productQueryService.getProduct(any())).thenReturn(product1());
        doThrow(new ProductAlreadyOnWishlistException(1L)).when(userRepository).save(any());

        var result = assertThrows(
                ProductAlreadyOnWishlistException.class,
                () -> userService.addProductToWishlist(1L)
        );

        assertThat(result.getMessage(), equalTo("This product with the given ID: #1 is already on your wishlist."));

        verify(authService, times(1)).getCurrentUser();
        verify(productQueryService, times(1)).getProduct(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void deleteProductFromWishlistTest() {
        when(authService.getCurrentUser()).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user1WithoutAvatar());

        userService.deleteProductFromWishlist(1L);

        assertThat(user.getFavoriteProducts().size(), equalTo(0));

        verify(authService, times(1)).getCurrentUser();
        verify(userRepository, times(1)).save(any());
    }
}
