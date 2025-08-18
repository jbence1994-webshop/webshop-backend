package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
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

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    public void getUserTest_ById_HappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user()));

        var result = assertDoesNotThrow(() -> userQueryService.getUser(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(user().getId())),
                hasProperty("email", equalTo(user().getEmail())),
                hasProperty("password", equalTo(user().getPassword())),
                hasProperty("role", equalTo(user().getRole()))
        ));
    }

    @Test
    public void getUserTest_ById_UnhappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userQueryService.getUser(1L)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }

    @Test
    public void getUserTest_ByEmail_HappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user()));

        var result = assertDoesNotThrow(() -> userQueryService.getUser(EMAIL));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(user().getId())),
                hasProperty("email", equalTo(user().getEmail())),
                hasProperty("password", equalTo(user().getPassword())),
                hasProperty("role", equalTo(user().getRole()))
        ));
    }

    @Test
    public void getUserTest_ByEmail_UnhappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userQueryService.getUser(EMAIL)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given e-mail: 'juhasz.bence.zsolt@gmail.com'."));
    }
}
