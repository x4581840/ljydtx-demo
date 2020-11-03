package com.demo.util_concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3);
//                    int i = 0;
//                    if(i == 0) {
//                        //如果这里抛出错误，主线程会一直卡着，因为没有countDown
//                        throw new RuntimeException("抛出错误");
//                    }
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//        int i = 0;
//        if(i == 0) {
//            //如果这里抛出错误，主线程不会继续执行
//            throw new RuntimeException("抛出错误");
//        }

        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
