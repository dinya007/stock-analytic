package ru.tisov.denis.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Quote;
import ru.tisov.denis.service.HistoryDataLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.BaseStream;

@Repository
public class QuoteDao {

    private final ObjectMapper objectMapper;

    public QuoteDao(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Flux<Quote> getQuotes(String securityId) {
        String path = HistoryDataLoader.STORAGE_PATH + "/" + securityId + ".json";
        return Flux.using(() -> Files.lines(Paths.get(path)), Flux::fromStream, BaseStream::close)
                .map(line -> {
                    try {
                        return objectMapper.readValue(line, Quote.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
