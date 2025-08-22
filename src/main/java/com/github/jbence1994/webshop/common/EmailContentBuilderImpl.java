package com.github.jbence1994.webshop.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailContentBuilderImpl implements EmailContentBuilder {
    private final WebshopNameConfig webshopNameConfig;

    public EmailContent buildForForgotPassword(String firstName, String rawTemporaryPassword) {
        var subject = String.format("%s | Reset your password", webshopNameConfig.value());
        var body = String.format(
                """
                        Hi %s!
                        Previously you have informed us that you forgot your password.
                        If it was not you, please ignore this email.
                        Your temporary password: %s
                        Note that your temporary password expires in ten minutes.
                        """,
                firstName,
                rawTemporaryPassword
        );

        return new EmailContent(subject, body);
    }
}
