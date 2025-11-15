package com.github.jbence1994.webshop.ai;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.ai.chat-memory")
public record ChatMemoryMaxMessagesConfig(int maxMessages) {
}
