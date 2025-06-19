package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.UserQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static com.github.jbence1994.webshop.auth.JwtTestObject.accessToken;
import static com.github.jbence1994.webshop.auth.JwtTestObject.refreshToken;
import static com.github.jbence1994.webshop.auth.LoginRequestTestObject.loginRequest;
import static com.github.jbence1994.webshop.auth.LoginResponseTestObject.loginResponse;
import static com.github.jbence1994.webshop.auth.UserIdentityTestObject.userIdentity;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserIdentityMapper userIdentityMapper;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void loginTest() {
        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userQueryService.getUser(anyString())).thenReturn(user());
        when(userIdentityMapper.toUserIdentity(any())).thenReturn(userIdentity());
        when(jwtService.generateAccessToken(any())).thenReturn(accessToken());
        when(jwtService.generateRefreshToken(any())).thenReturn(refreshToken());

        var result = authService.login(loginRequest());

        assertThat(result, equalTo(loginResponse()));
    }

    @Test
    public void refreshAccessTokenTest_HappyPath() {
        when(jwtService.parseToken(any())).thenReturn(refreshToken());
        when(userQueryService.getUser(anyLong())).thenReturn(user());
        when(userIdentityMapper.toUserIdentity(any())).thenReturn(userIdentity());
        when(jwtService.generateAccessToken(any())).thenReturn(accessToken());

        var result = authService.refreshAccessToken(refreshToken().toString());

        assertThat(result, equalTo(accessToken()));
    }

    @Test
    public void refreshAccessTokenTest_UnhappyPath_JwtIsNull() {
        when(jwtService.parseToken(any())).thenReturn(null);

        var result = assertThrows(
                BadCredentialsException.class,
                () -> authService.refreshAccessToken(refreshToken().toString())
        );

        assertThat(result.getMessage(), equalTo("Invalid refresh token."));
    }
}
