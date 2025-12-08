package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.notSanitizedChangePasswordRequest;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_NEW_PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ChangePasswordRequestSanitizerTests {

    @InjectMocks
    private ChangePasswordRequestSanitizer changePasswordRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = changePasswordRequestSanitizer.sanitize(notSanitizedChangePasswordRequest());

        assertThat(result.newPassword(), equalTo(RAW_NEW_PASSWORD));
        assertThat(result.confirmNewPassword(), equalTo(RAW_CONFIRM_NEW_PASSWORD));
    }
}
