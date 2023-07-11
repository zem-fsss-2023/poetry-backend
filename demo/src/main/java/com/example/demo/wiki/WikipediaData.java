package com.example.demo.wiki;


import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "wikipedia", createIndex = false)
public record WikipediaData(@Id Long id, String title, String text, String redirectTitle, List<String> parsedParagraphs, WikipediaRelations relations) { }
