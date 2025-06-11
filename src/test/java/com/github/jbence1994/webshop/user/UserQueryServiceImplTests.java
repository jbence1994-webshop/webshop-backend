package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    public void getUserTest_HappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user()));

        var result = assertDoesNotThrow(() -> userQueryService.getUser(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(user().getId())),
                hasProperty("firstName", equalTo(user().getFirstName())),
                hasProperty("middleName", equalTo(user().getMiddleName())),
                hasProperty("lastName", equalTo(user().getLastName())),
                hasProperty("email", equalTo(user().getEmail())),
                hasProperty("phoneNumber", equalTo(user().getPhoneNumber()))
        ));
    }

    @Test
    public void getUserTest_UnhappyPath() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userQueryService.getUser(1L)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }
}
