package com.zhi.controller;

import com.zhi.pojo.Purchase;
import com.zhi.service.PurchaseService;
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
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired
    @Qualifier("PurchaseServiceImpl")
    private PurchaseService purchaseService;

    @RequestMapping ( value = "/genderprefer", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String genderPrefer() {
        List<Purchase> list = purchaseService.queryTopProductBySex();
        return JsonUtil.objectToJson(list);
    }

}
