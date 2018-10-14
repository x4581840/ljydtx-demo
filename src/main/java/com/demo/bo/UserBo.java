package com.demo.bo;


import com.demo.model.User;

public interface UserBo {
    void insertUser(User user);

    User selectUser(Integer id);

    void testJishu();
}
