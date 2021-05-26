package com.zhi.service;

import com.zhi.pojo.User;

import java.util.List;

public interface UserService {

    List<User> queryUser(String year);
}
