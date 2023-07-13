package com.example.demo.wiki;
import com.example.demo.poetry.PoetryClientImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
public class WikipediaController {
    private final WikipediaRepository wikipediaRepository;
    private final Logger logger = LoggerFactory.getLogger(WikipediaController.class.getName());
    private final Counter getWikiCounter;
    private final Timer getWikiTimer;

    public WikipediaController(WikipediaRepository wikipediaRepository, MeterRegistry meterRegistry){
        this.wikipediaRepository = wikipediaRepository;
        this.getWikiCounter = meterRegistry.counter("getWikiCounter");
        this.getWikiTimer = meterRegistry.timer("getWikiTimer");
    }
    @GetMapping("wiki")
    public WikipediaData getWiki(@RequestParam String title){
        logger.info(String.format("Retrieving wiki page with title:\"%s\".", title));
        getWikiCounter.increment();
        return getWikiTimer.record(() -> wikipediaRepository.findByTitle(title));
    }
}
