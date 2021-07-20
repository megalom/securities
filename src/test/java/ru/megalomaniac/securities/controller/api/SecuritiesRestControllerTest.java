package ru.megalomaniac.securities.controller.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.service.SecuritiesInfoService;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SecuritiesRestController.class)
public class SecuritiesRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SecuritiesInfoService securitiesInfoService;


    private SecuritiesInfo securitiesInfo1;
    private SecuritiesInfo securitiesInfo2;
    private SecuritiesInfo securitiesInfo3;

    @Before
    public void setUp(){
        securitiesInfo1= new SecuritiesInfo();
        securitiesInfo1.setId(1);
        securitiesInfo1.setSecid("AAPL");
        securitiesInfo1.setName("Warner");
        securitiesInfo1.setRegnumber("1-11-11111-A");
        securitiesInfo1.setEmitentTitle("WB");

        securitiesInfo2= new SecuritiesInfo();
        securitiesInfo2.setId(2);
        securitiesInfo2.setSecid("BOPD");
        securitiesInfo2.setName("Walt");
        securitiesInfo2.setRegnumber("2-11-11111-A");
        securitiesInfo2.setEmitentTitle("Disney");

        securitiesInfo3= new SecuritiesInfo();
        securitiesInfo3.setId(3);
        securitiesInfo3.setSecid("KTYL");
        securitiesInfo3.setName("Stan");
        securitiesInfo3.setRegnumber("3-11-11111-A");
        securitiesInfo3.setEmitentTitle("Marvel");
    }

    @Test
    public void testFindAll() throws Exception {
        when(securitiesInfoService.findAll()).thenReturn(Arrays.asList(
                securitiesInfo1,securitiesInfo2,securitiesInfo3));
        MvcResult mvcResult = mockMvc.perform(get("/api/securities"))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        String testResult = "<document>" +
                "<data id=\"securities\">" +
                "<metadata/>" +
                "<rows>" +
                "<row id=\"1\" secid=\"AAPL\" regnumber=\"1-11-11111-A\" name=\"Warner\" emitentTitle=\"WB\"/>" +
                "<row id=\"2\" secid=\"BOPD\" regnumber=\"2-11-11111-A\" name=\"Walt\" emitentTitle=\"Disney\"/>" +
                "<row id=\"3\" secid=\"KTYL\" regnumber=\"3-11-11111-A\" name=\"Stan\" emitentTitle=\"Marvel\"/>" +
                "</rows>" +
                "</data>" +
                "</document>";
        assertTrue(body.equals(testResult));
    }
}
