package ru.tisov.denis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import ru.tisov.denis.dao.MoexDao;
import ru.tisov.denis.dto.Quote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class HistoryDataLoader {

    private final MoexDao moexDao;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String storagePath = "/Users/denis/Documents/Java/Idea_Projects/stock-analytic/data";
    private final int BATCH_SIZE = 100;

    public HistoryDataLoader(MoexDao moexDao) {
        this.moexDao = moexDao;
    }

    public void load(String securityId) {
        BufferedWriter writer = getBufferedWriter(securityId);
        Flux
                .range(0, BATCH_SIZE)
                .map(number -> number * 100)
                .handle((BiConsumer<Integer, SynchronousSink<List<Quote>>>) (start, synchronousSink) -> {
                    try {
                        List<Quote> quotes = moexDao.getHistoryData(securityId, start).execute().body().getHistoryData().getRows().getQuotes();
                        if (quotes != null) {
                            synchronousSink.next(quotes);
                        } else {
                            synchronousSink.complete();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMapIterable(list -> list)
                .map(quote -> {
                    try {
                        return objectMapper.writeValueAsString(quote) + "\n";
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .doAfterTerminate(() -> close(writer))
                .subscribe(ex(writer::write));
    }

    private <T, E extends Exception> Consumer<T> ex(ConsumerWithException<T, E> fe) {
        return arg -> {
            try {
                fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private BufferedWriter getBufferedWriter(String securityId) {
        try {
            File outputFile = new File(storagePath + "/" + securityId + ".json");
            return new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(BufferedWriter writer) {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
