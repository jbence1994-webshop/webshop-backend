package com.github.jbence1994.webshop.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OllamaChatService implements ChatService {
    private final SystemPromptUtil systemPromptUtil;
    private final OllamaChatModel ollamaChatModel;

    @Override
    public ChatResponse chat(ChatRequest request) {
        try {
            var systemMessage = new SystemMessage(systemPromptUtil.getSystemPrompt());
            var userMessage = new UserMessage(request.prompt());
            var prompt = new Prompt(List.of(systemMessage, userMessage));

            var message = ollamaChatModel.call(prompt)
                    .getResult()
                    .getOutput()
                    .getText();

            return new ChatResponse(message);
        } catch (Exception exception) {
            throw new OllamaException();
        }
    }
}
