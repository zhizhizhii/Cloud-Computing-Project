package com.zhi.dao;

import com.zhi.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    //查询销量Top10
    List<Product> queryTopProduct(@Param("num") int num);

    //每个类别的销量第一
    List<Product> queryTopProductByCategory();

    List<Product> getAllProduct(Map paramMap);

    List<Product> getAllProductByCategory(@Param("category") String category,@Param("low") int low, @Param("high") int high);

    List<Product> SearchProduct(@Param("s") String s);

    int getCount(@Param("id") String id);
}
