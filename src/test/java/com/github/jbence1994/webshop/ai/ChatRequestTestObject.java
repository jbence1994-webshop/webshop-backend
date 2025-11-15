package com.github.jbence1994.webshop.ai;

import static com.github.jbence1994.webshop.ai.ChatTestConstants.CONVERSATION_ID;

public final class ChatRequestTestObject {
    public static ChatRequest chatRequest() {
        return new ChatRequest(CONVERSATION_ID, "Greet.");
    }
}
