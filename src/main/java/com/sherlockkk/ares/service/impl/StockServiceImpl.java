package com.sherlockkk.ares.service.impl;

import com.sherlockkk.ares.dao.StockDao;
import com.sherlockkk.ares.entity.Stock;
import com.sherlockkk.ares.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;

    @Override
    public Stock save(Stock stock) {
        return stockDao.save(stock);
    }

    @Override
    public List<Stock> saveAll(List<Stock> stocks) {
        return stockDao.saveAll(stocks);
    }

    @Override
    public Optional<Stock> findByPartsCode(String partsCode) {
        return stockDao.findById(partsCode);
    }

    @Override
    public List<Stock> findAll() {
        return stockDao.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(String partsCode, Integer checkQuantity) {
        return stockDao.update(partsCode, checkQuantity) > 0;
    }
}
