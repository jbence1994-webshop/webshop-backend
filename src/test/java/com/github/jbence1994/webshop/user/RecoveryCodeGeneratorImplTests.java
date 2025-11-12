package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class RecoveryCodeGeneratorImplTests {

    @InjectMocks
    private RecoveryCodeGeneratorImpl recoveryCodeGenerator;

    @Test
    public void generateTest_HappyPath() {
        var result1 = recoveryCodeGenerator.generate();
        var result2 = recoveryCodeGenerator.generate();

        assertThat(result1, not(nullValue()));
        assertThat(result1.length(), equalTo(6));
        assertThat(result1, not(equalTo(result2)));
    }
}
