package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.loyalty.MembershipTier;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.UserTestObject.bronzeUser;
import static com.github.jbence1994.webshop.user.UserTestObject.goldUser;
import static com.github.jbence1994.webshop.user.UserTestObject.silverUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTests {

    private static Stream<Arguments> getMembershipTierTestParams() {
        return Stream.of(
                Arguments.of(Named.of("BRONZE user", bronzeUser()), MembershipTier.BRONZE),
                Arguments.of(Named.of("SILVER user", silverUser()), MembershipTier.SILVER),
                Arguments.of(Named.of("GOLD user", goldUser()), MembershipTier.GOLD)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("getMembershipTierTestParams")
    public void getMembershipTierTests(User user, MembershipTier expectedMembershipTier) {
        var result = user.getMembershipTier();

        assertThat(result, equalTo(expectedMembershipTier));
    }
}
