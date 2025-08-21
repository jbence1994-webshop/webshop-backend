package com.github.jbence1994.webshop.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.PasswordResetTokenTestObject.expiredPasswordResetToken;
import static com.github.jbence1994.webshop.user.PasswordResetTokenTestObject.notExpiredPasswordResetToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PasswordResetTokenTests {

    private static Stream<Arguments> passwordResetTokenParams() {
        return Stream.of(
                Arguments.of("Password reset token is not expired", notExpiredPasswordResetToken(), false),
                Arguments.of("Password reset token is expired", expiredPasswordResetToken(), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("passwordResetTokenParams")
    public void isExpiredTests(
            String testCase,
            PasswordResetToken passwordResetToken,
            boolean expectedResult
    ) {
        var result = passwordResetToken.isExpired();

        assertThat(result, is(expectedResult));
    }
}
