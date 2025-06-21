package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.auth.AuthTestConstants.ACCESS_TOKEN_AS_STRING;
import static com.github.jbence1994.webshop.auth.JwtTestObject.accessToken;
import static com.github.jbence1994.webshop.auth.JwtTestObject.expiredAccessToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JwtTests {

    private static Stream<Arguments> accessTokenParams() {
        return Stream.of(
                Arguments.of("Access token is not expired", accessToken(), false),
                Arguments.of("Access token is expired", expiredAccessToken(), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("accessTokenParams")
    public void isExpired(
            String testCase,
            Jwt jwt,
            boolean expectedResult
    ) {
        var result = jwt.isExpired();

        assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void getUserIdTest() {
        var result = accessToken().getUserId();

        assertThat(result, equalTo(1L));
    }

    @Test
    public void getRoleTest() {
        var result = accessToken().getRole();

        assertThat(result, equalTo(Role.ADMIN));
    }

    @Test
    public void toStringTest() {
        var result = accessToken().toString();

        assertThat(result, equalTo(ACCESS_TOKEN_AS_STRING));
    }
}
