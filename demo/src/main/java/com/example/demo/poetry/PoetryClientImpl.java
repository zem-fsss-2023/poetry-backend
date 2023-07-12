package com.example.demo.poetry;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PoetryClientImpl implements PoetryClient{

    private final PoetryApi poetryApi;
    private final Logger logger = LoggerFactory.getLogger(PoetryClient.class.getName());
    private final Counter counter;
    private final Timer timer;

    @Autowired
    public PoetryClientImpl(@Value("${poetry.db.api.url}") final String url, MeterRegistry meterRegistry) {
        this.poetryApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(PoetryApi.class, url);
        counter = meterRegistry.counter("getPoetsCounter", "");
        timer = meterRegistry.timer("getPoetsExcectionTimer", "");
    }

    @Override
    public PoetsResponseDto getPoets() {
        counter.increment();
        PoetsResponseDto response = timer.record(() -> poetryApi.getPoets());
        return response;
    }

    @Override
    public List<PoemResponseDto> getPoems(String poet) {
        return poetryApi.getPoems(poet);
    }
}
