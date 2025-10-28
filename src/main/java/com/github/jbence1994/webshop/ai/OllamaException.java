package com.github.jbence1994.webshop.ai;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class OllamaException extends RuntimeException {
    public OllamaException(Exception exception) {
        super(String.format("Ollama failure: %s", exception.getMessage()));
    }
}
