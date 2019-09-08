package com.sherlockkk.ares.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sherlockkk.ares.domain.StockData;

import java.util.ArrayList;
import java.util.List;

public class StockDataListener extends AnalysisEventListener<StockData> {

	private List<StockData> data = new ArrayList<>();

	public List<StockData> getData() {
		return data;
	}

	@Override
	public void invoke(StockData stockData, AnalysisContext context) {
		data.add(stockData);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {

	}
}
