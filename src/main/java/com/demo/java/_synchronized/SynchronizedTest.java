package com.demo.java._synchronized;

public class SynchronizedTest {

    public static void main(String[] args) {

        while (true) {
            System.out.println("true");
        }
    }

    public synchronized void doSth(){
        System.out.println("Hello World");
    }

    public void doSth1(){
        synchronized (SynchronizedTest.class){
            System.out.println("Hello World");
        }
    }

    public void doSth2(){
        synchronized(this){
            System.out.println("Hello World");
        }
    }
}
