package com.example.demo.gpt.dto;

public record GptModelDto(
        String id,
        String object,
        Long created,
        String owned_by
) {}
