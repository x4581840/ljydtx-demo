package com.demo.controller;


import com.demo.mapper.StudentMapper;
import com.demo.model.Student;
import com.demo.service.StudentService;
import com.demo.springcontext.SpringContextUtil;
import com.demo.utils.DateUtils;
import com.demo.zhujie_setvalue.FromRedis;
import com.demo.zhujie_setvalue.LogExecutionTime;
import com.demo.zhujie_setvalue.SomeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019-07-15 11:04
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource(name = "studentService")
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Resource
    private SomeService someService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 101, 10L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());


    @GetMapping(value = "/getStudentList")
    public ResponseEntity getStudentList(@Param("date") String date) {
        String year = date.split("-")[0];
        String weekNo = date.split("-")[1];
        System.out.println("year=" + year);
        System.out.println("weekNo=" + weekNo);
        Date startDate = DateUtils.getStartDayOfWeekNo(Integer.parseInt(year), Integer.parseInt(weekNo));
        Date endDate = DateUtils.getEndDayOfWeekNo(Integer.parseInt(year), Integer.parseInt(weekNo));

        return ResponseEntity.ok(studentService.getStudentList(startDate, endDate));
    }

    @GetMapping(value = "/likeName")
    public ResponseEntity likeNameByDefaultDataSource(@Param("name") String name) {
        return ResponseEntity.ok(studentService.likeNameByDefaultDataSource(name));
    }

    @GetMapping(value = "/getStudentById")
    public ResponseEntity likeNameByDefaultDataSource(@Param("id") Integer id) {
//        try{
               // studentService.insertStudent1();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        StudentService studentService1 = (StudentService)SpringContextUtil.getBean("studentService");
        System.out.println("fuck");
        studentService1.insertStudent1();
        return ResponseEntity.ok(studentService1.getStudentById1(id));
    }



    @PostMapping(value = "/testValid") //必须要加@valid，属性上面的校验才生效
    public ResponseEntity likeNameByDefaultDataSource(@Param("userCond") @RequestBody @Valid @NotNull UserCond userCond) {
        /*if(StringUtils.isBlank(userCond.getName())) {
            throw new RuntimeException("不能为空！");
        }*/
        UserCond cond = new UserCond();
        cond.setId(1);
        return new ResponseEntity(cond, HttpStatus.OK);
    }

    @GetMapping(value = "/updateCount")
    public ResponseEntity updateCount(@RequestParam("count") Integer count) {
        System.out.println("coreSize::"+corePoolSize);
        for(int i=0;i<count;i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    studentService.updateCount();
                }
            });
        }
        System.out.println("end");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/testInjectValue")
    public ResponseEntity updateCount() {
//        System.out.println(stringRedisTemplate.opsForValue().get("yourKey"));
        someService.oneMethod(null, "one_lastParam");
        someService.twoMethod(null, "two_lastParam");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/testNotNull")
    public ResponseEntity test(@com.demo.zhujie_setvalue.NotNull(message = "我丢") @Param(value = "test") String test, Integer test1) {
//        System.out.println("testNotNull");
        return ResponseEntity.ok("testNotNull");
    }

    @LogExecutionTime(value = "Custom Message")
    @GetMapping("/demo")
    public String demoEndpoint(@RequestParam("param") @LogExecutionTime(value = "yourKey") String param) {
        // 模拟业务逻辑
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Demo endpoint executed with param: " + param;
    }
}
