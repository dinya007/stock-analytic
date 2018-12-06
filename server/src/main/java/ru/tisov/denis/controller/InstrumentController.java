package ru.tisov.denis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.tisov.denis.Application;
import ru.tisov.denis.dto.Instrument;

import java.util.List;

@RestController
public class InstrumentController {

    @GetMapping("/api/instruments")
    public Mono<List<String>> getPlot() {
        return Application.SUPPORTED_INSTRUMENTS.filter(Instrument::isLoaded).map(Instrument::getSecurityId).collectList();
    }

}
