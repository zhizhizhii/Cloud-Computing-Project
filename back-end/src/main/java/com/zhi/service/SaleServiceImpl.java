package com.zhi.service;

import com.zhi.dao.SaleMapper;
import com.zhi.pojo.Sale;

import java.util.List;

public class SaleServiceImpl implements SaleService{

    SaleMapper saleMapper;

    public void setSaleMapper(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    public List<Sale> queryTotalByMonth() {
        return saleMapper.queryTotalByMonth();
    }
}
