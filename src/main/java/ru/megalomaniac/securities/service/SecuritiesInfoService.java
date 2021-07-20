package ru.megalomaniac.securities.service;

import ru.megalomaniac.securities.model.SecuritiesInfo;

import java.util.List;

public interface SecuritiesInfoService{
    List<SecuritiesInfo> findAll();
    SecuritiesInfo findById(int id);
    void save(SecuritiesInfo securitiesInfo);
    void deleteById(int id);
    Boolean existsBySecid(String secid);
    Boolean existById(int id);
    SecuritiesInfo findBySecid(String secid);
}
