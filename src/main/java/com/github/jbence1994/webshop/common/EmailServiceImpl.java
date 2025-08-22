package com.github.jbence1994.webshop.common;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    @SneakyThrows
    public void sendEmail(String from, String to, String subject, String body) {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, true);

        mailSender.send(mimeMessageHelper.getMimeMessage());
    }
}
