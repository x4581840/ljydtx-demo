package com.demo.bo;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class UserBoImpl /*extends SqlSessionDaoSupport*/ implements UserBo {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /*@Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }*/
}
