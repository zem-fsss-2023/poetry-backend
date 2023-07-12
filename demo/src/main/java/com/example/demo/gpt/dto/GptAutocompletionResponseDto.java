package com.example.demo.gpt.dto;

public record GptAutocompletionResponseDto(String id, String object, List<GptChoicesDto> choices) {
    public String getAutocompletedMesage() {
        return this.choices.get(0).message().content();
    }
}