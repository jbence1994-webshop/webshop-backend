package com.github.jbence1994.webshop.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailContentBuilderImplTests {

    @Mock
    private WebshopNameConfig webshopNameConfig;

    @InjectMocks
    private EmailContentBuilderImpl emailContentBuilder;

    @Test
    public void buildForForgotPasswordTest() {
        when(webshopNameConfig.value()).thenReturn("webshop-name");

        var result = emailContentBuilder.buildForForgotPassword(FIRST_NAME, TEMPORARY_PASSWORD);

        assertThat(result.subject(), not(nullValue()));
        assertThat(result.body(), not(nullValue()));
    }
}
