package com.example.demo.gpt.dto;

import java.util.List;

public record GptResponseDto(
        String id,
        String object,
        List<GptChoicesDto> choices
) {
    public String getAutocompletedMessage() {
        return this.choices.get(0).message().content();
    }
}