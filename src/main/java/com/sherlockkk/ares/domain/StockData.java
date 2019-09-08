package com.sherlockkk.ares.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StockData {
	@ExcelProperty(value = "配件代码")
	private String code;
	@ExcelProperty(value = "配件名称及规格")
	private String name;
	@ExcelProperty(value = "单位")
	private String unit;
	@ExcelProperty(value = "发放价")
	private String issuePrice;
	@ExcelProperty(value = "零售价")
	private String retailPrice;
	@ExcelProperty(value = "环保优惠价")
	private String preferentialPrice;
	@ExcelProperty(value = "好料总数量")
	private String goodCount;

	@ExcelProperty(value = "核对数量")
	private String checkCount;
	@ExcelProperty(value = "误差数量")
	private String deviationCount;

	@ExcelProperty(value = "坏料总数量")
	private String badCount;
	@ExcelProperty(value = "货位号")
	private String locationNumber;
	@ExcelProperty(value = "网点代码")
	private String netCode;
	@ExcelProperty(value = "厂别")
	private String factory;
	@ExcelProperty(value = "适用机型")
	private String applicableModels;
	@ExcelProperty(value = "配件属性")
	private String accessoryProperties;
}
