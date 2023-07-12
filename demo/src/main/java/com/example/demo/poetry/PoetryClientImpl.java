package com.example.demo.poetry;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoetryClientImpl implements PoetryClient{

    private final PoetryApi poetryApi;

    @Autowired
    public PoetryClientImpl(@Value("${poetry.db.api.url}") final String url) {
        this.poetryApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(PoetryApi.class, url);
    }

    @Override
    public PoetsResponseDto getPoets() {
        return poetryApi.getPoets();
    }

    @Override
    public List<PoemResponseDto> getPoems(String poet) {
        return poetryApi.getPoems(poet);
    }
}
