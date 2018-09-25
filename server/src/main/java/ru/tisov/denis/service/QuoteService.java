package ru.tisov.denis.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.tisov.denis.dao.QuoteDao;
import ru.tisov.denis.dto.Quote;

@Service
public class QuoteService {

    private final QuoteDao quoteDao;

    public QuoteService(QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    public Flux<Quote> getQuotes(String securityId) {
        return quoteDao.getQuotes(securityId);
    }

}
