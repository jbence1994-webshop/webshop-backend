package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.UserQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FakeAuthServiceTests {

    @Mock
    private UserQueryService userQueryService;

    @InjectMocks
    private FakeAuthService authService;

    @Test
    public void getCurrentUserTest() {
        when(userQueryService.getUser(anyLong())).thenReturn(user());

        var result = assertDoesNotThrow(() -> authService.getCurrentUser());

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(user().getId())),
                hasProperty("email", equalTo(user().getEmail())),
                hasProperty("password", equalTo(user().getPassword())),
                hasProperty("role", equalTo(user().getRole()))
        ));
    }
}
