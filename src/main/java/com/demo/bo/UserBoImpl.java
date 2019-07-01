package com.demo.bo;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Component
public class UserBoImpl /*extends SqlSessionDaoSupport*/ implements UserBo {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User selectUser(Integer id) {
        return userMapper.lockUserById(id);
    }

    @Override
    @Transactional
    public void testJishu() {
        User user = userMapper.lockUserById(1);
        user.setUserAge(user.getUserAge() + 1);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> selectUserByCondition(String userName) {
        return userMapper.selectUserByCondition(userName);
    }
    /*@Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }*/
}
