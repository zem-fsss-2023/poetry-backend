package com.example.demo.gpt.dto;

import java.util.List;

public class GptRequestDto {
    String model;
    List<GptMessageDto> messages;

    public GptRequestDto(String model, String content) {
        GptMessageDto message = new GptMessageDto("user", content);
        this.model = model;
        this.messages = List.of(message);
    }
}
