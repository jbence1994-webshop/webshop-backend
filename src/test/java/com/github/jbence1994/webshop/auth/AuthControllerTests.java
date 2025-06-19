package com.github.jbence1994.webshop.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.auth.AuthTestConstants.REFRESH_TOKEN_EXPIRATION;
import static com.github.jbence1994.webshop.auth.JwtTestObject.accessToken;
import static com.github.jbence1994.webshop.auth.JwtTestObject.refreshToken;
import static com.github.jbence1994.webshop.auth.LoginRequestTestObject.loginRequest;
import static com.github.jbence1994.webshop.auth.LoginResponseTestObject.loginResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {

    @Mock
    private JwtConfig jwtConfig;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void loginTest() {
        var httpServletResponse = mock(HttpServletResponse.class);
        when(authService.login(any())).thenReturn(loginResponse());
        when(jwtConfig.getRefreshTokenExpiration()).thenReturn(REFRESH_TOKEN_EXPIRATION);

        var result = authController.login(loginRequest(), httpServletResponse);

        assertThat(result.token(), equalTo(accessToken().toString()));
    }

    @Test
    public void refreshTest() {
        when(authService.refreshAccessToken(any())).thenReturn(accessToken());

        var result = authController.refresh(refreshToken().toString());

        assertThat(result.token(), equalTo(accessToken().toString()));
    }
}
