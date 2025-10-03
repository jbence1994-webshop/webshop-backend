package com.github.jbence1994.webshop.ai;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ChatRequest(

        @NotNull(message = "Prompt must be provided.")
        @NotBlank(message = "Prompt must be not empty.")
        String prompt,

        @NotNull(message = "Conversation ID must be provided.")
        UUID conversationId
) {
}
