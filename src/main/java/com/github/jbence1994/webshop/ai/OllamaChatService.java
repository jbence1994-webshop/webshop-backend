package com.github.jbence1994.webshop.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OllamaChatService implements ChatService {
    private final SystemPromptUtil systemPromptUtil;
    private final OllamaChatModel ollamaChatModel;
    private final ChatMemory chatMemory;

    @Override
    public String chat(String promptText) {
        try {
            var sanitizedPromptText = promptText.trim();
            var systemPrompt = trimOrEmpty(systemPromptUtil.getSystemPrompt());

            var systemMessage = new SystemMessage(systemPrompt);
            var userMessage = new UserMessage(sanitizedPromptText);
            var prompt = new Prompt(List.of(systemMessage, userMessage));

            var response = Optional.ofNullable(ollamaChatModel.call(prompt).getResult().getOutput().getText());

            return trimOrEmpty(response);
        } catch (Exception exception) {
            throw new OllamaException(exception);
        }
    }

    @Override
    @Transactional
    public String chat(UUID conversationId, String promptText) {
        try {
            var conversationIdAsString = conversationId.toString();
            var sanitizedPromptText = promptText.trim();
            var systemPrompt = trimOrEmpty(systemPromptUtil.getSystemPrompt());

            var memoryMessages = chatMemory.get(conversationIdAsString);

            var systemMessage = new SystemMessage(systemPrompt);
            var userMessage = new UserMessage(sanitizedPromptText);
            var promptMessages = new ArrayList<Message>(memoryMessages.size() + 2);
            var prompt = new Prompt(promptMessages);

            promptMessages.add(systemMessage);
            promptMessages.add(userMessage);
            promptMessages.addAll(memoryMessages);

            var response = Optional.ofNullable(
                    ollamaChatModel.call(prompt)
                            .getResult()
                            .getOutput()
                            .getText()
            );

            var sanitizedResponse = trimOrEmpty(response);

            var assistantMessage = new AssistantMessage(sanitizedResponse);

            chatMemory.add(conversationIdAsString, List.of(userMessage, assistantMessage));

            return sanitizedResponse;
        } catch (Exception exception) {
            throw new OllamaException(exception);
        }
    }

    private String trimOrEmpty(Optional<String> input) {
        return input
                .map(String::trim)
                .orElse("");
    }
}
