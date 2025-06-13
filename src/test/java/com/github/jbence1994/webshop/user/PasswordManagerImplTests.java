package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordManagerImplTests {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordManagerImpl passwordManager;

    @Test
    public void encodeTest() {
        when(passwordEncoder.encode(any())).thenReturn(HASHED_PASSWORD);

        var result = passwordManager.encode(PASSWORD);

        assertThat(result, equalTo(HASHED_PASSWORD));
    }

    @Test
    public void verifyTest_HappyPath() {
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        var result = passwordManager.verify(PASSWORD, HASHED_PASSWORD);

        assertTrue(result);
    }

    @Test
    public void verifyTest_UnhappyPath() {
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        var result = passwordManager.verify(INVALID_CONFIRM_PASSWORD, HASHED_PASSWORD);

        assertFalse(result);
    }
}
