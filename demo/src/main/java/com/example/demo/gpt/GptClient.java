package com.example.demo.gpt;


import com.example.demo.gpt.dto.GptModelsResponseDto;

public interface GptClient {
    GptModelsResponseDto getModels();

    String generateNoteText(String command);
}
