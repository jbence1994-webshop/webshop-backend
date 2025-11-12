package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.RecoveryCodeTestObject.expiredRecoveryCode;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestObject.notExpiredRecoveryCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RecoveryCodeTests {

    private static Stream<Arguments> recoveryCodeParams() {
        return Stream.of(
                Arguments.of(Named.of("Recovery code is not expired", notExpiredRecoveryCode()), false),
                Arguments.of(Named.of("Recovery code is expired", expiredRecoveryCode()), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("recoveryCodeParams")
    public void isExpiredTests(RecoveryCode recoveryCode, boolean expectedResult) {
        var result = recoveryCode.isExpired();

        assertThat(result, is(expectedResult));
    }
}
