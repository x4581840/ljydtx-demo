package com.demo.mapper;

import com.demo.model.User;
import com.demo.model.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User lockUserById(Integer id);

    List<User> selectUserByCondition(@Param("userName") String userName);

    List<User> selectUserByUserList(@Param("userList") List<User> userList);
}