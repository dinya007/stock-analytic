package ru.tisov.denis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import ru.tisov.denis.dao.MoexDao;
import ru.tisov.denis.dto.Instrument;
import ru.tisov.denis.dto.Quote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import static ru.tisov.denis.utils.Utils.ex;

@Service
public class HistoryDataLoader {

    private final MoexDao moexDao;
    private final ObjectMapper objectMapper;
    public static final String STORAGE_PATH = "/Users/denis/Documents/Java/Idea_Projects/stock-analytic/data";
    private final int BATCH_SIZE = 100;

    public HistoryDataLoader(MoexDao moexDao, ObjectMapper objectMapper) {
        this.moexDao = moexDao;
        this.objectMapper = objectMapper;
    }

    public void load(Instrument instrument) {
        if (loaded(instrument)) {
            System.out.println(instrument.getSecurityId() + " already loaded");
            instrument.setLoaded(true);
            return;
        }
        final long startTime = System.currentTimeMillis();
        BufferedWriter writer = getBufferedWriter(instrument.getSecurityId());
        Flux
                .range(0, Integer.MAX_VALUE)
                .map(number -> number * BATCH_SIZE)
                .handle((BiConsumer<Integer, SynchronousSink<List<Quote>>>) (start, synchronousSink) -> {
                    try {
                        List<Quote> quotes = moexDao.getHistoryData(instrument.getSecurityId(), LocalDate.now(), start, BATCH_SIZE).execute().body().getHistoryData().getRows().getQuotes();
                        if (quotes != null) {
                            synchronousSink.next(quotes);
                        } else {
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
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
                .doAfterTerminate(() -> {
                    close(writer);
                    instrument.setLoaded(true);
                    System.out.println(instrument.getSecurityId() + " loaded by " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime) + " seconds");
                })
                .subscribe(ex(writer::write));
    }


    private boolean loaded(Instrument instrument) {
        return new File(STORAGE_PATH + "/" + instrument.getSecurityId() + ".json").exists();
    }

    private BufferedWriter getBufferedWriter(String securityId) {
        try {
            File outputFile = new File(STORAGE_PATH + "/" + securityId + ".json");
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
