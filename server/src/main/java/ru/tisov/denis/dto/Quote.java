package ru.tisov.denis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Quote {

    @XmlAttribute(name = "BOARDID")
    private String boardId;
    @XmlAttribute(name = "TRADEDATE")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate tradeDate;
    @XmlAttribute(name = "SHORTNAME")
    private String name;
    @XmlAttribute(name = "SECID")
    private String securityId;
    @XmlAttribute(name = "NUMTRADES")
    private BigDecimal numTrades;
    @XmlAttribute(name = "VALUE")
    private BigDecimal value;
    @XmlAttribute(name = "OPEN")
    private BigDecimal open;
    @XmlAttribute(name = "LOW")
    private BigDecimal low;
    @XmlAttribute(name = "HIGH")
    private BigDecimal high;
    @XmlAttribute(name = "LEGALCLOSEPRICE")
    private BigDecimal legalClosePrice;
    @XmlAttribute(name = "WAPRICE")
    private BigDecimal warPrice;
    @XmlAttribute(name = "CLOSE")
    private BigDecimal close;
    @XmlAttribute(name = "VOLUME")
    private BigDecimal volume;

    public Quote() {
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public BigDecimal getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(BigDecimal numTrades) {
        this.numTrades = numTrades;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLegalClosePrice() {
        return legalClosePrice;
    }

    public void setLegalClosePrice(BigDecimal legalClosePrice) {
        this.legalClosePrice = legalClosePrice;
    }

    public BigDecimal getWarPrice() {
        return warPrice;
    }

    public void setWarPrice(BigDecimal warPrice) {
        this.warPrice = warPrice;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "boardId='" + boardId + '\'' +
                ", tradeDate=" + tradeDate +
                ", name='" + name + '\'' +
                ", securityId='" + securityId + '\'' +
                ", numTrades=" + numTrades +
                ", value=" + value +
                ", open=" + open +
                ", low=" + low +
                ", high=" + high +
                ", legalClosePrice=" + legalClosePrice +
                ", warPrice=" + warPrice +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }

}
