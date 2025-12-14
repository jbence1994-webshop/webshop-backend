package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.CryptoService;
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
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_INVALID_OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestObject.decryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.EncryptedBillingAddressTestObject.encryptedBillingAddress;
import static com.github.jbence1994.webshop.user.EncryptedShippingAddressTestObject.encryptedShippingAddress;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.HASHED_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithFavoriteProducts;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.INVALID_RECOVERY_CODE;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.RECOVERY_CODE;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestObject.expiredRecoveryCode;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestObject.notExpiredRecoveryCode;
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
    private WebshopEmailAddressConfig webshopEmailAddressConfig;

    @Mock
    private RecoveryCodeRepository recoveryCodeRepository;

    @Mock
    private RecoveryCodeGenerator recoveryCodeGenerator;

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
    private CryptoService cryptoService;

    @Mock
    private UserEncrypter userEncrypter;

    @Mock
    private EmailService emailService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserServiceImpl userService;

    private final EncryptedUser user = encryptedUser1WithoutAvatar();

    @Test
    public void registerUserTest_HappyPath() {
        when(userEncrypter.encrypt(any(DecryptedBillingAddress.class), any())).thenReturn(encryptedBillingAddress());
        when(userEncrypter.encrypt(any(DecryptedShippingAddress.class), any())).thenReturn(encryptedShippingAddress());
        when(userEncrypter.encrypt(any(DecryptedUser.class), any())).thenReturn(encryptedUser1WithoutAvatar());
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(passwordManager.hash(any())).thenReturn(HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(encryptedUser1WithoutAvatar());

        assertDoesNotThrow(() -> userService.registerUser(decryptedUser1WithoutAvatar()));

        verify(userEncrypter, times(1)).encrypt(any(DecryptedBillingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedShippingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedUser.class), any());
        verify(userRepository, times(1)).existsByEmail(any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_EmailAlreadyExistsException() {
        when(userEncrypter.encrypt(any(DecryptedBillingAddress.class), any())).thenReturn(encryptedBillingAddress());
        when(userEncrypter.encrypt(any(DecryptedShippingAddress.class), any())).thenReturn(encryptedShippingAddress());
        when(userEncrypter.encrypt(any(DecryptedUser.class), any())).thenReturn(encryptedUser1WithoutAvatar());
        when(userRepository.existsByEmail(any())).thenReturn(true);

        var result = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(decryptedUser1WithoutAvatar())
        );

        assertThat(result.getMessage(), equalTo("Email address is already in use."));

        verify(userEncrypter, times(1)).encrypt(any(DecryptedBillingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedShippingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedUser.class), any());
        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, never()).existsByPhoneNumber(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_PhoneNumberAlreadyExistsException() {
        when(userEncrypter.encrypt(any(DecryptedBillingAddress.class), any())).thenReturn(encryptedBillingAddress());
        when(userEncrypter.encrypt(any(DecryptedShippingAddress.class), any())).thenReturn(encryptedShippingAddress());
        when(userEncrypter.encrypt(any(DecryptedUser.class), any())).thenReturn(encryptedUser1WithoutAvatar());
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(true);

        var result = assertThrows(
                PhoneNumberAlreadyExistsException.class,
                () -> userService.registerUser(decryptedUser1WithoutAvatar())
        );

        assertThat(result.getMessage(), equalTo("Phone number is already registered."));

        verify(userEncrypter, times(1)).encrypt(any(DecryptedBillingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedShippingAddress.class), any());
        verify(userEncrypter, times(1)).encrypt(any(DecryptedUser.class), any());
        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, times(1)).existsByPhoneNumber(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void changePasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(passwordManager.hash(any())).thenReturn(HASHED_NEW_PASSWORD);
        when(userRepository.save(any())).thenReturn(encryptedUser1WithoutAvatar());

        assertDoesNotThrow(() -> userService.changePassword(RAW_OLD_PASSWORD, RAW_NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void changePasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(passwordManager.verify(any(), any())).thenReturn(false);

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.changePassword(RAW_INVALID_OLD_PASSWORD, RAW_NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid old password."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void forgotPasswordTest() {
        when(cryptoService.encrypt(any())).thenReturn(ENCRYPTED_EMAIL_1);
        when(userQueryService.getUser(anyString())).thenReturn(encryptedUser1WithoutAvatar());
        when(recoveryCodeGenerator.generate()).thenReturn(RECOVERY_CODE);
        when(recoveryCodeRepository.save(any())).thenReturn(notExpiredRecoveryCode());
        when(emailTemplateBuilder.buildForForgotPassword(any(), any(), any())).thenReturn(emailContent());
        when(webshopEmailAddressConfig.username()).thenReturn("from@example.com");
        doNothing().when(emailService).sendEmail(any(), any(), any(), any());

        assertDoesNotThrow(() -> userService.forgotPassword(DECRYPTED_EMAIL_1));

        verify(cryptoService, times(1)).encrypt(any());
        verify(userQueryService, times(1)).getUser(anyString());
        verify(recoveryCodeGenerator, times(1)).generate();
        verify(recoveryCodeRepository, times(1)).save(any());
        verify(emailTemplateBuilder, times(1)).buildForForgotPassword(any(), any(), any());
        verify(webshopEmailAddressConfig, times(1)).username();
        verify(emailService, times(1)).sendEmail(any(), any(), any(), any());
    }

    @Test
    public void resetPasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(recoveryCodeRepository.findAllByUserId(any())).thenReturn(List.of(expiredRecoveryCode(), notExpiredRecoveryCode()));
        when(recoveryCodeRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(notExpiredRecoveryCode()));
        doNothing().when(recoveryCodeRepository).deleteAll(any());
        when(passwordManager.hash(any())).thenReturn(HASHED_NEW_PASSWORD);
        when(userRepository.save(any())).thenReturn(encryptedUser1WithoutAvatar());
        doNothing().when(recoveryCodeRepository).delete(any());

        assertDoesNotThrow(() -> userService.resetPassword(RECOVERY_CODE, RAW_NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(recoveryCodeRepository, times(1)).findAllByUserId(any());
        verify(recoveryCodeRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(recoveryCodeRepository, times(1)).deleteAll(any());
        verify(passwordManager, times(1)).hash(any());
        verify(userRepository, times(1)).save(any());
        verify(recoveryCodeRepository, times(1)).delete(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(recoveryCodeRepository.findAllByUserId(any())).thenReturn(List.of(expiredRecoveryCode(), notExpiredRecoveryCode()));
        when(recoveryCodeRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(notExpiredRecoveryCode()));
        doNothing().when(recoveryCodeRepository).deleteAll(any());

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.resetPassword(INVALID_RECOVERY_CODE, RAW_NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid recovery code."));

        verify(authService, times(1)).getCurrentUser();
        verify(recoveryCodeRepository, times(1)).findAllByUserId(any());
        verify(recoveryCodeRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(recoveryCodeRepository, times(1)).deleteAll(any());
        verify(recoveryCodeRepository, never()).delete(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_ExpiredRecoveryCodeException() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(recoveryCodeRepository.findAllByUserId(any())).thenReturn(List.of(expiredRecoveryCode()));
        when(recoveryCodeRepository.findTopByUserIdOrderByExpirationDateDesc(any())).thenReturn(Optional.of(expiredRecoveryCode()));
        doNothing().when(recoveryCodeRepository).deleteAll(any());
        doNothing().when(recoveryCodeRepository).delete(any());

        var result = assertThrows(
                ExpiredRecoveryCodeException.class,
                () -> userService.resetPassword(RECOVERY_CODE, RAW_NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Recovery code has expired."));

        verify(authService, times(1)).getCurrentUser();
        verify(recoveryCodeRepository, times(1)).findAllByUserId(any());
        verify(recoveryCodeRepository, times(1)).findTopByUserIdOrderByExpirationDateDesc(any());
        verify(recoveryCodeRepository, times(1)).deleteAll(any());
        verify(recoveryCodeRepository, times(1)).delete(any());
        verify(passwordManager, never()).hash(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void updateUserTest() {
        when(userRepository.save(any())).thenReturn(encryptedUser1WithoutAvatar());

        assertDoesNotThrow(() -> userService.updateUser(encryptedUser1WithoutAvatar()));
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
        when(userRepository.save(any())).thenReturn(encryptedUser1WithFavoriteProducts());

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
        when(userRepository.save(any())).thenReturn(encryptedUser1WithoutAvatar());

        userService.deleteProductFromWishlist(1L);

        assertThat(user.getFavoriteProducts().size(), equalTo(0));

        verify(authService, times(1)).getCurrentUser();
        verify(userRepository, times(1)).save(any());
    }
}
