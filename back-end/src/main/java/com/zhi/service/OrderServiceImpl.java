package com.zhi.service;

import com.zhi.dao.OrderMapper;
import com.zhi.pojo.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService{

    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> SearchByTime(String p_id) {
        return orderMapper.SearchByTime(p_id);
    }
}
