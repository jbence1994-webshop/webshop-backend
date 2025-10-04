package com.github.jbence1994.webshop.ai;

public interface ChatService {
    ChatResponse chat(ChatRequest request);

    ChatResponse chat(String prompt);
}
