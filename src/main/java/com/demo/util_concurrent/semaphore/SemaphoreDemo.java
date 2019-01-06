package com.demo.util_concurrent.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private Semaphore smp = new Semaphore(3);
    private Random rnd = new Random();

    class TaskDemo implements Runnable {

        private String id;

        public TaskDemo(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                smp.acquire();
                System.out.println("Thread " + id + " is working");

                Thread.sleep(rnd.nextInt(1000));
                smp.release();
                System.out.println("Thread " + id + " is over");
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

        //注意我创建的线程池类型，
        ExecutorService es = Executors.newCachedThreadPool();
//        ExecutorService es = Executors.newFixedThreadPool(20);
        //new 内部类的时候必须外部类先实例化，除非内部类是static
        es.submit(semaphoreDemo.new TaskDemo("a"));
        es.submit(semaphoreDemo.new TaskDemo("b"));
        es.submit(semaphoreDemo.new TaskDemo("c"));
        es.submit(semaphoreDemo.new TaskDemo("d"));
        es.submit(semaphoreDemo.new TaskDemo("e"));
        es.submit(semaphoreDemo.new TaskDemo("f"));
        es.submit(semaphoreDemo.new TaskDemo("g"));
        es.submit(semaphoreDemo.new TaskDemo("h"));
        es.submit(semaphoreDemo.new TaskDemo("j"));

        es.shutdown();
    }

    //可以看出，最多同时有三个线程并发执行，也可以认为有三个公共资源（比如计算机的三个串口）。
}
