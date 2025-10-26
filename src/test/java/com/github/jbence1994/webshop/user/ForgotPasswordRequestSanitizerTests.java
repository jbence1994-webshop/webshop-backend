package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.ForgotPasswordRequestTestObject.notSanitizedForgotPasswordRequest;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ForgotPasswordRequestSanitizerTests {

    @InjectMocks
    private ForgotPasswordRequestSanitizer forgotPasswordRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = forgotPasswordRequestSanitizer.sanitize(notSanitizedForgotPasswordRequest());

        assertThat(result.email(), equalTo(EMAIL));
    }
}
