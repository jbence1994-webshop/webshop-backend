package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestObject.decryptedAddress;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestObject.decryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithFavoriteProducts;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDecrypter userDecrypter;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    public void getEncryptedUserTest_HappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.of(encryptedUser1WithoutAvatar()));

        var result = assertDoesNotThrow(() -> userQueryService.getEncryptedUser(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(encryptedUser1WithoutAvatar().getId())),
                hasProperty("email", equalTo(encryptedUser1WithoutAvatar().getEmail())),
                hasProperty("password", equalTo(encryptedUser1WithoutAvatar().getPassword())),
                hasProperty("role", equalTo(encryptedUser1WithoutAvatar().getRole()))
        ));
    }

    @Test
    public void getEncryptedUserTest_UnhappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userQueryService.getEncryptedUser(1L)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }

    @Test
    public void getDecryptedUserTest() {
        when(userRepository.findById(any())).thenReturn(Optional.of(encryptedUser1WithoutAvatar()));
        when(userDecrypter.decrypt(any(EncryptedAddress.class), any())).thenReturn(decryptedAddress());
        when(userDecrypter.decrypt(any(EncryptedUser.class), any())).thenReturn(decryptedUser1WithoutAvatar());

        var result = assertDoesNotThrow(() -> userQueryService.getDecryptedUser(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(decryptedUser1WithoutAvatar().getId())),
                hasProperty("email", equalTo(decryptedUser1WithoutAvatar().getEmail())),
                hasProperty("password", equalTo(decryptedUser1WithoutAvatar().getPassword())),
                hasProperty("role", equalTo(decryptedUser1WithoutAvatar().getRole()))
        ));
    }

    @Test
    public void getUserTest_HappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(encryptedUser1WithoutAvatar()));

        var result = assertDoesNotThrow(() -> userQueryService.getUser(DECRYPTED_EMAIL_1));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(encryptedUser1WithoutAvatar().getId())),
                hasProperty("email", equalTo(encryptedUser1WithoutAvatar().getEmail())),
                hasProperty("password", equalTo(encryptedUser1WithoutAvatar().getPassword())),
                hasProperty("role", equalTo(encryptedUser1WithoutAvatar().getRole()))
        ));
    }

    @Test
    public void getUserTest_UnhappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userQueryService.getUser(DECRYPTED_EMAIL_1)
        );

        assertThat(result.getMessage(), equalTo("No user was found."));
    }

    @Test
    public void getWishlistTest() {
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithFavoriteProducts());

        var result = assertDoesNotThrow(() -> userQueryService.getWishlist());

        assertThat(result, not(nullValue()));
        assertThat(result.size(), equalTo(1));
    }
}
