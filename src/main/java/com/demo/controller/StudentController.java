package com.demo.controller;

import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 11:36 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getById")
    public Student getById(@RequestParam(name = "id") Integer id) {
        return null;
    }

    @GetMapping("/getList")
    public List<Student> getList() {
        return studentService.getList();
    }

    @GetMapping("/getListByDs1")
    public List<Student> getListByDs1() {
        return studentService.getListByDs1();
    }

    @GetMapping("/getListByDs2")
    public List<Student> getListByDs2() {
        return studentService.getListByDs2();
    }

    @GetMapping("/getAndGet")
    public Map<String, List<Student>> getAndGet() {
        return studentService.getAndGet();
    }
}
