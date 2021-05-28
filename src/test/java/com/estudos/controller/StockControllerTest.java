package com.estudos.controller;

import com.estudos.model.Stock;
import com.estudos.service.StockService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
@RequiredArgsConstructor
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void chamarSemParametroDeveRetornarBadRequest() throws Exception {
        /*
            Teste de falha: a controller necessitas de um parâmetro chamado 'quantidade',
            logo, um teste de falha pode ser criado caso o mesmo não seja passado
         */

        List<Stock> result = new ArrayList<>();

        result.add(new Stock(new Date(), "NEWSTOCK", BigDecimal.TEN));

        Mockito.when(stockService.createMockStocks(5)).thenReturn(result);

        this.mockMvc.perform(get("/stocks")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveRetornarSomenteUmRegistro() throws Exception {
        /*
            Teste de sucesso: passando todos o parâmetro 'quantidade' corretamente, o retorno deve ser 200;
        */

        List<Stock> result = new ArrayList<>();

        result.add(new Stock(new Date(), "NEWSTOCK", BigDecimal.TEN));

        Mockito.when(stockService.createMockStocks(1)).thenReturn(result);

        this.mockMvc.perform(get("/stocks").queryParam("quantidade", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("NEWSTOCK")));
    }
}