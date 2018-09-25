package ru.tisov.denis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Quote;
import ru.tisov.denis.service.QuoteService;

@RestController
public class PlotController {

    private final QuoteService quoteService;

    public PlotController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/api/plots")
    public Flux<Quote> getPlot() {
        return quoteService.getQuotes("SBER");
    }
}
