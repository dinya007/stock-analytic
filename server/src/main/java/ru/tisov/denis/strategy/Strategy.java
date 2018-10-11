package ru.tisov.denis.strategy;

import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Plot;

public interface Strategy {

    Flux<Plot> getPlots(String securityId);

}
