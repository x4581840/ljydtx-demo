package com.demo.thread;

import com.demo.util_concurrent.atomic.concurrentHashMap.Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest implements Callable<String> {

    private String name;

    public CallableTest(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(name + " call...");
        Thread.sleep(3000);
        return name + " call";
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        //普通线程
        CallableTest ct = new CallableTest("putong");
        String result = ct.call();
        System.out.println("result1:"+result);

        //提交任务
        CallableTest ct1 = new CallableTest("renwu");
        Future<String> res = executorService.submit(ct1);
        System.out.println("result2:"+res.get());

        //提交任务
        RunnableTest rt = new RunnableTest();
        executorService.submit(rt);

        executorService.shutdown();
    }


}
