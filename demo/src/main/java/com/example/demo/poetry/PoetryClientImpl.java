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

@Service
public class PoetryClientImpl implements PoetryClient{

    private final PoetryApi poetryApi;
    private final Logger logger = LoggerFactory.getLogger(PoetryClientImpl.class.getName());
    private final Counter getPoetsCounter;
    private final Timer getPoetsTimer;

    private final Counter getPoemsCounter;
    private final Timer getPoemsTimer;

    @Autowired
    public PoetryClientImpl(@Value("${poetry.db.api.url}") final String url, MeterRegistry meterRegistry) {
        this.poetryApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(PoetryApi.class, url);
        this.getPoetsCounter = meterRegistry.counter("getPoetsCounter");
        this.getPoetsTimer = meterRegistry.timer("getPoetsTimer");

        this.getPoemsCounter = meterRegistry.counter("getPoemsCounter");
        this.getPoemsTimer = meterRegistry.timer("getPoemsTimer");
    }

    @Override
    public PoetsResponseDto getPoets() {
        logger.info("Retrieving list of available poets.");
        getPoetsCounter.increment();
        return getPoetsTimer.record(() -> poetryApi.getPoets());
    }

    @Override
    public List<PoemResponseDto> getPoems(String poet) {
        logger.info(String.format("Retrieving poems written by %s.", poet));
        getPoemsCounter.increment();
        return getPoemsTimer.record(() -> poetryApi.getPoems(poet));
    }
}
