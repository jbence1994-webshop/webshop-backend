package com.github.jbence1994.webshop.ai;

public class OllamaException extends RuntimeException {
    public OllamaException() {
        super("Ollama currently unavailable.");
    }
}
