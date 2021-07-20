package ru.megalomaniac.securities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "trading_history")
public class TradingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    // Торговый код
    @Column(name = "secid")
    @NotBlank(message = "{TradingHistory.secid.notblank}")
    @Pattern(regexp = "[a-zA-Z0-9]{4,18}",message = "{TradingHistory.secid.pattern}")
    @JacksonXmlProperty(isAttribute = true)
    private String secid;

    // Дата торгов
    @Column(name = "tradedate")
    @NotNull(message = "{TradingHistory.tradedate.notempty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JacksonXmlProperty(isAttribute = true)
    private Date tradedate;

    // Количество сделок за день
    @Column(name = "numtrades")
    @DecimalMin(value = "0",message = "{TradingHistory.numtrades.min}")
    @JacksonXmlProperty(isAttribute = true)
    private double numtrades;

    // Цена предторгового периода/Цена аукциона открытия
    @Column(name = "open")
    @DecimalMin(value = "0",message = "{TradingHistory.open.min}")
    @JacksonXmlProperty(isAttribute = true)
    private double open;

    // Цена закрытия
    @Column(name = "close")
    @DecimalMin(value = "0",message = "{TradingHistory.close.min}")
    @JacksonXmlProperty(isAttribute = true)
    private double close;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="secid", referencedColumnName = "secid",nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    SecuritiesInfo securitiesInfo;

    public TradingHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public Date getTradedate() {
        return tradedate;
    }

    public void setTradedate(Date tradedate) {
        this.tradedate = tradedate;
    }

    public double getNumtrades() {
        return numtrades;
    }

    public void setNumtrades(double numtrades) {
        this.numtrades = numtrades;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public SecuritiesInfo getSecuritiesInfo() {
        return securitiesInfo;
    }

    public void setSecuritiesInfo(SecuritiesInfo securitiesInfo) {
        this.securitiesInfo = securitiesInfo;
    }

    @Override
    public String toString() {
        return "TradingHistory{" +
                "id=" + id +
                ", secId='" + secid + '\'' +
                ", tradeDate='" + tradedate + '\'' +
                ", numtrades=" + numtrades +
                ", open=" + open +
                ", close=" + close +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingHistory that = (TradingHistory) o;
        return id == that.id &&
                Double.compare(that.numtrades, numtrades) == 0 &&
                Double.compare(that.open, open) == 0 &&
                Double.compare(that.close, close) == 0 &&
                secid.equals(that.secid) &&
                tradedate.equals(that.tradedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secid, tradedate, numtrades, open, close);
    }
}
