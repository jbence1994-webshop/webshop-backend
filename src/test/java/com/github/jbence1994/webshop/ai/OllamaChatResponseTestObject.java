package com.github.jbence1994.webshop.ai;

import org.springframework.ai.chat.model.ChatResponse;

import java.util.List;

import static com.github.jbence1994.webshop.ai.GenerationTestObject.generation;

public final class OllamaChatResponseTestObject {
    public static ChatResponse chatResponse() {
        return new ChatResponse(List.of(generation()));
    }
}
