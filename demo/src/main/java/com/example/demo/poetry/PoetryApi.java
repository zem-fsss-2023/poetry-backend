package com.example.demo.poetry;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface PoetryApi {
    @RequestLine("GET /author")
    PoetsResponseDto getPoets();

    @RequestLine("GET /author,random/{poet};3")
    List<PoemResponseDto> getPoems(@Param String poet);
}

