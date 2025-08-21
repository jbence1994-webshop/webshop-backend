package com.github.jbence1994.webshop.common;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
