package com.demo.java._volatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {

    private VolatileTest(){}

    public static void main(String[] args) throws InterruptedException {
        VolatileTest v = new VolatileTest();
        v.test_01();
        test_01();
    }

    private static void test_01() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            System.out.println("开始执行");
            while (Value.i == 0) {
                //类Value的i属性没有加volatile关键字之前，这个while是死循环
                /**
                 * 我们来解释下这么程序的意图吧。刚开始那么没有加volatile关键字修饰的demo是这样的。
                 * 子线程去确认i的值是否为0,如果是0则一直死循环。当我们在主线程里将i的值设置为1000时。发现还是在死循环。
                 * 这说明子线程没有读取到i的最新的值。这里要讲一下jvm内存模型了。i这个变量是存放在主内存中的。
                 * 线程是这样执行的,将主内存里面的内容读取到线程特有的工作内存中去,然后再去执行相应的操作。
                 * 如果值是有更新的话,再将值刷新回主内存。所以这里也可以看出线程之间的内部的内存里的变量是无法共享的。
                 * 也就是说线程与线程之间无法之间将自己的数据给对方。
                 *
                 */
            }
            System.out.println("执行结束");
        });
        System.out.println("111");
        Thread.sleep(1000);
        System.out.println("222");
        //子线程把类Value的属性i的值改变了
        Value.i = 1000;
        System.out.println("333");
        executorService.shutdown();
    }
}

class Value{
    public volatile static int i = 0;
}
