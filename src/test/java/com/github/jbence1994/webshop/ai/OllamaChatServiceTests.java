package com.github.jbence1994.webshop.ai;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;

import java.util.List;

import static com.github.jbence1994.webshop.ai.ChatMessageTestObject.chatMessage1;
import static com.github.jbence1994.webshop.ai.ChatMessageTestObject.chatMessage2;
import static com.github.jbence1994.webshop.ai.ChatTestConstants.CONVERSATION_ID;
import static com.github.jbence1994.webshop.ai.OllamaChatResponseTestObject.chatResponse;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OllamaChatServiceTests {

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @Mock
    private SystemPromptUtil systemPromptUtil;

    @Mock
    private OllamaChatModel ollamaChatModel;

    @Mock
    private AuthService authService;

    @InjectMocks
    private OllamaChatService chatService;

    @BeforeEach
    public void setUp() {
        when(systemPromptUtil.getSystemPrompt()).thenReturn("You are a virtual assistant.");
    }

    @Test
    public void chat1Test_HappyPath() {
        when(ollamaChatModel.call(any(Prompt.class))).thenReturn(chatResponse());

        var result = assertDoesNotThrow(() -> chatService.chat("Greet."));

        assertThat(result, not(nullValue()));

        verify(systemPromptUtil, times(1)).getSystemPrompt();
        verify(ollamaChatModel, times(1)).call(any(Prompt.class));
    }

    @Test
    public void chat1Test_UnhappyPath_OllamaException() {
        doThrow(OllamaException.class).when(ollamaChatModel).call(any(Prompt.class));

        var result = assertThrows(
                OllamaException.class,
                () -> chatService.chat("Greet.")
        );

        assertThat(result.getMessage(), equalTo("Ollama currently unavailable."));

        verify(systemPromptUtil, times(1)).getSystemPrompt();
    }

    @Test
    public void chat2Test_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(chatMessageRepository.findByConversationIdOrderByCreatedAtAsc(any())).thenReturn(List.of(chatMessage1()));
        when(ollamaChatModel.call(any(Prompt.class))).thenReturn(chatResponse());
        when(chatMessageRepository.save(any())).thenReturn(chatMessage2());

        var result = assertDoesNotThrow(() -> chatService.chat(CONVERSATION_ID, "Greet."));

        assertThat(result, equalTo("Hello, World!"));

        verify(authService, times(1)).getCurrentUser();
        verify(chatMessageRepository, times(1)).findByConversationIdOrderByCreatedAtAsc(any());
        verify(systemPromptUtil, times(1)).getSystemPrompt();
        verify(ollamaChatModel, times(1)).call(any(Prompt.class));
        verify(chatMessageRepository, times(1)).save(any());
    }

    @Test
    public void chat2Test_UnhappyPath_OllamaException() {
        doThrow(OllamaException.class).when(ollamaChatModel).call(any(Prompt.class));

        var result = assertThrows(
                OllamaException.class,
                () -> chatService.chat(CONVERSATION_ID, "Greet.")
        );

        assertThat(result.getMessage(), equalTo("Ollama currently unavailable."));

        verify(authService, times(1)).getCurrentUser();
        verify(chatMessageRepository, times(1)).findByConversationIdOrderByCreatedAtAsc(any());
        verify(systemPromptUtil, times(1)).getSystemPrompt();
        verify(chatMessageRepository, never()).save(any());
    }
}
