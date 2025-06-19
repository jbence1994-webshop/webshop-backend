package com.github.jbence1994.webshop.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class AuthControllerExceptionHandlerTests {

    @InjectMocks
    private AuthControllerExceptionHandler authControllerExceptionHandler;

    @Test
    public void handleBadCredentialsExceptionTest() {
        var result = authControllerExceptionHandler.handleBadCredentialsException();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Invalid e-mail address or password."));
    }
}
