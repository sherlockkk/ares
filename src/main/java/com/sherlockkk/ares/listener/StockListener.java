package com.sherlockkk.ares.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sherlockkk.ares.entity.Stock;
import com.sherlockkk.ares.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StockListener extends AnalysisEventListener<Stock> {
    @Autowired
    private StockService stockService;
    private final List<Stock> stocks = new ArrayList<>();

    @Override
    public void invoke(Stock stock, AnalysisContext analysisContext) {
        stocks.add(stock);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        stockService.saveAll(stocks);
        stocks.clear();
    }
}
