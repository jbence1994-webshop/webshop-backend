package com.github.jbence1994.webshop.ai;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class SystemPromptUtil {

    @SneakyThrows
    public Optional<String> getSystemPrompt() {
        return Optional.of(Files.readString(Path.of("system_prompt.txt"), UTF_8));
    }
}
