package ru.megalomaniac.securities.xml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.model.TradingHistory;
import ru.megalomaniac.securities.service.SecuritiesInfoService;
import ru.megalomaniac.securities.service.TradingHistoryService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecuritiesXmlImport extends DefaultHandler{
    @Autowired
    SecuritiesInfoService securitiesInfoService;

    @Autowired
    TradingHistoryService tradingHistoryService;

    private List<SecuritiesInfo> securitiesInfos= new ArrayList<>();
    private List<TradingHistory> tradingHistory=new ArrayList<>();
    private List<String> errors=new ArrayList<>();

    public boolean importFromFile(InputStream is){
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try{
            SAXParser parser = parserFactory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(is,handler);
            tradingHistory = handler.getTradingHistory();
            securitiesInfos = handler.getSecurities();
            errors = handler.getErrors();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Сохранение загруженных данных в БД
    public void saveToDb(){
        if(securitiesInfos.size()>0)
            securitiesInfos.stream().forEach((sInfo)->{
                if(!securitiesInfoService.existsBySecid(sInfo.getSecid()))
                    securitiesInfoService.save(sInfo);
                else{
                    errors.add("Ценная бумага с индексом "+sInfo.getSecid()+" уже существует");
                }
            });

        if(tradingHistory.size()>0){
            tradingHistory.stream().forEach((tHistory)->{
                SecuritiesInfo sec =securitiesInfoService.findBySecid(tHistory.getSecid());
                // Если ценная бумага существует то сохраняем историю операции
                if(sec!=null) {
                    tradingHistoryService.save(tHistory);
                }
                // Иначе попробуем отискать на московской бирже
                else {
                    SecuritiesInfo secFromMoex = MoexLoader.loadSecurities(tHistory.getSecid());
                    if(secFromMoex!=null) {
                        securitiesInfoService.save(secFromMoex);
                        tradingHistoryService.save(tHistory);
                    }
                    else{
                        errors.add("Ценная бумага с кодом "+tHistory.getSecid()+"не найдена.");
                    }
                }

            });
        }

        securitiesInfos.clear();
        tradingHistory.clear();
    }

    public List<String> getErrors(){
        return errors;
    }

    public int getRecordsCount(){
        return securitiesInfos.size()+tradingHistory.size();
    }

}


