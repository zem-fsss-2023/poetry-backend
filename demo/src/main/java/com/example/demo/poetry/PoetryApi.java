package com.example.demo.poetry;

import feign.RequestLine;

public interface PoetryApi {
    @RequestLine("GET /author")
    PoetsResponseDto getPoets();
}

