package com.demo.shejimoshi._danli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 双重检查加锁式单例--- 第三种
 * <p>
 * 这种实现方式既可以实现线程安全地创建实例，而又不会对性能造成太大的影响。它只是第一次创建实例的时候同步，以后就不需要同步了，
 * 从而加快了运行速度。
 * <p>
 * 提示：由于volatile关键字可能会屏蔽掉虚拟机中一些必要的代码优化，所以运行效率并不是很高。因此一般建议，没有特别的需要，
 * 不要使用。也就是说，虽然可以使用“双重检查加锁”机制来实现线程安全的单例，但并不建议大量采用，可以根据情况来选用。
 * <p>
 * 根据上面的分析，常见的两种单例实现方式都存在小小的缺陷，那么有没有一种方案，既能实现延迟加载，又能实现线程安全呢？
 */
public class DoubleCheckSingleton {

    //    private static volatile DoubleCheckSingleton singleton = null;
    private static DoubleCheckSingleton singleton = null;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getSingleton() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (singleton == null) {
            //同步块，线程安全的创建实例
            synchronized (DoubleCheckSingleton.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        // 静态变量singleton没有被volatile修饰时的测试（没测出来singleton为null情况）
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        for (int i = 1; i <= 10000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("thread name:"+Thread.currentThread().getName()+",single:"+DoubleCheckSingleton.getSingleton());
                    DoubleCheckSingleton singleton = DoubleCheckSingleton.getSingleton();
                    if (singleton == null) {
                        System.out.println("thread name:" + Thread.currentThread().getName() + ",single:" + singleton);
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
