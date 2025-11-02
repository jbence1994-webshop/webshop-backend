package com.github.jbence1994.webshop.ai;

import static com.github.jbence1994.webshop.ai.ChatTestConstants.CHAT_RESPONSE_TEXT;

public final class ChatResponseTestObject {
    public static ChatResponse chatResponse() {
        return new ChatResponse(CHAT_RESPONSE_TEXT);
    }
}
