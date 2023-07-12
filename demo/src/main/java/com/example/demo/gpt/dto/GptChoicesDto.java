package com.example.demo.gpt.dto;

public record GptChoicesDto(
        GptMessageDto message,
        String finish_reason,
        Integer index
) {}
