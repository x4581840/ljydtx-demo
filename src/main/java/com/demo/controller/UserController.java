package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.bo.UserBo;
import com.demo.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBo userBo;

    @RequestMapping("/insertUser")
    public User test() {
        User user = new User();
        user.setUserName("zhangsan");
        user.setUserAge(20);
        user.setUserAddress("hangzhou");
        user.setUserPassword("123");
        user.setUserInfo(JSONObject.toJSONString(user));
        //user.setUserInfo(user.toString()); 如果不是json字符串，就好报错
        userBo.insertUser(user);
        return user;
    }
}
