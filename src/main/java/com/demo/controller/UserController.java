package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.bo.UserBo;
import com.demo.model.Student;
import com.demo.model.User;

import com.demo.model.ValidationObject;
import com.demo.page.Page;
import com.demo.page.PageHelper;
import com.demo.page.PageInfo;
import com.demo.page.PageRequest;
import com.demo.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 测试本地合并分支
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBo userBo;

    @Resource
    private UserService userService;

    @RequestMapping("/test_long2")
    public void test_long2() {
        System.out.println("tttt");
        System.out.println("ffff");
    }

    @RequestMapping("/test_long")
    public void test_long() {
        System.out.println("====");
        System.out.println("----");
    }

    @RequestMapping("/test_long1")
    public void test_long1() {
        System.out.println("tttt");
        System.out.println("ffff");
    }

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

    @RequestMapping("/selectUser")
    public List<User> selectUser(@RequestParam("userName") String userName) {
        return userBo.selectUserByCondition(userName);
    }

    @RequestMapping("/testjisshu")
    public User testjisshu() {
        MyThread myThread1 = new MyThread();
        Thread t1 = new Thread(myThread1);
        MyThread myThread2 = new MyThread();
        Thread t2 = new Thread(myThread2);
        MyThread myThread3 = new MyThread();
        Thread t3 = new Thread(myThread3);
        MyThread myThread4 = new MyThread();
        Thread t4 = new Thread(myThread4);
        MyThread myThread5 = new MyThread();
        Thread t5 = new Thread(myThread5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
//        userBo.testJishu();

        return userBo.selectUser(1);
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            userBo.testJishu();
        }
    }

    @RequestMapping("/getUserInfoByName")
    public ResponseEntity<Page<User>> getName(@RequestParam(name = "name") String name) {
        List<User> userList = userBo.selectUserByCondition(name);

        System.out.println(userList.getClass().getName()+",size:"+userList.size());

        PageInfo pageInfo = new PageInfo(1, 2);
        Page<User> listPage = new Page(userList, pageInfo, userList.size());

        PageRequest pageRequest = new PageRequest(1, 2);
        Page<User> userPage = PageHelper.doPageAndSort(pageRequest, () -> listPage);
        System.out.println(userPage.getContent().size());
        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/test")
    public void test_query(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("test");

        String test = "longjianyong";
        byte[] bytes = test.getBytes();
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

        response.reset();
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s", "longjianyong.txt"));
        response.setContentType("application/pdf;charset=UTF-8");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        response.setDateHeader("Expires", System.currentTimeMillis() + 1000L);

        IOUtils.write(bytes, out);
        out.flush();
        //out.close();
    }

    public static void main(String[] args) {
        System.out.println(Pattern.matches("^[A-Z0-9][A-Z0-9-_./]*$", "274-S2"));

        //Validator
        Test t = new Test();
        t.setAmount(BigDecimal.ZERO);
        BigDecimal res = t.getAmount().multiply(null);
        System.out.println(res);

    }

    static class Test {
        public BigDecimal amount;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    @GetMapping("validation")
    public ResponseEntity testValidation(@RequestBody @Valid ValidationObject obj) {
        //Validator validator = new Validator();
        System.out.println(obj.getId());
        System.out.println(obj.getName());
        System.out.println(obj.getAreBeautiful());
        return ResponseEntity.ok(true);
    }

    @GetMapping("validation_list")
    public ResponseEntity validation_list(@RequestBody List<ValidationObject> obj) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<List<ValidationObject>>> res = validator.validate(obj);

        for (ValidationObject validationObject : obj) {
            System.out.println(validationObject.getId());
            System.out.println(validationObject.getName());
            System.out.println(validationObject.getAreBeautiful());
        }
        return ResponseEntity.ok(true);
    }
}


