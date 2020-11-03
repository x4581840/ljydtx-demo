package com.demo.mapper.impl;

import com.demo.mapper.StudentMapper;
import com.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 11:16 AM
 * @Version 1.0
 **/

@Mapper
public interface StudentImplMapper extends StudentMapper {

    // 注解 @TargetDataSource 不可以在这里使用
    List<Student> likeName(String name);

    Student getById(int id);

    String getNameById(int id);

    List<Student> getStudentList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    void updateCount();
}
