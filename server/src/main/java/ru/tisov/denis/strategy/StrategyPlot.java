package ru.tisov.denis.strategy;

import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Plot;

public interface StrategyPlot {

    Flux<Plot> getPlots(String securityId);

}
