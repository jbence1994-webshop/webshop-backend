package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.EmailConfig;
import com.github.jbence1994.webshop.common.EmailService;
import com.github.jbence1994.webshop.common.EmailTemplateBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;

import static com.github.jbence1994.webshop.common.EmailContentTestObject.emailContent;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.HASHED_TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.expiredTemporaryPassword;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.notExpiredTemporaryPassword;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.OLD_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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
    private EmailTemplateBuilder emailTemplateBuilder;

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

    @Mock
    private EmailConfig emailConfig;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void registerUserTest_HappyPath() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(passwordManager.encode(any())).thenReturn(HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user());

        assertDoesNotThrow(() -> userService.registerUser(user()));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(passwordManager, times(1)).encode(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_EmailAlreadyExistsException() {
        when(userRepository.existsByEmail(any())).thenReturn(true);

        var result = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(user())
        );

        assertThat(result.getMessage(), equalTo("Email address 'juhasz.bence.zsolt@gmail.com' is already in use. Please use a different."));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, never()).existsByProfilePhoneNumber(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void registerUserTest_UnhappyPath_PhoneNumberAlreadyExistsException() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByProfilePhoneNumber(any())).thenReturn(true);

        var result = assertThrows(
                PhoneNumberAlreadyExistsException.class,
                () -> userService.registerUser(user())
        );

        assertThat(result.getMessage(), equalTo("Phone number '+36501323566' is already registered. Please use a different."));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, times(1)).existsByProfilePhoneNumber(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void changePasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(passwordManager.encode(any())).thenReturn(NEW_HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user());

        assertDoesNotThrow(() -> userService.changePassword(OLD_PASSWORD, NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, times(1)).encode(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void changePasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(false);

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.changePassword(INVALID_OLD_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid old password."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void forgotPasswordTest() {
        when(userQueryService.getUser(anyString())).thenReturn(user());
        when(temporaryPasswordGenerator.generate()).thenReturn(TEMPORARY_PASSWORD);
        when(passwordManager.encode(any())).thenReturn(HASHED_TEMPORARY_PASSWORD);
        when(temporaryPasswordRepository.save(any())).thenReturn(notExpiredTemporaryPassword());
        when(emailTemplateBuilder.buildForForgotPassword(any(), any(), any())).thenReturn(emailContent());
        when(emailConfig.username()).thenReturn("from@example.com");
        doNothing().when(emailService).sendEmail(any(), any(), any(), any());

        assertDoesNotThrow(() -> userService.forgotPassword(EMAIL));

        verify(userQueryService, times(1)).getUser(anyString());
        verify(temporaryPasswordGenerator, times(1)).generate();
        verify(passwordManager, times(1)).encode(any());
        verify(temporaryPasswordRepository, times(1)).save(any());
        verify(emailTemplateBuilder, times(1)).buildForForgotPassword(any(), any(), any());
        verify(emailConfig, times(1)).username();
        verify(emailService, times(1)).sendEmail(any(), any(), any(), any());
    }

    @Test
    public void resetPasswordTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(temporaryPasswordRepository.findByPassword(any())).thenReturn(Optional.of(notExpiredTemporaryPassword()));
        doNothing().when(temporaryPasswordRepository).delete(any());
        when(passwordManager.encode(any())).thenReturn(NEW_HASHED_PASSWORD);
        when(userRepository.save(any())).thenReturn(user());

        assertDoesNotThrow(() -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(temporaryPasswordRepository, times(1)).findByPassword(any());
        verify(temporaryPasswordRepository, times(1)).delete(any());
        verify(passwordManager, times(1)).encode(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_AccessDeniedException() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(false);

        var result = assertThrows(
                AccessDeniedException.class,
                () -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid temporary password."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(temporaryPasswordRepository, never()).findByPassword(any());
        verify(temporaryPasswordRepository, never()).delete(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_InvalidTemporaryPasswordException() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(temporaryPasswordRepository.findByPassword(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                InvalidTemporaryPasswordException.class,
                () -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Invalid temporary password."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(temporaryPasswordRepository, times(1)).findByPassword(any());
        verify(temporaryPasswordRepository, never()).delete(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void resetPasswordTest_UnhappyPath_ExpiredTemporaryPasswordException() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(passwordManager.verify(any(), any())).thenReturn(true);
        when(temporaryPasswordRepository.findByPassword(any())).thenReturn(Optional.of(expiredTemporaryPassword()));
        doNothing().when(temporaryPasswordRepository).delete(any());

        var result = assertThrows(
                ExpiredTemporaryPasswordException.class,
                () -> userService.resetPassword(TEMPORARY_PASSWORD, NEW_PASSWORD)
        );

        assertThat(result.getMessage(), equalTo("Temporary password has expired."));

        verify(authService, times(1)).getCurrentUser();
        verify(passwordManager, times(1)).verify(any(), any());
        verify(temporaryPasswordRepository, times(1)).findByPassword(any());
        verify(temporaryPasswordRepository, times(1)).delete(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void updateUserTest() {
        when(userRepository.save(any())).thenReturn(user());

        assertDoesNotThrow(() -> userService.updateUser(user()));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(any());

        assertDoesNotThrow(() -> userService.deleteUser(any()));
    }
}
