package com.zhi.service;

import com.zhi.pojo.Product;

import java.util.List;

public interface ProductService {

    //查询销量Top10
    List<Product> queryTopProduct(int num);

    //每个类别的销量第一
    List<Product> queryTopProductByCategory();

    List<Product> getAllProduct(int page_id);

    List<Product> getAllProductByCategory(String category,int page_id);

    List<Product> SearchProduct(String s);

    int getCount(String id);
}
