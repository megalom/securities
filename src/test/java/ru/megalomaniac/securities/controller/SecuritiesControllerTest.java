package ru.megalomaniac.securities.controller;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SecuritiesController.class)
public class SecuritiesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception{
        mockMvc.perform(get("/api/securities"))
                .andExpect(status().isOk());
    }
}
