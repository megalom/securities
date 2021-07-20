package ru.megalomaniac.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Класс для сохранения данных из SecuritiesInfo в xml
@JacksonXmlRootElement(localName = "history")
public class TradingHistoryXmlString implements Serializable {
    private static final long serialVersionUID = 1L;

    @JacksonXmlProperty()
    private String id;
    @JacksonXmlProperty()
    private String secid;
    @JacksonXmlProperty()
    private String tradedate;
    @JacksonXmlProperty()
    private String numtrades;
    @JacksonXmlProperty()
    private String open;
    @JacksonXmlProperty()
    private String close;

    public TradingHistoryXmlString(){

    }

    public TradingHistoryXmlString(TradingHistory tradingHistory){
        this.setId(String.valueOf(tradingHistory.getId()));
        this.setSecid(tradingHistory.getSecid());
        this.setTradedate(new SimpleDateFormat("yyyy-MM-dd").format(tradingHistory.getTradedate()));
        this.setNumtrades(String.valueOf(tradingHistory.getNumtrades()));
        this.setOpen(String.valueOf(tradingHistory.getOpen()));
        this.setClose(String.valueOf(tradingHistory.getClose()));
    }

    public TradingHistory convertToTradingHistory(){
        TradingHistory tradingHistory = new TradingHistory();
        tradingHistory.setId(Integer.parseInt(this.getId()));
        tradingHistory.setSecid(this.getSecid());

        try {
            tradingHistory.setTradedate(new SimpleDateFormat("yyyy-MM-dd").parse(this.getTradedate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tradingHistory.setNumtrades(Double.parseDouble(this.numtrades));
        tradingHistory.setOpen(Double.parseDouble(this.open));
        tradingHistory.setClose(Double.parseDouble(this.close));

        return tradingHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }

    public String getNumtrades() {
        return numtrades;
    }

    public void setNumtrades(String numtrades) {
        this.numtrades = numtrades;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }
}
