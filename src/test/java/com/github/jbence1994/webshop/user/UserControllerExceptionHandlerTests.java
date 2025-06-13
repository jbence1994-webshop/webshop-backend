package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class UserControllerExceptionHandlerTests {

    @InjectMocks
    private UserControllerExceptionHandler userControllerExceptionHandler;

    @Test
    public void handleUserNotFoundException() {
        var result = userControllerExceptionHandler
                .handleUserNotFoundException(new UserNotFoundException(1L));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No user was found with the given ID: #1."));
    }

    @Test
    public void handleEmailAlreadyExistsExceptionTest() {
        var result = userControllerExceptionHandler
                .handleEmailAlreadyExistsException(new EmailAlreadyExistsException(EMAIL));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CONFLICT));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Email address 'juhasz.bence.zsolt@gmail.com' is already in use. Please use a different."));
    }
}
