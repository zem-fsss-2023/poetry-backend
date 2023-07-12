package com.example.demo.gpt;

import com.example.demo.gpt.dto.GptModelsResponseDto;
import com.example.demo.gpt.dto.GptRequestDto;
import com.example.demo.gpt.dto.GptResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface GptApi {
    @RequestLine("GET /v1/models")
    @Headers("Authorization: Bearer {apiKey}")
    GptModelsResponseDto getModels(@Param("apiKey") String apiKey);

    @RequestLine("POST /v1/chat/completions")
    @Headers({"Authorization: Bearer {apiKey}", "Content-Type: application/json"})
    GptResponseDto generateTask(GptRequestDto autocompletion, @Param("apiKey") String apiKey);
}
