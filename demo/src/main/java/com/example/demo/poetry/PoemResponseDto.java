package com.example.demo.poetry;

import java.util.List;

public record PoemResponseDto(
        String title,
        String author,
        List<String> lines,
        Integer linecount
) {}
