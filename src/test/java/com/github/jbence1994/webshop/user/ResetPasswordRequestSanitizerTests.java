package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.notSanitizedResetPasswordRequest;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ResetPasswordRequestSanitizerTests {

    @InjectMocks
    private ResetPasswordRequestSanitizer resetPasswordRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = resetPasswordRequestSanitizer.sanitize(notSanitizedResetPasswordRequest());

        assertThat(result.getNewPassword(), equalTo(NEW_PASSWORD));
        assertThat(result.getConfirmNewPassword(), equalTo(CONFIRM_NEW_PASSWORD));
    }
}
