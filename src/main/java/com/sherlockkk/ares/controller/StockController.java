package com.sherlockkk.ares.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sherlockkk.ares.common.base.Result;
import com.sherlockkk.ares.domain.StockData;
import com.sherlockkk.ares.entity.Stock;
import com.sherlockkk.ares.listener.StockListener;
import com.sherlockkk.ares.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private StockListener stockListener;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file, Model model) throws IOException {
        if (file.getSize() == 0) {
            model.addAttribute("error", true);
            model.addAttribute("message", "不允许上传空文件");
            return "index";
        }
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        int i = originalFilename.lastIndexOf(".");
        String fileSuffix = originalFilename.substring(i);
        ExcelTypeEnum excelTypeEnum;
        if (".xls".equals(fileSuffix)) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        } else if (".xlsx".equals(fileSuffix)) {
            excelTypeEnum = ExcelTypeEnum.XLSX;
        } else {
            model.addAttribute("error", true);
            model.addAttribute("message", "上传文件格式不正确");
            return "index";
        }

        EasyExcel.read(file.getInputStream(), Stock.class, stockListener).excelType(excelTypeEnum).sheet().doRead();
        model.addAttribute("success", true);
        return "index";
    }

    @GetMapping("/view")
    @ResponseBody
    public List<StockData> view() {
        return Collections.emptyList();
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        List<Stock> stocks = stockService.findAll();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=data.xlsx");
        EasyExcel.write(response.getOutputStream(), Stock.class).sheet("sheet").doWrite(stocks);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestParam String partsCode, @RequestParam(defaultValue = "0") Integer checkQuantity) {
        if (StringUtils.isEmpty(partsCode)) {
            return Result.error("partsCode cannot be empty");
        }
        return stockService.update(partsCode, checkQuantity) ? Result.ok() : Result.error("The part is not found, please check the part code");
    }

    @GetMapping("/getOneByCode")
    @ResponseBody
    public Result getOneByCode(@RequestParam String partsCode) {
        if (StringUtils.isEmpty(partsCode)) {
            return Result.error("partsCode cannot be empty");
        }
        Optional<Stock> optional = stockService.findByPartsCode(partsCode);
        return optional.map(Result::ok).orElseGet(() -> Result.error("The part is not found, please check the part code"));
    }
}
