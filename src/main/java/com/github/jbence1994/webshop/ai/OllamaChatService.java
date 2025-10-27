package com.github.jbence1994.webshop.ai;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OllamaChatService implements ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final SystemPromptUtil systemPromptUtil;
    private final OllamaChatModel ollamaChatModel;
    private final AuthService authService;

    @Override
    public String chat(String promptText) {
        try {
            var systemMessage = new SystemMessage(systemPromptUtil.getSystemPrompt());
            var userMessage = new UserMessage(promptText);
            var prompt = new Prompt(List.of(systemMessage, userMessage));

            return ollamaChatModel.call(prompt)
                    .getResult()
                    .getOutput()
                    .getText();
        } catch (Exception exception) {
            throw new OllamaException();
        }
    }

    @Override
    @Transactional
    public String chat(UUID conversationId, String promptText) {
        try {
            var user = authService.getCurrentUser();

            var history = chatMessageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);

            var userMessages = history.stream()
                    .map(chatMessage -> new UserMessage(chatMessage.getPrompt()))
                    .toList();

            var systemMessage = new SystemMessage(systemPromptUtil.getSystemPrompt());

            var messages = new ArrayList<Message>(userMessages.size() + 1);
            messages.add(systemMessage);
            messages.addAll(userMessages);
            messages.add(new UserMessage(promptText));

            var prompt = new Prompt(messages);

            var response = ollamaChatModel
                    .call(prompt)
                    .getResult()
                    .getOutput()
                    .getText();

            var chatMessage = ChatMessage.from(conversationId, promptText, user);
            chatMessageRepository.save(chatMessage);

            return response;
        } catch (Exception exception) {
            throw new OllamaException();
        }
    }
}
