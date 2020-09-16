package com.sherlockkk.ares.dao;

import com.sherlockkk.ares.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<Stock, String> {

    @Modifying
    @Query("update Stock set checkQuantity=?2,differentQuantity=checkQuantity-goodQuantity,updateTime=CURRENT_TIMESTAMP where partsCode=?1")
    int update(String partsCode, Integer checkQuantity);

}
