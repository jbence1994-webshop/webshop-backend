package com.github.jbence1994.webshop.common;

public interface EmailService {
    void sendEmail(String from, String to, String subject, String body);
}
