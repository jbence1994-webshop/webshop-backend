package com.github.jbence1994.webshop.ai;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.ai.ChatRequestTestObject.chatRequest;
import static com.github.jbence1994.webshop.ai.ChatResponseTestObject.chatResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTests {

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatController chatController;

    @Test
    public void chatTest() {
        when(chatService.chat(any(ChatRequest.class))).thenReturn(chatResponse("Hello, World!"));

        var result = chatController.chat(chatRequest());

        assertThat(result, not(nullValue()));
        assertThat(result.message(), not(nullValue()));
    }
}
