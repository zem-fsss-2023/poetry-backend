package com.example.demo.gpt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class GptController {
    private final GptClient gptClient;

    public GptController(GptClient gptClient) {
        this.gptClient = gptClient;
    }

    @PostMapping("gpt")
    public ResponseEntity<String> getGptPoem(@RequestBody PoemRequest poemRequest) {
        String topicList = String.join(", ", poemRequest.topics());
        String command = String.format("Write me a short poem about %s in style of %s.",
                topicList,
                poemRequest.poet());

        String gptPoem = gptClient.generateNoteText(command);
        return ResponseEntity.ok(gptPoem);
    }
}
