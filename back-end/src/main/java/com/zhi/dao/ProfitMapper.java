package com.zhi.dao;

import com.zhi.pojo.Profit;

import java.util.List;

public interface ProfitMapper {
    
    List<Profit> queryProfitByMonth();
}
