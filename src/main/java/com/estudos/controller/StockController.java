package com.estudos.controller;

import com.estudos.model.Stock;
import com.estudos.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class StockController {

//    public StockController(StockService stockService) {
//        this.stockService = stockService;
//    }

    private final StockService stockService;

    @GetMapping(value = "/stocks")
    public @ResponseBody
    List<Stock> getStocks(@RequestParam("quantidade") int quantidade) {
        return stockService.createMockStocks(quantidade);
    }

}
