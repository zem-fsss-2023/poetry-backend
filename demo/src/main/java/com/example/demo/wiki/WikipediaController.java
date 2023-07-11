package com.example.demo.wiki;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")   //localhost:8080/api
public class WikipediaController {
    private final WikipediaRepository wikipediaRepository;

    public WikipediaController(WikipediaRepository wikipediaRepository){
        this.wikipediaRepository = wikipediaRepository;
    }
    @GetMapping("wiki")  //localhost:8080/api/wiki
    public WikipediaData getWiki(){
        return wikipediaRepository.findByTitle("William Shakespeare");
    }
}
