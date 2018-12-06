package ru.tisov.denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Instrument;
import ru.tisov.denis.service.HistoryDataLoader;

@SpringBootApplication
public class Application {

    public static Flux<Instrument> SUPPORTED_INSTRUMENTS = Flux.just(new Instrument("SBER", false), new Instrument("GAZP", false), new Instrument("YNDX", false), new Instrument("MOEX", false));

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        HistoryDataLoader historyDataLoader = context.getBean(HistoryDataLoader.class);
        SUPPORTED_INSTRUMENTS.parallel().subscribe(historyDataLoader::load);
    }

}
