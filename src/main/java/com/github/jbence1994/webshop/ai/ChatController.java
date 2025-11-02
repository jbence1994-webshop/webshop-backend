package com.github.jbence1994.webshop.ai;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final ChatMapper chatMapper;

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        var message = chatService.chat(request.prompt());

        return chatMapper.toChatResponse(message);
    }
}
