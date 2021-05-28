package com.estudos.service;

import com.estudos.model.Stock;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
//@RequiredArgsConstructor
public class StockService {

    public List<Stock> createMockStocks(int quantidade) {
        return IntStream.range(0, quantidade).mapToObj(i -> new Stock(new Date(), "STK" + i, BigDecimal.valueOf(i))).collect(Collectors.toList());
    }
}
