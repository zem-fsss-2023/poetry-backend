package com.example.demo.poetry;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface PoetryClient {
    PoetsResponseDto getPoets();
    List<PoemResponseDto> getPoems(String poet);
}
