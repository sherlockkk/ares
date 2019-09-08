package com.sherlockkk.ares.controller;

import com.alibaba.excel.EasyExcel;
import com.sherlockkk.ares.domain.StockData;
import com.sherlockkk.ares.listener.StockDataListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

	private List<StockData> cacheDatas = new ArrayList<>();

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/upload")
	public String upload(MultipartFile file, Model model) throws IOException {
		resetDatas();

		StockDataListener readListener = new StockDataListener();
		EasyExcel.read(file.getInputStream(), StockData.class, readListener).sheet().doRead();
		cacheDatas = readListener.getData();
		model.addAttribute("success", true);
		return "index";
	}

	@GetMapping("/view")
	@ResponseBody
	public List<StockData> view() {
		return cacheDatas;
	}

	@GetMapping("/download")
	public void download(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=demo.xlsx");
		EasyExcel.write(response.getOutputStream(), StockData.class).sheet("sheet").doWrite(cacheDatas);

		resetDatas();
	}

	private void resetDatas() {
		cacheDatas = new ArrayList<>();
	}

	@PostMapping("/update")
	@ResponseBody
	public Map<Integer, String> update(String code, Integer count) {
		Map<Integer, String> result = new HashMap<>();
		if (cacheDatas == null || cacheDatas.isEmpty()) {
			result.put(1, "请先上传原始Excel文件");
			return result;
		}
		Integer goodCount;
		Integer deviationCount;
		for (StockData cacheData : cacheDatas) {
			if (code.equals(cacheData.getCode())) {
				goodCount = Integer.valueOf(cacheData.getGoodCount());
				cacheData.setCheckCount(count.toString());
				deviationCount = count - goodCount;
				cacheData.setDeviationCount(String.valueOf(deviationCount));
				result.put(1, "操作成功！好料数量：" + goodCount + ",核对数量：" + count + ",误差数量：" + deviationCount);
				return result;
			}
		}
		result.put(1, "查无此配件");
		return result;
	}

	@GetMapping("/getOneByCode")
	@ResponseBody
	public StockData getOneByCode(String code) {
		if (cacheDatas == null || cacheDatas.isEmpty()) {
			StockData stockData = new StockData();
			stockData.setName("请先上传原始数据！！！");
			return stockData;
		}
		for (StockData cacheData : cacheDatas) {
			if (code.equals(cacheData.getCode())) {
				return cacheData;
			}
		}
		StockData stockData = new StockData();
		stockData.setName("未找到相匹配配件！！！");
		return stockData;
	}
}
