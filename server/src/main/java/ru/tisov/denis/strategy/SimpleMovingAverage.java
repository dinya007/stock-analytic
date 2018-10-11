package ru.tisov.denis.strategy;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tisov.denis.dto.Plot;
import ru.tisov.denis.dto.PlotPoint;
import ru.tisov.denis.dto.Quote;
import ru.tisov.denis.service.QuoteService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SimpleMovingAverage implements Strategy {

    private final QuoteService quoteService;

    public SimpleMovingAverage(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Override
    public Flux<Plot> getPlots(String securityId) {
        Flux<Quote> quotes = quoteService.getQuotes(securityId);
        return Flux.mergeSequential(
                Arrays.asList(
                        quotes.collectList().map(quotesList -> {
                            List<PlotPoint> plotPoints = new ArrayList<>();
                            for (Quote quote : quotesList) {
                                plotPoints.add(new PlotPoint(quote.getTradeDate(), quote.getClose()));
                            }
                            return new Plot(securityId, plotPoints);
                        }),
                        movingAverage(quotes.collectList(), 50),
                movingAverage(quotes.collectList(), 100))
        );
    }

    private Mono<Plot> movingAverage(Mono<List<Quote>> quotesMono, int n) {
        List<PlotPoint> plotPoints = new ArrayList<>();

        return quotesMono.map(quotes -> {
            if (quotes.size() < n) {
                throw new IllegalStateException("Недостаточное количество данных для построения графика");
            }


            int k = n - 1;

            for (int i = 0; i < k; i++) {
                plotPoints.add(new PlotPoint(quotes.get(i).getTradeDate(), null));
            }

            for (int i = k; i < quotes.size(); i++) {
                BigDecimal sum = BigDecimal.ZERO;
                for (int j = i - n + 1; j <= i; j++) {
                    sum = sum.add(quotes.get(j).getClose());
                }
                plotPoints.add(new PlotPoint(quotes.get(i).getTradeDate(), sum.divide(BigDecimal.valueOf(n), RoundingMode.CEILING)));
            }


            return new Plot(String.format("Экспоненциальная скользязая средняя за %d дней", n), plotPoints);
        });

    }

}
