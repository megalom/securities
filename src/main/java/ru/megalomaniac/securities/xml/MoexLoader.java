package ru.megalomaniac.securities.xml;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import ru.megalomaniac.securities.model.SecuritiesInfo;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Класс для работы с московской биржей
public class MoexLoader {

    // Загрузка ценной бумаги с московской биржи
    public static SecuritiesInfo loadSecurities(String secid) {

        List<SecuritiesInfo> securities = new ArrayList<>();

        // REST запрос к бирже для получения информации о ценных бумагах
        RestTemplate restTemplate = new RestTemplate();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        String resourceUrl
                = "http://iss.moex.com/iss/securities.xml?q=" + secid;
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(resourceUrl, String.class);
        } catch (RestClientException e) {
            System.out.println("moex resource is unreachable.");
        }

        if ((response != null) && (response.getStatusCode().equals(HttpStatus.OK))) {
            try (InputStream is = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8))) {
                SAXParser parser = parserFactory.newSAXParser();
                SAXHandler handler = new SAXHandler();
                parser.parse(is, handler);
                securities = handler.getSecurities().stream()
                        .filter(sec -> sec.getSecid().equalsIgnoreCase(secid))
                        .collect(Collectors.toList());
            }  catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (securities.size() == 0)
            return null;
        return securities.get(0);
    }
}
