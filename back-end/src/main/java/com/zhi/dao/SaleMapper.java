package com.zhi.dao;

import com.zhi.pojo.Sale;

import java.util.List;

public interface SaleMapper {

    List<Sale> queryTotalByMonth();
}
