package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordManager passwordManager;

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
    public void registerUserTest_UnhappyPath_EmailAlreadyExists() {
        when(userRepository.existsByEmail(any())).thenReturn(true);

        var result = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(user())
        );

        assertThat(result.getMessage(), equalTo("Email address 'juhasz.bence.zsolt@gmail.com' is already in use. Please use a different."));

        verify(userRepository, times(1)).existsByEmail(any());
        verify(passwordManager, never()).encode(any());
        verify(userRepository, never()).save(any());
    }
}
