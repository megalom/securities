package ru.megalomaniac.securities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.megalomaniac.securities.exceptions.SecuritiesNotFoundException;
import ru.megalomaniac.securities.model.TradingHistory;
import ru.megalomaniac.securities.repository.TradingHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TradingHistoryServiceImpl implements TradingHistoryService{
    @Autowired
    private TradingHistoryRepository tradingHistoryRepository;

    @Override
    public List<TradingHistory> findAll() {
        return tradingHistoryRepository.findAll();
    }

    @Override
    public TradingHistory findById(int id) {
        Optional<TradingHistory> result = tradingHistoryRepository.findById(id);
        TradingHistory tradingHistory;
        if(result.isPresent()){
            tradingHistory = result.get();
        }
        else{
            throw new SecuritiesNotFoundException("trading history id "+id+" not found");
        }
        return tradingHistory;
    }

    @Override
    public void save(TradingHistory tradingHistory) {
        tradingHistoryRepository.save(tradingHistory);
    }

    @Override
    public void deleteById(int id) {
        tradingHistoryRepository.deleteById(id);
    }

    @Override
    public List<TradingHistory> findAllFromPage(int limit,int offset) {
        return tradingHistoryRepository.findAllFromPage(limit,offset);
    }

    @Override
    public Boolean existsBySecid(String secid) {
        return tradingHistoryRepository.existsBySecid(secid);
    }

    @Override
    public Boolean existById(int id) {
        return tradingHistoryRepository.existsById(id);
    }

    @Override
    public long getCount() {
        return tradingHistoryRepository.count();
    }
}
