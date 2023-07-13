package com.example.demo.poetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class PoetryController {

    private final PoetryClient poetryClient;
    private final Logger logger = LoggerFactory.getLogger(PoetryController.class.getName());

    public PoetryController(PoetryClient poetryClient) {
        this.poetryClient = poetryClient;
    }

    @GetMapping("poets")
    public ResponseEntity<List<String>> getPoets() {
        logger.info("Retrieving all available poets.");
        List<String> poets = poetryClient.getPoets().authors();
        return ResponseEntity.ok(poets);
    }

    @GetMapping("poems")
    public ResponseEntity<List<PoemResponseDto>> getPoems(@RequestParam String poet) {
        logger.info(String.format("Retrieving poems of %s.", poet));
        List<PoemResponseDto> poems = poetryClient.getPoems(poet);
        return ResponseEntity.ok(poems);
    }
}
