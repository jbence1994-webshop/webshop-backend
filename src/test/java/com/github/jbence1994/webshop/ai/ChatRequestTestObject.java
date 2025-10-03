package com.github.jbence1994.webshop.ai;

import java.util.UUID;

public final class ChatRequestTestObject {
    public static ChatRequest chatRequest() {
        return new ChatRequest("Greet.", UUID.randomUUID());
    }
}
