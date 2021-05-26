package com.zhi.dao;

import com.zhi.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    
    List<Order> SearchByTime(@Param("p_id") String p_id);
}
