package com.demo.shiwu;

import com.demo.mapper.StudentMapper;
import com.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/11/12 9:32 AM
 * @Version 1.0
 **/

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void serivce1(String name) {
        Student stu = new Student();
        stu.setName(name);
        studentMapper.insert(stu);
    }

    @Override
    public void service2(String name) {
        Student stu = new Student();
        stu.setName(name);
        studentMapper.insert(stu);
    }

    @Override
    public void service3(String name) {
        Student stu = new Student();
        stu.setName(name);
        studentMapper.insert(stu);
        int i = 1/0;
    }

}
