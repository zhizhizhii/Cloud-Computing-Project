package com.zhi.service;

import com.zhi.dao.ProductMapper;
import com.zhi.pojo.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService{

    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> queryTopProduct(int num) {
        return productMapper.queryTopProduct(num);
    }

    public List<Product> queryTopProductByCategory() {
       return productMapper.queryTopProductByCategory();
    }

    public List<Product> getAllProduct(int page_id) {

        Map paramMap = new HashMap();
        int low = (page_id-1)*100+1;
        int high = page_id * 100 ;
        paramMap.put("low",low);
        paramMap.put("high",high);
        return productMapper.getAllProduct(paramMap);
    }

    public List<Product> getAllProductByCategory(String category,int page_id) {
        int low = (page_id-1)*100+1;
        int high = page_id * 100 ;
        return productMapper.getAllProductByCategory(category,low,high);
    }

    public List<Product> SearchProduct(String s){
        return productMapper.SearchProduct(s);
    }

    public int getCount(String id) {
        return productMapper.getCount(id);
    }

}
