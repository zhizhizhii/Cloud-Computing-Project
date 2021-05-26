package com.zhi.controller;


import com.zhi.pojo.Product;
import com.zhi.service.ProductService;
import com.zhi.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/product")
public class ProductController {

    private int count = 0 ;

    @Autowired
    @Qualifier("ProductServiceImpl")
    private ProductService productService;

    @RequestMapping ( value = "/top", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String top(@RequestParam int num) {
        List<Product> list = productService.queryTopProduct(num);
        return JsonUtil.objectToJson(list);
    }

    @RequestMapping ( value = "/categorytop", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String topByCategory() {
        List<Product> list = productService.queryTopProductByCategory();
        return JsonUtil.objectToJson(list);
    }

    @RequestMapping ( value = "/getAllProduct", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String getAllProduct(@RequestParam int page_id) {
        List<Product> list = productService.getAllProduct(page_id);
        return JsonUtil.objectToJsonWithCount(list,251662);
    }

    @RequestMapping ( value = "/getProductByCategroy", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String getProductByCategory(@RequestParam String category,int page_id,int c) {
        int count = 0;
        if(c==0){
            count = productService.getCount(category);
        }
        List<Product> list = productService.getAllProductByCategory(category,page_id);
        return JsonUtil.objectToJsonWithCount(list,count);
    }

    @RequestMapping ( value = "/searchProduct", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String searchProduct(@RequestParam String s) {
        List<Product> list = productService.SearchProduct(s);
        return JsonUtil.objectToJsonWithCount(list,count);
    }
}
