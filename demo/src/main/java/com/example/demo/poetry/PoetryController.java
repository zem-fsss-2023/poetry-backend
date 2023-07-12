package com.example.demo.poetry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PoetryController {

    private final PoetryClient poetryClient;

    public PoetryController(PoetryClient poetryClient) {
        this.poetryClient = poetryClient;
    }

    @GetMapping("poets")
    public ResponseEntity<List<String>> getPoets() {
        List<String> poets = poetryClient.getPoets().authors();
        return ResponseEntity.ok(poets);
    }

    @GetMapping("poems")
    public ResponseEntity<List<PoemResponseDto>> getPoets(@RequestParam String poet) {
        System.out.println(poet);
        List<PoemResponseDto> poems = poetryClient.getPoems(poet);
        return ResponseEntity.ok(poems);
    }
}
