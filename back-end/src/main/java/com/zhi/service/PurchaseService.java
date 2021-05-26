package com.zhi.service;

import com.zhi.pojo.Purchase;

import java.util.List;

public interface PurchaseService {

    //男女生最喜欢的商品前三
    List<Purchase> queryTopProductBySex();
}
