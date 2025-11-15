package com.github.jbence1994.webshop.ai;

import java.util.UUID;

public interface ChatService {
    String chat(String promptText);

    String chat(UUID conversationId, String promptText);
}
