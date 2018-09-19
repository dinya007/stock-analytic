package ru.tisov.denis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryData {

    @XmlElement(name = "rows")
    private HistoryRows rows;

    public HistoryData() {
    }

    public HistoryRows getRows() {
        return rows;
    }

    public void setRows(HistoryRows rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "rows=" + rows +
                '}';
    }
}
