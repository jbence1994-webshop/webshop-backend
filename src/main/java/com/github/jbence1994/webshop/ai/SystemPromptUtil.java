package com.github.jbence1994.webshop.ai;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class SystemPromptUtil {

    public String getSystemPrompt() {
        String systemPrompt = "";

        try {
            return Files.readString(Path.of("system_prompt.txt"), UTF_8);
        } catch (IOException ignored) {
        }

        return systemPrompt;
    }
}
