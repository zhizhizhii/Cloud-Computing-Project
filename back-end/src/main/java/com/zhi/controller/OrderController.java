package com.zhi.controller;


import com.zhi.pojo.Order;
import com.zhi.service.OrderService;
import com.zhi.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    @Qualifier("OrderServiceImpl")
    private OrderService orderService;

    @RequestMapping ( value = "/getPurchaseById", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String getPurchaseById(@RequestParam String p_id) {
        List<Order> list = orderService.SearchByTime(p_id);
        return JsonUtil.objectToJson(list);
    }
}
