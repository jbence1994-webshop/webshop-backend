package com.github.jbence1994.webshop.ai;

import org.springframework.ai.chat.messages.AssistantMessage;

public final class AssistantMessageTestObject {
    public static AssistantMessage assistantMessage() {
        return new AssistantMessage("Hello, World!");
    }
}
