package ru.megalomaniac.securities.model;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TradingHistoryXmlStringTest {
    @Test
    public void testSecuritiesInfoXmlString(){
        TradingHistory tradingHistory = new TradingHistory();

        try {
            tradingHistory.setId(1);
            tradingHistory.setSecid("AAPP");
            tradingHistory.setTradedate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-20"));
            tradingHistory.setNumtrades(10.0);
            tradingHistory.setOpen(1.0);
            tradingHistory.setClose(5.0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TradingHistoryXmlString tradingHistoryXmlString = new TradingHistoryXmlString(tradingHistory);
        assertEquals("1",tradingHistoryXmlString.getId());
        assertEquals("AAPP",tradingHistoryXmlString.getSecid());
        assertEquals("2021-07-20",tradingHistoryXmlString.getTradedate());
        assertEquals("10.0",tradingHistoryXmlString.getNumtrades());
        assertEquals("1.0",tradingHistoryXmlString.getOpen());
        assertEquals("5.0",tradingHistoryXmlString.getClose());

    }

    @Test
    public void testConvertToSecuritiesInfo(){
        TradingHistory tradingHistory1 = new TradingHistory();

        try {
            tradingHistory1.setId(1);
            tradingHistory1.setSecid("AAPP");
            tradingHistory1.setTradedate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-20"));
            tradingHistory1.setNumtrades(10.0);
            tradingHistory1.setOpen(1.0);
            tradingHistory1.setClose(5.0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TradingHistoryXmlString tradingHistoryXmlString = new TradingHistoryXmlString(tradingHistory1);
        TradingHistory tradingHistory2 = tradingHistoryXmlString.convertToTradingHistory();
        assertTrue(tradingHistory1.equals(tradingHistory2));
    }
}
