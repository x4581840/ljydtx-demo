package com.demo.bo;


import com.demo.model.User;

import java.util.List;

public interface UserBo {
    void insertUser(User user);

    User selectUser(Integer id);

    void testJishu();

    List<User> selectUserByCondition(String userName);
}
