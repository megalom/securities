package ru.megalomaniac.securities.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecuritiesInfoXmlStringTest {
    @Test
    public void testSecuritiesInfoXmlString(){
        SecuritiesInfo securitiesInfo = new SecuritiesInfo();
        securitiesInfo.setId(1);
        securitiesInfo.setSecid("AAPP");
        securitiesInfo.setName("Walt");
        securitiesInfo.setRegnumber("1-11-11111-A");
        securitiesInfo.setEmitentTitle("Disney");

        SecuritiesInfoXmlString securitiesInfoXmlString = new SecuritiesInfoXmlString(securitiesInfo);
        assertEquals("1",securitiesInfoXmlString.getId());
        assertEquals("AAPP",securitiesInfoXmlString.getSecid());
        assertEquals("Walt",securitiesInfoXmlString.getName());
        assertEquals("1-11-11111-A",securitiesInfoXmlString.getRegnumber());
        assertEquals("Disney",securitiesInfoXmlString.getEmitentTitle());
    }

    @Test
    public void testConvertToSecuritiesInfo(){
        SecuritiesInfo securitiesInfo1 = new SecuritiesInfo();
        securitiesInfo1.setId(1);
        securitiesInfo1.setSecid("AAPP");
        securitiesInfo1.setName("Walt");
        securitiesInfo1.setRegnumber("1-11-11111-A");
        securitiesInfo1.setEmitentTitle("Disney");

        SecuritiesInfoXmlString securitiesInfoXmlString = new SecuritiesInfoXmlString(securitiesInfo1);
        SecuritiesInfo securitiesInfo2 = securitiesInfoXmlString.convertToSecuritiesInfo();
        assertTrue(securitiesInfo1.equals(securitiesInfo2));
    }
}
