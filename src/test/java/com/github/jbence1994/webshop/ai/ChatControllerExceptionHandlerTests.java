package com.github.jbence1994.webshop.ai;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class ChatControllerExceptionHandlerTests {

    @InjectMocks
    private ChatControllerExceptionHandler chatControllerExceptionHandler;

    @Test
    public void handleOllamaExceptionTest() {
        var result = chatControllerExceptionHandler.handleOllamaException(new OllamaException());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Ollama currently unavailable."));
    }
}
