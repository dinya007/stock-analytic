package ru.tisov.denis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlotPoint {

    private LocalDate date;
    private BigDecimal value;

    public PlotPoint() {
    }

    public PlotPoint(LocalDate date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PlotPoint{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
