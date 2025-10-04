package com.github.jbence1994.webshop.ai;

import org.springframework.ai.chat.model.Generation;

import static com.github.jbence1994.webshop.ai.AssistantMessageTestObject.assistantMessage;

public final class GenerationTestObject {
    public static Generation generation() {
        return new Generation(assistantMessage());
    }
}
