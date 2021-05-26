package com.zhi.service;

import com.zhi.pojo.Order;

import java.util.List;

public interface OrderService {

    List<Order> SearchByTime(String p_id);
}
