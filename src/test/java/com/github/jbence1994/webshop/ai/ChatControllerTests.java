package com.github.jbence1994.webshop.ai;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.ai.ChatRequestTestObject.chatRequest;
import static com.github.jbence1994.webshop.ai.ChatResponseTestObject.chatResponse;
import static com.github.jbence1994.webshop.ai.ChatTestConstants.CHAT_RESPONSE_TEXT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTests {

    @Mock
    private ChatService chatService;

    @Mock
    private ChatMapper chatMapper;

    @InjectMocks
    private ChatController chatController;

    @Test
    public void chatTest() {
        when(chatService.chat(any(), any())).thenReturn(CHAT_RESPONSE_TEXT);
        when(chatMapper.toChatResponse(any())).thenReturn(chatResponse());

        var result = chatController.chat(chatRequest());

        assertThat(result, not(nullValue()));
        assertThat(result.message(), not(nullValue()));

        verify(chatService, times(1)).chat(any(), any());
        verify(chatMapper, times(1)).toChatResponse(any());
    }
}
