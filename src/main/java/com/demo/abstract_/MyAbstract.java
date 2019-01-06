package com.demo.abstract_;

public abstract class MyAbstract {
    private String state;

    abstract void func();

    protected void setState(String state) {
        this.state = state;
        System.out.println(this.state);
    }
}
