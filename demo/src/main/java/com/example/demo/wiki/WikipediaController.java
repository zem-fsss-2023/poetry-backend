package com.example.demo.wiki;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class WikipediaController {
    private final WikipediaRepository wikipediaRepository;

    public WikipediaController(WikipediaRepository wikipediaRepository){
        this.wikipediaRepository = wikipediaRepository;
    }
    @GetMapping("wiki")
    public WikipediaData getWiki(@RequestParam String title){
        return wikipediaRepository.findByTitle(title);
    }
}
