package com.example.demo.gpt;


import com.example.demo.gpt.dto.GptModelsResponseDto;
import com.example.demo.gpt.dto.GptRequestDto;
import com.example.demo.gpt.dto.GptResponseDto;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GptClientImpl implements GptClient {

    private final String apiKey;

    private final GptApi chatGptApi;

    @Autowired
    public GptClientImpl(
            @Value("${gpt.api.url}") final String url,
            @Value("${gpt.api.key}") final String apiKey
    ) {
        this.apiKey = apiKey;
        this.chatGptApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(GptApi.class, url);
    }

    @Override
    public GptModelsResponseDto getModels() {
        return chatGptApi.getModels(apiKey);
    }

    @Override
    public String generateNoteText(String command) {
        GptRequestDto requestBody = new GptRequestDto(
                "gpt-3.5-turbo",
                command
        );
        GptResponseDto response = chatGptApi.generateTask(requestBody, apiKey);

        return response.getAutocompletedMessage();
    }
}
