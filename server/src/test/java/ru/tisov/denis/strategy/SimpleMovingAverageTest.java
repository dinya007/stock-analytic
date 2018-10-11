package ru.tisov.denis.strategy;

import org.junit.Assert;
import org.junit.Test;
import ru.tisov.denis.dto.Plot;
import ru.tisov.denis.dto.Quote;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class SimpleMovingAverageTest {

    @Test
    public void testMovingAverage() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(null);

        Quote quote1 = new Quote();
        quote1.setClose(BigDecimal.valueOf(5.3));
        quote1.setTradeDate(LocalDate.of(2018, Month.JANUARY, 1));

        Quote quote2 = new Quote();
        quote2.setClose(BigDecimal.valueOf(6.7));
        quote2.setTradeDate(LocalDate.of(2018, Month.JANUARY, 2));

        Quote quote3 = new Quote();
        quote3.setClose(BigDecimal.valueOf(7.9));
        quote3.setTradeDate(LocalDate.of(2018, Month.JANUARY, 3));

        Quote quote4 = new Quote();
        quote4.setClose(BigDecimal.valueOf(7.1));
        quote4.setTradeDate(LocalDate.of(2018, Month.JANUARY, 4));

        Quote quote5 = new Quote();
        quote5.setClose(BigDecimal.valueOf(5.2));
        quote5.setTradeDate(LocalDate.of(2018, Month.JANUARY, 5));

        Quote quote6 = new Quote();
        quote6.setClose(BigDecimal.valueOf(4.1));
        quote6.setTradeDate(LocalDate.of(2018, Month.JANUARY, 6));

        Quote quote7 = new Quote();
        quote7.setClose(BigDecimal.valueOf(3.5));
        quote7.setTradeDate(LocalDate.of(2018, Month.JANUARY, 7));


        Plot plot = simpleMovingAverage.movingAverage(Arrays.asList(quote1, quote2, quote3, quote4, quote5, quote6, quote7), 4);

        Assert.assertEquals("Экспоненциальная скользязая средняя за 4 дней", plot.getName());
        Assert.assertNull(plot.getPoints().get(0).getValue());
        Assert.assertNull(plot.getPoints().get(1).getValue());
        Assert.assertNull(plot.getPoints().get(2).getValue());
        Assert.assertTrue(BigDecimal.valueOf(6.8).compareTo(plot.getPoints().get(3).getValue()) == 0);
        Assert.assertTrue(BigDecimal.valueOf(6.8).compareTo(plot.getPoints().get(4).getValue()) == 0);
        Assert.assertTrue(BigDecimal.valueOf(6.1).compareTo(plot.getPoints().get(5).getValue()) == 0);
        Assert.assertTrue(BigDecimal.valueOf(5.0).compareTo(plot.getPoints().get(6).getValue()) == 0);
    }
}