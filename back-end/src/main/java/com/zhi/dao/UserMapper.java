package com.zhi.dao;

import com.zhi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //查询某年连续三天以上购买

    List<User> queryUser(@Param("year") String year);


}
