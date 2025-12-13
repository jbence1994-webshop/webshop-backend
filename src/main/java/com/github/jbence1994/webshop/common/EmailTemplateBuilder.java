package com.github.jbence1994.webshop.common;

import java.util.Locale;

public interface EmailTemplateBuilder {
    EmailContent buildForForgotPassword(String firstName, String recoveryCode, Locale locale);
}
