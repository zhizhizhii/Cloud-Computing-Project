package com.zhi.service;

import com.zhi.dao.PurchaseMapper;
import com.zhi.pojo.Purchase;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService{

    private PurchaseMapper purchaseMapper;

    public void setPurchaseMapper(PurchaseMapper purchaseMapper) {
        this.purchaseMapper = purchaseMapper;
    }

    public List<Purchase> queryTopProductBySex() {
        return purchaseMapper.queryTopProductBySex();
    }
}
