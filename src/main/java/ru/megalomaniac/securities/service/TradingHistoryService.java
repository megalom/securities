package ru.megalomaniac.securities.service;

import ru.megalomaniac.securities.model.TradingHistory;

import java.util.List;

public interface TradingHistoryService {
    List<TradingHistory> findAll();
    List<TradingHistory> findAllFromPage(int limit,int offset);
    TradingHistory findById(int id);
    Boolean existsBySecid(String secid);
    Boolean existById(int id);
    void save(TradingHistory tradingHistory);
    void deleteById(int id);
    long getCount();

}
