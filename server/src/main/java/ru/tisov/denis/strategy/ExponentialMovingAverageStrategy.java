package ru.tisov.denis.strategy;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dto.Plot;
import ru.tisov.denis.dto.PlotPoint;
import ru.tisov.denis.dto.Quote;
import ru.tisov.denis.service.QuoteService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

public class ExponentialMovingAverageStrategy implements Strategy {

    @Override
    public Flux<Plot> getPlots(String securityId) {
        return null;
    }

//    private final QuoteService quoteService;
//
//    public ExponentialMovingAverageStrategy(QuoteService quoteService) {
//        this.quoteService = quoteService;
//    }
//
//    @Override
//    public Flux<Plot> getPlots(String securityId) {
//        Flux<Quote> quotes = quoteService.getQuotes(securityId);
//        return Flux.just(movingAverage(quotes.collectList().block(), 50), movingAverage(quotes, 100));
//    }
//
//    private Plot movingAverage(List<Quote> quotes, int n) {
//        List<PlotPoint> plotPoints = new ArrayList<>(quotes.size());
//
//        if (quotes.size() < n) {
//            throw new IllegalStateException("Недостаточное количество данных для построения графика");
//        }
//
//        for (int i = 0; i < n; i++) {
//            plotPoints.add(new PlotPoint(quotes.get(i).getTradeDate(), null));
//        }
//
//        for (int i = n; i < quotes.size(); i++) {
//            BigDecimal previousMovingAveragePoint = Optional.ofNullable(plotPoints.get(i - n).getValue()).orElse(BigDecimal.ZERO);
//        }
//
//        List<PlotPoint> plotPoints = quotes.skip(n).map(quote -> {
//            quotes.elementAt()
//
//            return new PlotPoint(quote.getTradeDate(), null);
//        }).collectList().block();
//
//
//        return new Plot(String.format("Экспоненциальная скользязая средняя за %d дней", n), plotPoints);
//    }

}
