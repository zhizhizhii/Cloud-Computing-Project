package com.zhi.dao;

import com.zhi.pojo.Purchase;

import java.util.List;

public interface PurchaseMapper {

    //男女生最喜欢的商品前三
    List<Purchase> queryTopProductBySex();
}
