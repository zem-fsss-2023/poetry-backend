package com.example.demo.gpt;



public interface ChatGptClient {
    GptModelsResponseDto getModels();

    String generateNoteText();
}
