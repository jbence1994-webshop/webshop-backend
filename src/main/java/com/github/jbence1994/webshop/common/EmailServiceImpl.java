package com.github.jbence1994.webshop.common;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(emailConfig.webshopEmailAddress());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        mailSender.send(simpleMailMessage);
    }
}
