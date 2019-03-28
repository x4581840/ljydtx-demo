package com.demo.thread;

public class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.out.println("run...");
    }

    public static void main(String[] args) {
        RunnableTest rt = new RunnableTest();
        rt.run();
    }
}
