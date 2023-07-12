package com.example.demo.gpt;

import java.util.List;

public record PoemRequest(
        String poet,
        List<String> topics) {
}
