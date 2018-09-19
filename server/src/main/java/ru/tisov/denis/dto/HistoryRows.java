package ru.tisov.denis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryRows {

    @XmlElement(name = "row")
    private List<Quote> quotes;

    public HistoryRows() {
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "quotes=" + quotes +
                '}';
    }

}
