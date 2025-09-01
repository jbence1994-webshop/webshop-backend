package com.github.jbence1994.webshop.common;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class FreeMarkerEmailTemplateBuilder implements EmailTemplateBuilder {
    private final WebshopNameConfig webshopNameConfig;
    private final MessageSource messageSource;

    @SneakyThrows
    public EmailContent buildForForgotPassword(String firstName, String temporaryPassword, Locale locale) {
        var subject = messageSource.getMessage("email.forgot-password.subject", new Object[]{webshopNameConfig.name()}, locale);

        var model = new HashMap<String, Object>();
        model.put("firstName", messageSource.getMessage("email.forgot-password.firstName", new Object[]{firstName}, locale));
        model.put("temporaryPassword", temporaryPassword);
        model.put("webshopName", messageSource.getMessage("email.forgot-password.webshopName", new Object[]{webshopNameConfig.name()}, locale));

        var freeMarkerConfig = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_32);
        freeMarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/");
        freeMarkerConfig.setDefaultEncoding("UTF-8");
        var template = freeMarkerConfig.getTemplate("forgot-password.ftl");

        var stringWriter = new StringWriter();
        template.process(model, stringWriter);

        return new EmailContent(subject, stringWriter.toString());
    }
}
