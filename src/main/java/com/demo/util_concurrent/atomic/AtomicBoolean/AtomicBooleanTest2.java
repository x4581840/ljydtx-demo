package com.demo.util_concurrent.atomic.AtomicBoolean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest2 implements Runnable {

    private static AtomicBoolean exists = new AtomicBoolean(false);

    private String name;

    public AtomicBooleanTest2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (exists.compareAndSet(false, true)) {

            System.out.println(name + " enter");
            try {
                System.out.println(name + " working");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // do nothing
            }
            System.out.println(name + " leave");
            exists.set(false);
        } else {
            System.out.println(name + " give up");
        }
    }

    public static void main(String[] args) {

        AtomicBooleanTest2 bar1 = new AtomicBooleanTest2("bar1");
        AtomicBooleanTest2 bar2 = new AtomicBooleanTest2("bar2");
        new Thread(bar1).start();
        new Thread(bar2).start();

    }
}
