package com.zhi.service;

import com.zhi.dao.ProfitMapper;
import com.zhi.pojo.Profit;

import java.util.List;

public class ProfitServiceImpl implements ProfitService{

    ProfitMapper profitMapper;

    public void setProfitMapper(ProfitMapper profitMapper) {
        this.profitMapper = profitMapper;
    }

    public List<Profit> queryProfitByMonth() {
        return profitMapper.queryProfitByMonth();
    }
}
