package ru.tisov.denis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Plot;
import ru.tisov.denis.strategy.Strategy;

@RestController
public class PlotController {

    private final Strategy quoteService;

    public PlotController(Strategy stategy) {
        this.quoteService = stategy;
    }

    @GetMapping("/api/plots")
    public Flux<Plot> getPlot() {
        return quoteService.getPlots("SBER");
    }
}
