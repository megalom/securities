package ru.megalomaniac.securities.xml;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.helpers.DefaultHandler;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.model.TradingHistory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Обработчик xml файла
public class SAXHandler extends DefaultHandler {
    private List<SecuritiesInfo> securities = new ArrayList<>();
    private List<TradingHistory> tradingHistories = new ArrayList<>();
    private SecuritiesInfo securitiesInfo = null;
    private TradingHistory tradingHistory = null;
    private List<String> errors = new ArrayList<>();
    private Locator locator;

    // Список типов данных в xml файле
    private enum DataType {
        UNKNOWN, SECURITIES, HISTORY
    }

    private DataType dataType = DataType.UNKNOWN;

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        // перебор тегов
        switch (qName) {
            // определение типа загружаеммых данных из тега <data id="">
            case "data":
                // выбор типа данных из атрибута id
                switch (attributes.getValue("id")) {
                    case "securities":
                        dataType = DataType.SECURITIES;
                        break;
                    case "history":
                        dataType = DataType.HISTORY;
                        break;
                    default:
                        break;
                }
                break;

            // загрузка данных об объекте из атрибутов тега <row ...>
            case "row":
                switch (dataType) {
                    case SECURITIES:
                        securitiesInfo = getSecurities(attributes);
                        break;
                    case HISTORY:
                        tradingHistory = getTradingHistory(attributes);
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }
    }

    private SecuritiesInfo getSecurities(Attributes attributes){
        SecuritiesInfo result = new SecuritiesInfo();
        result.setId(getInteger(
                attributes.getValue("id"),"id",locator.getLineNumber()
        ));
        result.setSecid(attributes.getValue("secid"));
        result.setName(attributes.getValue("name"));
        result.setRegnumber(attributes.getValue("regnumber"));
        result.setEmitentTitle(attributes.getValue("emitent_title"));
        return result;
    }

    private TradingHistory getTradingHistory(Attributes attributes) {
        TradingHistory result = new TradingHistory();
        result.setId(0);

        result.setSecid(attributes.getValue("SECID"));

        result.setNumtrades(getDouble(
                attributes.getValue("NUMTRADES"), "NUMTRADES", locator.getLineNumber()
        ));

        result.setTradedate(getDate(
                attributes.getValue("TRADEDATE"),"TRADEDATE", locator.getLineNumber()
        ));

        result.setOpen(getDouble(
                attributes.getValue("OPEN"), "OPEN", locator.getLineNumber()
        ));

        result.setClose(getDouble(
                attributes.getValue("CLOSE"), "CLOSE", locator.getLineNumber()
        ));

        return result;
    }


    @Override
    public void endElement(String uri, String localName, String qName){
        switch (qName) {
            // если дошли до закрывающего тега добавляем объект в список
            case "row":
                switch (dataType) {
                    case SECURITIES:
                        securities.add(securitiesInfo);
                        securitiesInfo = null;
                        break;
                    case HISTORY:
                        tradingHistories.add(tradingHistory);
                        tradingHistory = null;
                        break;
                }
                break;
            case "data":
                dataType = DataType.UNKNOWN;
            default:
                break;
        }
    }

    @Override
    public void endDocument(){

    }

    public List<SecuritiesInfo> getSecurities() {
        return securities;
    }

    public List<TradingHistory> getTradingHistory() {
        return tradingHistories;
    }

    public List<String> getErrors(){
        return errors;
    }



    // Приведение типов с проверкой строки и запись ошибок в errors
    private Integer getInteger(String str, String attributeName, int lineNumber) {
        int result = 0;
        if (str.matches("\\d+"))
            result = Integer.parseInt(str);
        else
            errors.add(
                    new StringBuilder().append("Не установлено значение: ").append(str).append(" для атрибута ")
                    .append(attributeName).append(" в строке ").append(lineNumber)
                    .append(" установлено по умолчанию в 0\n").toString()
            );
        return result;
    }

    private Date getDate(String str, String attributeName, int lineNumber) {
        Date result = Date.valueOf("1970-01-01");
        if (str.matches("\\d{4}-\\d{2}-\\d{2}"))
            result = Date.valueOf(str);
        else {
            if(str.isEmpty())
                str="\"\"";
            errors.add(
                    new StringBuilder().append("Не установлено значение: ").append(str).append(" для атрибута ")
                    .append(attributeName).append(" в строке ").append(lineNumber)
                    .append(" установлено по умолчанию в 1970-01-01\n").toString()
            );
        }
        return result;
    }

    private Double getDouble(String str, String attributeName, int lineNumber) {
        double result = 0.0;
        if (str.matches("\\d+.\\d+|\\d+"))
            result = Double.parseDouble(str);
        else {
            if(str.isEmpty())
                str="\"\"";
            errors.add(
                    new StringBuilder().append("Не установлено значение: ").append(str).append(" для атрибута ")
                    .append(attributeName).append(" в строке ").append(lineNumber)
                    .append("устанолвено по умолчанию в 0.0\n").toString()
            );
        }
        return result;
    }
}
