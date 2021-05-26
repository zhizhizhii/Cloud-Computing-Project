package com.zhi.service;

import com.zhi.dao.UserMapper;
import com.zhi.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserMapper userMapper;


    public void setUserMapper(com.zhi.dao.UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> queryUser(String year) {
        return userMapper.queryUser(year);
    }
}
