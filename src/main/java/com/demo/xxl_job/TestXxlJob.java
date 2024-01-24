package com.demo.xxl_job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class TestXxlJob {

    @XxlJob("demoJobHandler")
    public void testXxlJob_1() {
        System.out.println("开始第一个xxlJob任务");
        System.out.println("执行任务");
        System.out.println("结束第一个xxlJob任务");
    }
}
