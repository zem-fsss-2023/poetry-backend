package com.example.demo.gpt;


import com.example.demo.gpt.dto.GptModelsResponseDto;
import com.example.demo.gpt.dto.GptRequestDto;
import com.example.demo.gpt.dto.GptResponseDto;
import com.example.demo.poetry.PoetryController;
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

@Service
public class GptClientImpl implements GptClient {

    private final String apiKey;
    private final Logger logger = LoggerFactory.getLogger(GptClientImpl.class.getName());
    private final Counter generateTextCounter;
    private final Timer generateTextTimer;
    private final GptApi gptApi;

    @Autowired
    public GptClientImpl(
            @Value("${gpt.api.url}") final String url,
            @Value("${gpt.api.key}") final String apiKey,
            final MeterRegistry meterRegistry
    ) {
        this.generateTextCounter = meterRegistry.counter("generateTextCounter");
        this.generateTextTimer = meterRegistry.timer("generateTextTimer");

        this.apiKey = apiKey;
        this.gptApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(GptApi.class, url);
    }

    @Override
    public GptModelsResponseDto getModels() {
        return gptApi.getModels(apiKey);
    }

    @Override
    public String generateText(String command) {
        GptRequestDto requestBody = new GptRequestDto(
                "gpt-3.5-turbo",
                command
        );

        logger.info("Generating text with GPT.");
        generateTextCounter.increment();
        GptResponseDto response = generateTextTimer.record(() -> gptApi.generateTask(requestBody, apiKey));
        return response.getAutocompletedMessage();
    }
}
