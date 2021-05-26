package com.zhi.controller;

import com.zhi.pojo.Sale;
import com.zhi.service.SaleService;
import com.zhi.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/sale")
public class SaleController {

    @Autowired
    @Qualifier("SaleServiceImpl")
    private SaleService saleService;

    @RequestMapping ( value = "/monthtotal", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String monthTotal() {
        List<Sale> list = saleService.queryTotalByMonth();
        return JsonUtil.objectToJson(list);
    }
}
