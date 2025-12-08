package com.github.jbence1994.webshop.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

@Configuration
@RequiredArgsConstructor
public class ChatMemoryConfiguration {
    private final ChatMemoryMaxMessagesConfig chatMemoryMaxMessagesConfig;

    @Bean
    public ChatMemory chatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(chatMemoryMaxMessagesConfig.maxMessages())
                .build();
    }
}
