package com.github.jbence1994.webshop.ai;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.ai.ChatTestConstants.CONVERSATION_ID;
import static com.github.jbence1994.webshop.ai.ChatTestConstants.PROMPT_TEXT;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class ChatMessageTestObject {
    public static ChatMessage chatMessage1() {
        return new ChatMessage(
                1L,
                CONVERSATION_ID,
                PROMPT_TEXT,
                LocalDateTime.now(),
                user()
        );
    }

    public static ChatMessage chatMessage2() {
        return new ChatMessage(
                2L,
                CONVERSATION_ID,
                "Greet.",
                LocalDateTime.now(),
                user()
        );
    }
}
