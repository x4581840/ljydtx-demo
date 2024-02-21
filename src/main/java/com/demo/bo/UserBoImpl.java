package com.demo.bo;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
@Lazy()
public class UserBoImpl /*extends SqlSessionDaoSupport*/ implements UserBo {

    @Resource
    private UserMapper userMapper;//

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
        List<User> userList = userMapper.selectUserByCondition(userName);
        List<User> userFromList = new ArrayList<>(userList);
        Iterator<User> iterator = userFromList.iterator();

        while (iterator.hasNext()) {

            User next = iterator.next();
            if("zhangsan".equals(next.getUserName())){
                System.out.println("删除：" + next.getUserName());
                iterator.remove();
            }

        }
        userList = userFromList;
        return userList;
    }

    @Override
    public List<User> selectUserByUserList(List<User> userList) {
        return userMapper.selectUserByUserList(userList);
    }

    /*@Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }*/
}
