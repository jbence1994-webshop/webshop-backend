package com.github.jbence1994.webshop.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.expiredTemporaryPassword;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestObject.notExpiredTemporaryPassword;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TemporaryPasswordTests {

    private static Stream<Arguments> temporaryPasswordParams() {
        return Stream.of(
                Arguments.of("Temporary password is not expired", notExpiredTemporaryPassword(), false),
                Arguments.of("Temporary password is expired", expiredTemporaryPassword(), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("temporaryPasswordParams")
    public void isExpiredTests(
            String testCase,
            TemporaryPassword temporaryPassword,
            boolean expectedResult
    ) {
        var result = temporaryPassword.isExpired();

        assertThat(result, is(expectedResult));
    }
}
