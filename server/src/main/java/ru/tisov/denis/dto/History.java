package ru.tisov.denis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class History {

    @XmlElement(name = "data")
    private HistoryData historyData;

    public History() {
    }

    public HistoryData getHistoryData() {
        return historyData;
    }

    public void setHistoryData(HistoryData historyData) {
        this.historyData = historyData;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyData=" + historyData +
                '}';
    }
}
