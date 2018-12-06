package ru.tisov.denis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Plot;
import ru.tisov.denis.strategy.StrategyPlot;

@RestController
public class PlotController {

    private final StrategyPlot strategyPlot;

    public PlotController(StrategyPlot strategyPlot) {
        this.strategyPlot = strategyPlot;
    }

    @GetMapping("/api/plots")
    public Flux<Plot> getPlot(@RequestParam("securityId") String securityId) {
        return strategyPlot.getPlots(securityId);
    }

}
