package com.demo.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程之间的通信
 */
public class ThreadTest {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        runDAfterABC();
//        runABCWhenAllReady();
        doTaskWithResultInWorker();
    }

    //子线程完成某件任务后，把得到的结果回传给主线程

    private static void doTaskWithResultInWorker() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task starts");
                Thread.sleep(3000);
                int result = 0;
                for (int i = 0; i <= 100; i++) {
                    result += i;
                }
                System.out.println("Task finished and return result");
                return result;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("Before futureTask.get()");
            System.out.println("Result:" + futureTask.get());
            System.out.println("After futureTask.get()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //三个运动员各自准备，等到三个人都准备好后，再一起跑

    /**
     * 上面是一个形象的比喻，针对 线程 A B C 各自开始准备，直到三者都准备完毕，然后再同时运行 。也就是要实现一种 线程之间互相等待 的效果，那应该怎么来实现呢？
     * <p>
     * 上面的 CountDownLatch 可以用来倒计数，但当计数完毕，只有一个线程的 await() 会得到响应，无法让多个线程同时触发。
     * <p>
     * 为了实现线程间互相等待这种需求，我们可以利用 CyclicBarrier 数据结构，它的基本用法是：
     * <p>
     * 先创建一个公共 CyclicBarrier 对象，设置 同时等待 的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
     * 这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人；
     * 当指定的 同时等待 的线程数都调用了 cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。
     */

    private static void runABCWhenAllReady() {
        int runner = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        final Random random = new Random();
        for (char runnerName = 'A'; runnerName <= 'C'; runnerName++) {
            final String rN = String.valueOf(runnerName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long prepareTime = random.nextInt(10000) + 100;
                    System.out.println(rN + "is preparing for time:" + prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(rN + "is prepared, waiting for others");
                        cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(rN + "starts running"); // 所有运动员都准备好了，一起开始跑
                }
            }).start();
        }
    }
    //四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的

    /**
     * 针对这种情况，我们可以利用 CountdownLatch 来实现这类通信方式。它的基本用法是：
     * <p>
     * 创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
     * 在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
     * 在 其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
     * 当 其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。
     */
    private static void runDAfterABC() {
        int worker = 3;
        CountDownLatch countDownLatch = new CountDownLatch(worker);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D is waiting for other three threads");
                try {
                    countDownLatch.await();
                    System.out.println("All done, D starts working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tN + "is working");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(tN + "finished");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }
    /**
     * 其实简单点来说，CountDownLatch 就是一个倒计数器，我们把初始计数值设置为3，当 D 运行时，先调用 countDownLatch.await() 检查计数器值是否为 0，若不为 0 则保持等待状态；当A B C 各自运行完后都会利用countDownLatch.countDown()，将倒计数器减 1，当三个都运行完后，计数器被减至 0；此时立即触发 D 的 await() 运行结束，继续向下执行。

     因此，CountDownLatch 适用于一个线程去等待多个线程的情况。
     */

    //那如何让 两个线程按照指定方式有序交叉运行呢？

    /**
     * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
     * <p>
     * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
     * 这里有个缺陷，这里并不能保证A一定先执行
     */
    private static void demo2() {
        Object lock = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: A 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: A 得到了锁 lock");
                    System.out.println("A 1");
                    try {
                        System.out.println("INFO: A 准备进入等待状态，放弃锁 lock 的控制权");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("INFO: 有人唤醒了 A, A 重新获得锁 lock");
                    System.out.println("A 2");
                    System.out.println("A 3");
                }
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: B 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: B 得到了锁 lock");
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");
                    System.out.println("INFO: B 打印完毕，调用 notify 方法");
                    lock.notify();
                }
            }
        });
        A.start();
        B.start();
    }

    /**
     * 首先创建一个 A 和 B 共享的对象锁 lock = new Object();
     * 当 A 得到锁后，先打印 1，然后调用 lock.wait() 方法，交出锁的控制权，进入 wait 状态；
     * 对 B 而言，由于 A 最开始得到了锁，导致 B 无法执行；直到 A 调用 lock.wait() 释放控制权后， B 才得到了锁；
     * B 在得到锁后打印 1， 2， 3；然后调用 lock.notify() 方法，唤醒正在 wait 的 A;
     * A 被唤醒后，继续打印剩下的 2，3。
     */

    //如何让两个线程依次执行？假设有两个线程，一个是线程 A，另一个是线程 B，两个线程分别依次打印 1-3 三个数字即可
    private static void demo1() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        //我们能看到 A.join() 方法会让 B 一直等待直到 A 运行完毕。
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber("B");
            }
        });
        B.start();
        A.start();
    }

    private static void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "print:" + i);
        }
    }
}
