package com.github.jbence1994.webshop.ai;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatResponse toChatResponse(String message);
}
