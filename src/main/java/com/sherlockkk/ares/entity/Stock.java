package com.sherlockkk.ares.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Stock {

    /**
     * 配件编号
     */
    @Id
    @ExcelProperty("Parts code")
    @Column(nullable = false, unique = true)
    private String partsCode;

    /**
     * 配件名称及规格
     */
    @ExcelProperty("Part name and specification")
    @Column(nullable = false)
    private String partNameAndSpecification;

    /**
     * 好料数量
     */
    @ExcelProperty("Total quantity of good materials")
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer goodQuantity;

    /**
     * 核对数量
     */
    @ExcelProperty("Check quantity")
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer checkQuantity;

    /**
     * 差异数量
     */
    @ExcelProperty("Different quantity")
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer differentQuantity;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelIgnore
    @LastModifiedDate
    private LocalDateTime updateTime;

}
