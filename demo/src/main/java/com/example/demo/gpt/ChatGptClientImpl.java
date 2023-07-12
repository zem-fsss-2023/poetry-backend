package com.example.demo.gpt;


import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatGptClientImpl implements ChatGptClient {

    private final String apiKey;

    private final ChatGptApi chatGptApi;

    @Autowired
    public ChatGptClientImpl(@Value("${gpt.api.url}") final String url,
                             @Value("${gpt.api.key}") final String apiKey,
                             ) {

        this.apiKey = apiKey;
        this.chatGptApi = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(ChatGptApi.class, url);
    }

    @Override
    public GptModelsResponseDto getModels() {
        return chatGptApi.getModels(apiKey);
    }

    @Override
    public String generateNoteText() {
        GptAutocompletionRequestDto requestBody = new GptAutocompletionRequestDto(
                "gpt-3.5-turbo",
                "create a random to do item that would appear on a random to do list. Return only the description, nothing else"
        );
        logger.info("Generating new Note text");
        noteGeneratorCounter.increment();
        GptAutocompletionResponseDto response = noteGenerationTimer.record(() -> chatGptApi.generateTask(requestBody, apiKey));

        return response.getAutocompletedMesage();
    }
}
