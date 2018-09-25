package ru.tisov.denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Flux;
import ru.tisov.denis.service.HistoryDataLoader;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        HistoryDataLoader historyDataLoader = context.getBean(HistoryDataLoader.class);

        Flux.just("SBER", "GAZP", "YNDX").parallel().subscribe(historyDataLoader::load);
    }

}
