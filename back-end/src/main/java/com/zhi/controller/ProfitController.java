package com.zhi.controller;


import com.zhi.pojo.Profit;
import com.zhi.service.ProfitService;
import com.zhi.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/profit")
public class ProfitController {


    @Autowired
    @Qualifier("ProfitServiceImpl")
    private ProfitService profitService;


    @RequestMapping ( value = "/monthprofit", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String monthProfit() {
        List<Profit> list = profitService.queryProfitByMonth();
        return JsonUtil.objectToJson(list);
    }
}
