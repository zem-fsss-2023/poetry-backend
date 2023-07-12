package com.example.demo.gpt.dto;

public record GptChoicesDto(GptAutocompletionMessageDto message, String finish_reason, Integer index) {

}
