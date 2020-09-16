package com.sherlockkk.ares.service;

import com.sherlockkk.ares.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {

    Stock save(Stock stock);

    List<Stock> saveAll(List<Stock> stocks);

    Optional<Stock> findByPartsCode(String partsCode);

    List<Stock> findAll();

    boolean update(String partsCode, Integer checkQuantity);
}
