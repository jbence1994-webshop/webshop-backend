package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.github.jbence1994.webshop.auth.AuthTestConstants.ACCESS_TOKEN_EXPIRATION;
import static com.github.jbence1994.webshop.auth.AuthTestConstants.MALFORMED_TOKEN;
import static com.github.jbence1994.webshop.auth.AuthTestConstants.REFRESH_TOKEN_EXPIRATION;
import static com.github.jbence1994.webshop.auth.SecretKeyTestObject.secretKey;
import static com.github.jbence1994.webshop.auth.UserIdentityTestObject.userIdentity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JwtServiceImplTests {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtServiceImpl jwtService;

    @BeforeEach
    public void setUp() {
        when(jwtConfig.getAccessTokenExpiration()).thenReturn(ACCESS_TOKEN_EXPIRATION);
        when(jwtConfig.getRefreshTokenExpiration()).thenReturn(REFRESH_TOKEN_EXPIRATION);
        when(jwtConfig.getSecretKey()).thenReturn(secretKey());
    }

    @Test
    public void generateAccessTokenTest() {
        var jwt = jwtService.generateAccessToken(userIdentity());

        assertThat(jwt, not(nullValue()));

        var parsed = jwtService.parseToken(jwt.toString());

        assertThat(parsed, not(nullValue()));
        assertThat(parsed.getUserId(), equalTo(userIdentity().id()));
        assertThat(parsed.isExpired(), is(false));

        verify(jwtConfig).getAccessTokenExpiration();
        verify(jwtConfig, atLeastOnce()).getSecretKey();
    }

    @Test
    public void generateRefreshTokenTest() {
        var jwt = jwtService.generateRefreshToken(userIdentity());

        assertThat(jwt, not(nullValue()));

        var parsed = jwtService.parseToken(jwt.toString());

        assertThat(parsed, not(nullValue()));
        assertThat(parsed.getUserId(), equalTo(userIdentity().id()));
        assertThat(parsed.isExpired(), is(false));

        verify(jwtConfig).getRefreshTokenExpiration();
        verify(jwtConfig, atLeastOnce()).getSecretKey();
    }

    @Test
    public void parseTokenTest_HappyPath() {
        var token = jwtService.generateAccessToken(userIdentity()).toString();
        var result = jwtService.parseToken(token);

        assertThat(result, not(nullValue()));
        assertThat(result.getUserId(), equalTo(1L));
    }

    @Test
    public void parseTokenTest_UnhappyPath_MalformedToken() {
        var result = jwtService.parseToken(MALFORMED_TOKEN);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void getUserIdFromTokenTest() {
        var result = jwtService.getUserIdFromToken(jwtService.generateAccessToken(userIdentity()).toString());

        assertThat(result, equalTo(1L));
    }

    @Test
    public void getRoleFromTokenTest() {
        var result = jwtService.getRoleFromToken(jwtService.generateAccessToken(userIdentity()).toString());

        assertThat(result, equalTo(Role.ADMIN));
    }
}
