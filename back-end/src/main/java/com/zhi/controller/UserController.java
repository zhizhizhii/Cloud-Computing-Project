package com.zhi.controller;

import com.zhi.pojo.User;
import com.zhi.service.UserService;
import com.zhi.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/user")
public class UserController {


    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;


    @RequestMapping ( value = "/threeday", method = RequestMethod.GET,produces = "application/json;charset=utf8")
    @ResponseBody
    @CrossOrigin
    public String getUser(@RequestParam String year) {
        List<User> list = userService.queryUser(year);
        return JsonUtil.objectToJson(list);
    }
}
